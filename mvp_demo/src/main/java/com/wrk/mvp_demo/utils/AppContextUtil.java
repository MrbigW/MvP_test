package com.wrk.mvp_demo.utils;

import android.content.Context;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class AppContextUtil {

    private static Context sContext;

    private AppContextUtil() {

    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getInstance() {
        if (sContext == null) {
            throw new NullPointerException("the context is null,please init AppContextUtil in Application first.");
        }
        return sContext;
    }
}

