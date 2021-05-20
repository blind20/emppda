package com.flj.latte.delegates;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.flj.latte.ui.camera.CameraImageBean;
import com.flj.latte.ui.camera.LatteCamera;
import com.flj.latte.ui.camera.RequestCodes;
import com.flj.latte.ui.scanner.ScannerDelegate;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.flj.latte.util.file.FileUtil;
import com.yalantis.ucrop.UCrop;


import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


/**
 * Created by  on 2017/4/2
 */

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    //车辆识别代号照片种类
    public static final String ZPZL_CLSBDH ="30";
    public static final String ZPZL_CZQ45 ="11";

    //不是直接调用方法
    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.startFastCyCamera(this);
    }


    //这个是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }


    //扫描二维码(不直接调用)
    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCodes.SCAN);
    }
    //扫描二维码
    public void startScanWithCheck(BaseDelegate delegate) {
        PermissionCheckerDelegatePermissionsDispatcher.startScanWithPermissionCheck(this,delegate);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_LONG).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever() {
        Toast.makeText(getContext(), "永久拒绝权限", Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCodes.TAKE_PHOTO:  //fastCy拍照调用
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    String zpzl = CallbackManager.getInstance().getPARAM();
                    if(zpzl.equals(this.ZPZL_CLSBDH)){
//                        UCrop.of(resultUri, resultUri).start(getContext(), this);
                        UCrop uCrop = UCrop.of(resultUri, resultUri);
                        uCrop.withAspectRatio(1,3).start(getContext(),this);
                    }else {
                        try {
                            if(zpzl.equals(this.ZPZL_CZQ45)){
                                FileUtil.saveBitmap(getContext(),resultUri,100,4);
                            }else{
                                FileUtil.saveBitmap(getContext(), resultUri);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        FileUtil.galleryAddPhoto(getContext(), resultUri);
                        final IGlobalCallback<Uri> takephotoCallback = CallbackManager
                                .getInstance()
                                .getCallback(CallbackType.ON_TAKE_PHOTO);
                        if (takephotoCallback != null) {
                            takephotoCallback.executeCallback(resultUri);
                        }
                    }
                    break;
                case RequestCodes.CROP_PHOTO://fastCy剪裁调用
                    final Uri cropUri = UCrop.getOutput(data);
                    try {
//                        FileUtil.saveBitmap(getContext(), cropUri);
                        FileUtil.saveBitmap(getContext(),cropUri,100,2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FileUtil.galleryAddPhoto(getContext(), cropUri);
                    final IGlobalCallback<Uri> callback = CallbackManager
                            .getInstance()
                            .getCallback(CallbackType.ON_TAKE_PHOTO);
                    if (callback != null) {
                        callback.executeCallback(cropUri);
                    }
                    break;
                case RequestCodes.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        //从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400)
                                .start(getContext(), this);
                    }
                    break;
                case RequestCodes.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
