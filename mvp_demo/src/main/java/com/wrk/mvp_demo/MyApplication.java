package com.wrk.mvp_demo;

import android.app.Application;
import android.content.Context;

import com.wrk.mvp_demo.utils.AppContextUtil;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MyApplication extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContextUtil.init(this);

        mApplicationContext = this;
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }

}
