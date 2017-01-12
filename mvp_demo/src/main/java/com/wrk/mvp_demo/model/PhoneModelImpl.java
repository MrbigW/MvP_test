package com.wrk.mvp_demo.model;

import android.content.Context;

import com.wrk.mvp_demo.API.PhoneNumInfoService;
import com.wrk.mvp_demo.base.BaseModel;
import com.wrk.mvp_demo.presenter.PhonePresenterImpl;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MrbigW on 2017/1/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class PhoneModelImpl extends BaseModel {

    private Context mContext;
    private PhoneNumInfoService mPhoneNumInfoService;

    private Subscription mSubscription;

    public PhoneModelImpl(Context context) {
        super();
        mContext = context;
        mPhoneNumInfoService = super.mRetrofitManager.getPhoneNumInfoService();
    }


    public void loadPhoneNumInfo(final String phoneNum, final PhonePresenterImpl callBack, final int requestTag) {
        mSubscription = mPhoneNumInfoService.getBeforeNews("phone.get", phoneNum, "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhoneNumInfo>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        callBack.beforeRequest(requestTag);
                    }

                    @Override
                    public void onCompleted() {
                        callBack.requestComplete(requestTag);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.requestError(e, requestTag);
                    }

                    @Override
                    public void onNext(PhoneNumInfo phoneNumInfo) {
                        if (null != phoneNumInfo && phoneNumInfo.getSuccess().equals("1")) {
                            callBack.requestSuccess(phoneNumInfo, requestTag);
                        } else if (null != phoneNumInfo && phoneNumInfo.getSuccess().equals("0")) {
                            callBack.requestError(new Exception(phoneNumInfo.getMsg()), requestTag);
                        } else {
                            callBack.requestError(new Exception("获取数据错误，请重试"), requestTag);
                        }
                    }
                });
    }

    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }


}
