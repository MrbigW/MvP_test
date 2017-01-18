package com.wrk.mvp_demo2.base;

import com.socks.library.KLog;
import com.wrk.mvp_demo2.base.ibase.IBaseRxJava;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Model基类
 * -------------------=.=------------------------
 */

public class BaseModel implements IBaseRxJava {

    private Subscription mSubscription;

    //  Rxjava线程控制
    @Override
    public Observable threadControl(Observable observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Subscription subscribe(Observable observable, final IOnCallBack callBack) {
        return observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (null != callBack) {
                    if (e instanceof HttpException) {
                        HttpException httpException = (HttpException) e;
                        int code = httpException.code();
                        if (code == 500 || code == 404) {
                            callBack.onFailed("服务器出错！");
                        }
                    } else if (e instanceof ConnectException) {
                        callBack.onFailed("网络断开，请打开网络！");
                    } else if (e instanceof SocketTimeoutException) {
                        callBack.onFailed("网络连接超时！");
                    } else {
                        callBack.onFailed("发送未知错误：" + e.getMessage());
                        KLog.e("MyLog", e.getMessage());
                    }
                }
            }

            @Override
            public void onNext(Object o) {
                if (null != callBack) {
                    callBack.onSuccessful(o);
                }
            }
        });
    }

    @Override
    public Subscription rxDeploy(Observable observable, IOnCallBack callback) {
        mSubscription = subscribe(threadControl(observable), callback);
        return mSubscription;
    }

    @Override
    public void unSubscribe() {
        if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
