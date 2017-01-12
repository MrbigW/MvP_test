package com.wrk.mvp_demo.base;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: View的基类
 * -------------------=.=------------------------
 */

public interface IBaseView<T> {

    /**
     * 通过Toast提示用户
     * @param msg   提示的信息
     * @param requestTag    请求的标识
     */
    void toast(String msg,int requestTag);

    /**
     * 显示进度
     * @param requestTag    请求标识
     */
    void showProgress(int requestTag);

    /**
     * 隐藏进度
     * @param requestTag    请求标识
     */
    void hideProgress(int requestTag);

    /**
     * 基础的请求的返回
     * @param data  数据
     * @param requestTag    请求标识
     */
    void loadDataSuccess(T data,int requestTag);

    /**
     * 基础请求的错误
     * @param e 异常
     * @param requestTag    请求标识
     */
    void loadDataError(Throwable e,int requestTag);

}
