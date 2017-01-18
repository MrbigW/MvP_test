package com.wrk.mvp_demo2.base.ibase;

import android.content.Context;

import java.util.List;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: View的基类
 * -------------------=.=------------------------
 */

public interface IBaseView<T> {

    Context getCurContext();    //  获取当前的上下文对象

    void toastInfo(String info); //  通过Toast显示信息

    void showDataSuccess(T data);   //  成功显示查询到的数据到界面

    void showListDataSuccess(List<T> datas);    //  成功显示查询到的所有集合

    void showDataError(Throwable throwable);    //  显示查询到的数据到界面失败

    void showProgress();    //  显示进度条对话框

    void hideProgress();    //  隐藏进度条对话框

}
