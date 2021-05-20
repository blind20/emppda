package com.tmri.enforcement.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.tmri.enforcement.app.delegate.PluginConnFrm;

public class RequestAction extends ProxyActivity {

    PluginConnFrm mPluginConnFrm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        RestClient.builder().build();
        Latte.getConfigurator().withActivity(this);
    }

    private Bundle putCyInfo(Intent intent){
        Bundle bundle= new Bundle();
        String cylsh = intent.getStringExtra("cylsh");
        String clsbdh = intent.getStringExtra("clsbdh");
        String cyqxh = intent.getStringExtra("cyqxh");
        String cyqtd = intent.getStringExtra("cyqtd");
        String cllx = intent.getStringExtra("cllx");
        String cycs = intent.getStringExtra("cycs");
        String sfzmhm = intent.getStringExtra("sfzmhm");
        String hpzl = intent.getStringExtra("hpzl");
        String hphm = intent.getStringExtra("hphm");
        String keystr = intent.getStringExtra("keystr");
        bundle.putString("cylsh",cylsh);
        bundle.putString("clsbdh",clsbdh);
        bundle.putString("cyqxh",cyqxh);
        bundle.putString("cyqtd",cyqtd);
        bundle.putString("cllx",cllx);
        bundle.putString("cycs",cycs);
        bundle.putString("sfzmhm",sfzmhm);
        bundle.putString("hpzl",hpzl);
        bundle.putString("hphm",hphm);
        bundle.putString("keystr",keystr);
        return bundle;
    }

    @Override
    public LatteDelegate setRootDelegate() {
        mPluginConnFrm = new PluginConnFrm();
        mPluginConnFrm.setArguments(putCyInfo(getIntent()));
        return mPluginConnFrm;
//        return new PluginConnFrm();
    }


    @Override
    public void post(Runnable runnable) {

    }
}
