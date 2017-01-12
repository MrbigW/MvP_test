package com.wrk.mvp_best.presenter;

import com.wrk.mvp_best.model.biz.IModel;
import com.wrk.mvp_best.model.biz.Model;
import com.wrk.mvp_best.view.IView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Presenter的实现
 * Presenter通过以下两点来解耦MV两层，最好不要有其他责任
 *  1.整理并保存线程逻辑
 *  2.从Model获取Model层的POJO转成View层的POJO或者基本数据元素，注意前后两个POJO必须是不同的。
 *  View不能直接使用Model层的POJO
 * -------------------=.=------------------------
 */

public class Presenter implements IPresenter {
    //  拥有View与Model
    private IView mIView;
    private IModel mIModel;

    public Presenter(IView view) {
        this.mIView = view;
        this.mIModel = new Model();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void perfromOnClick() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String data = mIModel.getData();
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        mIView.setData(s + " from presenter");
                    }
                });
    }

}
