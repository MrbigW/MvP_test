package com.wrk.mvp_demo.base;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IBasePresenter {

    /**
     * 开始
     * 用于做一些初始化的操作
     */
    void onResume();

    /**
     * 销毁
     * 用于做一些销毁，回收等类型的操作
     */
    void onDestroy();

}
