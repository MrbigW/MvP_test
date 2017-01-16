package com.wrk.mvp_demo2.base.ibase;

import rx.Observable;
import rx.Subscription;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Model的基类接口
 * -------------------=.=------------------------
 */

public interface IBaseRxJava {

    Observable threadControl(Observable observable);

    Subscription subscribe(Observable observable, final IOnCallBack callBack);

    Subscription rxDeploy(Observable observable, IOnCallBack callback);

    void unSubscribe();
}
