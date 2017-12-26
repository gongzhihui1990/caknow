package com.caknow.android;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;

import net.gtr.framework.app.BaseApp;

/**
 * Created by caroline on 2017/12/10.
 */

public class CaknowApplication extends BaseApp {

    @Override
    protected void attachBaseContext(Context base) {
        //解决分包问题
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
