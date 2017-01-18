package com.wrk.mvp_demo2.base.ibase;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 公共的请求回调监听器
 * -------------------=.=------------------------
 */
public interface IOnCallBack<T> {

    void onSuccessful(T t); //  成功了回调此方法，可以传递任何形式的数据给调用者

    void onFailed(String errorMsg);  //  失败了就调用这个方法，可以传递错误的请求信息给调用者

}
