package com.wrk.mvp_demo.base;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 请求数据的回调
 * Presenter用于接收Model获取（加载）数据后的回调
 * -------------------=.=------------------------
 */

public interface IBaseRequestCallBack<T> {

    //  开始请求之前
    void beforeRequest(int requestTag);

    /**
     * 请求失败
     * @param e 请求失败的原因
     * @param requestTag
     */
    void requestError(Throwable e, int requestTag);

    // 请求结束
    void requestComplete(int requestTag);

    /**
     * 请求成功
     * @param callback  根据业务返回相应的数据
     * @param requestTag
     */
    void requestSuccess(T callback, int requestTag);

}
