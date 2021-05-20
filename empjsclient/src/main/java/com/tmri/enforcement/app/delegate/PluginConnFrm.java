package com.tmri.enforcement.app.delegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.flj.latte.util.log.LatteLogger;
import com.tmri.enforcement.app.R;
import com.tmri.enforcement.app.R2;
import com.tmri.enforcement.app.common.Constants;
import com.tmri.enforcement.app.common.ToolUtils;
import com.tmri.enforcement.app.db.DbUtils;
import com.tmri.enforcement.app.db.VideoInfo;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class PluginConnFrm extends LatteDelegate {

    @BindView(R2.id.btn_start_record)
    Button btn_start_record;
    @BindView(R2.id.btn_stop_record)
    Button btn_stop_record;
    @BindView(R2.id.msglist)
    RecyclerView    mRecyclerView;
    private Context mContext;
    private Bundle mBundle;
    private DbUtils mDbUtils;

    String empip = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_EMP_SERVICE_IP,"");
    String empport = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_EMP_SERVICE_PORT,"");
    String videoip = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_VIDEO_SYSTEM_IP,"");
    String videoport = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_VIDEO_SYSTEM_PORT,"");
    String empUrl = "http://"+empip+":"+empport+"/emp/";
    String videoUrl = "http://"+videoip+":"+videoport+"/cmsvideo/pdaInfo/writeRecorder";

    @Override
    public Object setLayout() {
        return R.layout.frm_plugin_conn;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        mBundle = getArguments();
        mDbUtils = new DbUtils();
        initUI();
    }

    @OnClick({R2.id.btn_start_record,R2.id.btn_stop_record,R2.id.tv_back,R2.id.tv_next})
    void OnClickBtn(View view){
//        String empip = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_EMP_SERVICE_IP,"");
//        String empport = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_EMP_SERVICE_PORT,"");
//        String videoip = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_VIDEO_SYSTEM_IP,"");
//        String videoport = (String)SharedPreferenceUtils.get(Latte.getApplicationContext(),Constants.PREFS_VIDEO_SYSTEM_PORT,"");
//
//        String empUrl = "http://"+empip+":"+empport+"/emp/";
//        String videoUrl = "http://"+videoip+":"+videoport+"/cmsvideo/pdaInfo/writeRecorder";
        switch (view.getId()){
            case R.id.btn_start_record:
                if(TextUtils.isEmpty(empip)||TextUtils.isEmpty(empport)||TextUtils.isEmpty(videoip)||TextUtils.isEmpty(videoport)){
                    ToastUtils.showLong("请先配置执法记录仪IP,端口以及视频服务器IP,端口");
                    return;
                }
                reqRecordStart(empUrl,Constants.SCHEMA_EMP_START);
                break;
            case R.id.btn_stop_record:
                if(TextUtils.isEmpty(empip)||TextUtils.isEmpty(empport)||TextUtils.isEmpty(videoip)||TextUtils.isEmpty(videoport)){
                    ToastUtils.showLong("请先配置执法记录仪IP,端口以及视频服务器IP,端口");
                    return;
                }
                int factory  = (int) SharedPreferenceUtils.get(mContext,Constants.PREFS_FACTORY_ID,30);
                if(Constants.FACTORY_JINGSHENG == factory){
                    reqRecordStop(empUrl,videoUrl,Constants.SCHEMA_EMP_STOP);
                }else if(Constants.FACTORY_MINGDU == factory){
                    reqRecordStopMd(empUrl,videoUrl,Constants.SCHEMA_EMP_STOP);
                }else if(Constants.FACTORY_HIK== factory){
                    reqRecordStopMd(empUrl,videoUrl,Constants.SCHEMA_EMP_STOP);
                }
                break;
            case R.id.tv_next:
                SettingsFrm settingsFrm = new SettingsFrm();
                getSupportDelegate().start(settingsFrm);
                break;
            case R.id.tv_back:
                finishAppToCyzd();
                break;
        }
    }


    private void initUI() {
        ToastUtils.setGravity(Gravity.CENTER,0,0);
        setListView(mBundle);
        if(Constants.IS_AUTO_TRIGGER){
            btn_start_record.setVisibility(View.GONE);
            btn_stop_record.setVisibility(View.GONE);
        }

        String startOrstop = mBundle.getString("cllx");
        if(TextUtils.isEmpty(startOrstop)){
            btn_start_record.setVisibility(View.VISIBLE);
            btn_stop_record.setVisibility(View.VISIBLE);
        }else{
            if(startOrstop.equals("0")){
                if(TextUtils.isEmpty(empip)||TextUtils.isEmpty(empport)||TextUtils.isEmpty(videoip)||TextUtils.isEmpty(videoport)){
                    ToastUtils.showLong("请先配置执法记录仪IP,端口以及视频服务器IP,端口");
                    return;
                }
                reqRecordStart(empUrl,Constants.SCHEMA_EMP_START);
            }else if(startOrstop.equals("1")){
                if(TextUtils.isEmpty(empip)||TextUtils.isEmpty(empport)||TextUtils.isEmpty(videoip)||TextUtils.isEmpty(videoport)){
                    ToastUtils.showLong("请先配置执法记录仪IP,端口以及视频服务器IP,端口");
                    return;
                }
                reqRecordStopMd(empUrl,videoUrl,Constants.SCHEMA_EMP_STOP);
            }
        }

    }

    private void setListView(Bundle bundle) {
        final List<SimpleListBean> datas = new ArrayList<>();
        String[] items_left = getContext().getResources().getStringArray(R.array.receive_param_cn);
        String[] items_right = getContext().getResources().getStringArray(R.array.receive_param);
        for (int i = 0; i <items_left.length ; i++) {
            SimpleListBean bean = new SimpleListBean.Builder()
                    .setItemType(ItemType.ITEM_NARROW_LIST_2)
                    .setText(items_left[i])
                    .build();
            String rightValue = bundle.getString(items_right[i]);
            bean.setItemValue(rightValue);
            datas.add(bean);
        }
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        final SimpleListAdapter adapter = new SimpleListAdapter(datas,ItemType.ITEM_NARROW_LIST_2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }


    private void reqRecordStart(String rUrl,String schema ) {
        String url = rUrl+schema;
        RestClient.builder().url(url).loader(mContext)
                .success(response -> {
                    VideoInfo videoInfo = getVideoInfoByBundle(mBundle);
                    if (videoInfo != null) {
                        videoInfo.setVideo_begin(new Date());
                        mDbUtils.insertVideo(videoInfo);
                        if(Constants.IS_AUTO_TRIGGER){
                            finishAppToCyzd();
                        }else{
                            quitDlg(mContext,"执法仪录像开始");
                        }
                    }else {
                        alertDlg(mContext,"查验终端传递参数非法");
                    }
                })
                .failure(() -> alertDlg(mContext,"网络不通，请结束查验并检查网络"))
                .error((code, msg) -> alertDlg(mContext,"请重启执法仪。错误代码:"+code+"。信息:"+msg))
                .build().get();
    }

    private VideoInfo getVideoInfoByBundle(Bundle bundle){
        VideoInfo videoInfo = new VideoInfo();
        if (bundle == null) {
            return null;
        }
        videoInfo.setLsh(bundle.getString("cylsh"));
        videoInfo.setClsbdh(bundle.getString("clsbdh"));
        videoInfo.setCyqxh(bundle.getString("cyqxh"));
        videoInfo.setCyqtd(bundle.getString("cyqtd"));
        videoInfo.setJycs(Integer.parseInt(bundle.getString("cycs")));
        videoInfo.setSfzmhm(bundle.getString("sfzmhm"));
        videoInfo.setHphm(bundle.getString("hphm"));
        videoInfo.setHpzl(bundle.getString("hpzl"));
        return videoInfo;
    }


    private void reqRecordStop(String empUrl,String videoUrl,String schema ) {
        Date video_begin = mDbUtils.load(mBundle.getString("cylsh")).getVideo_begin();
        Date video_end = new Date();
        int section =ToolUtils.section(video_end,video_begin);
        String url = empUrl+schema;
        RestClient.builder().url(url).params("section",section).loader(mContext)
                .success(response -> {
                    VideoInfo bundleInfo = getVideoInfoByBundle(mBundle);
                    if (bundleInfo != null) {
                        VideoInfo videoInfo = getResponseInfo(response,bundleInfo);
                        videoInfo.setVideo_begin(video_begin);
                        videoInfo.setVideo_end(video_end);
                        mDbUtils.updateVideo(videoInfo);
                        new AlertDialog.Builder(mContext)
                                .setMessage("执法仪录像结束。现将记录传向服务器,等待中.....")
                                .setPositiveButton("确定", (dialogInterface, i) -> {
                                    uploadVideoInfo(videoUrl,videoInfo);
                                }).show();
                    }else {
                        alertDlg(mContext,"查验终端传递参数非法");
                    }
                })
                .failure(() -> alertDlg(mContext,"网络不通，请重新查验并检查网络"))
                .error((code, msg) -> alertDlg(mContext,"请重启执法仪。错误代码:"+code+"。信息:"+msg))
                .build().post();
    }


    private void reqRecordStopMd(String empUrl,String videoUrl,String action ) {
        Date video_begin = mDbUtils.load(mBundle.getString("cylsh")).getVideo_begin();
        Date video_end = new Date();
        String url = empUrl+action;
        RestClient.builder().url(url).loader(mContext).success(response -> {
                    VideoInfo bundleInfo = getVideoInfoByBundle(mBundle);
                    if (bundleInfo != null) {
                        VideoInfo videoInfo = getResponseInfo(response,bundleInfo);
                        videoInfo.setVideo_begin(video_begin);
                        videoInfo.setVideo_end(video_end);
                        mDbUtils.updateVideo(videoInfo);
                        new AlertDialog.Builder(mContext)
                                .setMessage("执法仪录像结束。现将记录传向服务器,等待中.....")
                                .setPositiveButton("确定", (dialogInterface, i) -> {
                                    uploadVideoInfo(videoUrl,videoInfo);
                                }).show();
                    }else {
                        alertDlg(mContext,"查验终端传递参数非法");
                    }
                })
                .failure(() -> alertDlg(mContext,"网络不通，请重新查验并检查网络"))
                .error((code, msg) -> alertDlg(mContext,"请重启执法仪。错误代码:"+code+"。信息:"+msg))
                .build().post();
    }


    private void uploadVideoInfo(String videoUrl,VideoInfo videoInfo) {
        LatteLogger.json("uploadVideoInfo",JSON.toJSONString(videoInfo));
        RestClient.builder().url(videoUrl).raw(JSON.toJSONString(videoInfo))
                .loader(mContext).success((String response) -> {
            if (!TextUtils.isEmpty(response)) {
                int state = JSON.parseObject(response).getIntValue("state");
                if(state==1){
                    quitDlg(mContext,"视频文件名称已记录");
                    mDbUtils.deleteVideo(mBundle.getString("cylsh"));
                }else {
                    alertDlg(mContext,"视频文件上传失败,原因："+response);
                }
            }
        }).failure(() -> alertDlg(mContext,"网络不通"))
                .error((code, msg) -> alertDlg(mContext,"videoCode="+code+";信息="+msg))
                .build().post();
    }

    public VideoInfo getResponseInfo(String response,VideoInfo videoInfo){
        if(TextUtils.isEmpty(response)){
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        String videoName = jsonObject.getString("filename");
        videoInfo.setVideo_name(videoName);
        videoInfo.setIp(jsonObject.getString("ip"));
        videoInfo.setPort(jsonObject.getString("port"));
        videoInfo.setUri(jsonObject.getString("uri"));
        return  videoInfo;
    }

    public void quitDlg(Context context, String msg){
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    finishAppToCyzd();
                }).show();
    }

    public void alertDlg(Context context,String msg){
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                }).show();
    }

    private void finishAppToCyzd() {
        Intent stopIntent = new Intent();
        stopIntent.putExtra("code","1");
        stopIntent.putExtra("message", "成功");

        getActivity().setResult(20, stopIntent);
        getActivity().finish();
    }

    @Override
    public void post(Runnable runnable) {}
}
