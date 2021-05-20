package com.tmri.enforcement.app;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.flj.latte.app.Latte;
import com.flj.latte.net.interceptors.LoggingInterceptor;
import com.tmri.enforcement.app.common.Constants;
import com.tmri.enforcement.app.db.DbManager;

public class PluginApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withLoaderDelayed(1000)
                .withApiHost(Constants.BASE_URL)
                .withInterceptor(new LoggingInterceptor())
                .configure();
        initStetho();
        DbManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }


}
