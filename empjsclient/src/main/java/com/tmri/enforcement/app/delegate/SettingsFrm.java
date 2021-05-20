package com.tmri.enforcement.app.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.tmri.enforcement.app.R;
import com.tmri.enforcement.app.R2;
import com.tmri.enforcement.app.common.Constants;
import com.tmri.enforcement.app.common.ToolUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsFrm extends LatteDelegate {

    @BindView(R2.id.rv_setting)
    RecyclerView mRecyclerView = null;
    Context mContext;
    @Override
    public Object setLayout() {
        return R.layout.frm_setting;
    }

    @OnClick(R2.id.tv_back)
    void onClickBack(View view){
        getSupportDelegate().pop();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = this.getContext();
        setListView();
    }

    private void setListView(){
        final List<SimpleListBean> datas = new ArrayList<>();
        String[] items_left = getContext().getResources().getStringArray(R.array.setting_items);
        for (int i = 0; i <items_left.length ; i++) {
            SimpleListBean bean = new SimpleListBean.Builder()
                    .setItemType(ItemType.ITEM_SIMPLE_LIST_2)
                    .setText(items_left[i])
                    .build();
            switch (i){
                case 0:
                    String empIp = (String) SharedPreferenceUtils.get(mContext,Constants.PREFS_EMP_SERVICE_IP,"");
                    bean.setItemValue(empIp);
                    break;
                case 1:
                    String empport = (String) SharedPreferenceUtils.get(mContext,Constants.PREFS_EMP_SERVICE_PORT,"");
                    bean.setItemValue(empport);
                    break;
                case 2:
                    String IP = (String) SharedPreferenceUtils.get(mContext,Constants.PREFS_VIDEO_SYSTEM_IP,"");
                    bean.setItemValue(IP);
                    break;
                case 3:
                    String videoport = (String) SharedPreferenceUtils.get(mContext,Constants.PREFS_VIDEO_SYSTEM_PORT,"");
                    bean.setItemValue(videoport);
                    break;
                case 4:
                    String version = ToolUtils.getVersionName(mContext);
                    bean.setItemValue(version);
                    break;
                case 5:
                    String[] items = mContext.getResources().getStringArray(R.array.factory);
                    int factory = (int) SharedPreferenceUtils.get(mContext,Constants.PREFS_FACTORY_ID,30);
                    if(factory==Constants.FACTORY_JINGSHENG){
                        bean.setItemValue(items[2]);
                    }else if(factory==Constants.FACTORY_MINGDU){
                        bean.setItemValue(items[1]);
                    }else if(factory==Constants.FACTORY_HIK){
                        bean.setItemValue(items[0]);
                    }

                    break;
            }
            datas.add(bean);
        }
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        final SimpleListAdapter adapter = new SimpleListAdapter(datas,ItemType.ITEM_SIMPLE_LIST_2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsItemClickListener(this,mContext));
    }

    @Override
    public void post(Runnable runnable) {
    }
}
