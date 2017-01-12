package com.wrk.mvp_demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by MrbigW on 2017/1/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 对当前网路连接的判断
 * -------------------=.=------------------------
 */

public class NetUtil {

    //  判断是否有网络连接
    public static boolean isNetworkConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager manager = (ConnectivityManager) AppContextUtil.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    //  判断是否为Wifi连接
    public static boolean isWifiConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager manager = (ConnectivityManager) AppContextUtil.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    // 判断是否移动网络
    public static boolean isMobileConnected() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager manager = (ConnectivityManager) AppContextUtil.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

    // 判断当前网络属于什么网络，返回当前网络的类型
    public static int getConnectedType() {
        if (AppContextUtil.getInstance() != null) {
            ConnectivityManager manager = (ConnectivityManager) AppContextUtil.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                return info.getType();
            }
        }
        return -1;
    }

}













