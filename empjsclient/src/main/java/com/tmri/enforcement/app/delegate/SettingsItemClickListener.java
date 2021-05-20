package com.tmri.enforcement.app.delegate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.flj.latte.util.log.LatteLogger;
import com.tmri.enforcement.app.R;
import com.tmri.enforcement.app.common.Constants;
import com.tmri.enforcement.app.common.ToolUtils;


public class SettingsItemClickListener extends SimpleClickListener {
    private Context mContext;
    private LatteDelegate mDelegate;

    public SettingsItemClickListener(LatteDelegate delegate, Context context){
        mContext = context;
        mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final SimpleListBean entity = (SimpleListBean) adapter.getData().get(position);
        switch (position){
            case 0:
                editAlertDlg(view, entity, "执法记录仪IP地址",Constants.PREFS_EMP_SERVICE_IP);
                break;
            case 1:
                editAlertDlg(view, entity, "执法记录仪端口",Constants.PREFS_EMP_SERVICE_PORT);
                break;
            case 2:
                editAlertDlg(view, entity, "查验视频服务器IP",Constants.PREFS_VIDEO_SYSTEM_IP);
                break;
            case 3:
                editAlertDlg(view, entity, "查验视频服务器端口",Constants.PREFS_VIDEO_SYSTEM_PORT);
                break;
            case 4:
                downloadApk();
                break;
            case 5:
                selectFactory(view,entity);
                break;
            default:
                break;
        }
    }

    private void selectFactory(View view, SimpleListBean entity) {
        String selectItem = entity.getItemValue();
        String[] items = mContext.getResources().getStringArray(R.array.factory);
        int checkItem=0;
        if(selectItem.equals(items[0])){
            checkItem=0;
        }else if(selectItem.equals(items[1])){
            checkItem=1;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==2){
                    SharedPreferenceUtils.put(mContext,Constants.PREFS_FACTORY_ID,Constants.FACTORY_JINGSHENG);
                }else if(which==1){
                    SharedPreferenceUtils.put(mContext,Constants.PREFS_FACTORY_ID,Constants.FACTORY_MINGDU);
                }else if(which==0){
                    SharedPreferenceUtils.put(mContext,Constants.PREFS_FACTORY_ID,Constants.FACTORY_HIK);
                }
                final TextView textView = view.findViewById(R.id.tv_item_value);
                textView.setText(items[which]);
                entity.setItemValue(items[which]);
                dialog.cancel();
            }
        }).show();
    }

    private void editAlertDlg(View view, SimpleListBean entity, String title,String prefKey) {
        final EditText et = new EditText(mContext);
        String IP = entity.getItemValue();
        if (!TextUtils.isEmpty(IP)) {
            et.setText(IP);
        }
        et.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        final AlertDialog.Builder dlg = new AlertDialog.Builder(mContext);
        dlg.setTitle(title).setView(et)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    String txt = et.getText().toString().trim();
                    final TextView textView = view.findViewById(R.id.tv_item_value);
                    textView.setText(txt);
                    entity.setItemValue(txt);
                    SharedPreferenceUtils.put(mContext, prefKey, txt);
                }).setNegativeButton("取消", null).show();
    }


    private void downloadApk()  {
        String ip = (String)SharedPreferenceUtils.get(mContext,Constants.PREFS_VIDEO_SYSTEM_IP,"");
        String port = (String) SharedPreferenceUtils.get(mContext,Constants.PREFS_VIDEO_SYSTEM_PORT,"");
        if(TextUtils.isEmpty(ip)||TextUtils.isEmpty(port)){
            ToastUtils.showLong("请先配置系统IP及端口");
            return;
        }
        String apkUrl = "http://"+ip + ":"+ port+"/cmsvideo/static/cache/apk/empjsclient-release.apk";
        String jsonUrl ="http://"+ip + ":"+ port+"/"+"cmsvideo/static/cache/apk/update_apk.json";
        LatteLogger.e("success","jsonUrl="+jsonUrl);
        RestClient.builder().url(jsonUrl)
                .loader(mContext).success(response -> {
            LatteLogger.e("success","success="+response);
            int localVersion = ToolUtils.getVersionCode(mContext);
            if(!TextUtils.isEmpty(response)){
                String replace = response.replaceAll("\\s*", "");
                JSONObject jo = JSON.parseObject(replace);
                int remoteVersion =jo.getInteger("version");
                if(localVersion>=remoteVersion){
                    ToastUtils.showLong("已经是最新版本");
                }else {
                    AllenVersionChecker.getInstance()
                            .downloadOnly(crateUIData(apkUrl))
                            .excuteMission(mContext);
                }
            }
        }).error((code, msg) -> LatteLogger.e("onError","onError="+code+";msg"+msg))
                .failure(() -> LatteLogger.e("onFailure","onFailure="))
                .build().get();
    }

    private UIData crateUIData(String downloadUrl) {
        UIData uiData = UIData.create();
        uiData.setTitle("翔尚插件更新");
        uiData.setDownloadUrl(downloadUrl);
        uiData.setContent("翔尚插件App更新到新版本");
        return uiData;
    }


    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
