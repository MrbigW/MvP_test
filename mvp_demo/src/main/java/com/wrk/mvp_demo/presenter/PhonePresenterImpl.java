package com.wrk.mvp_demo.presenter;

import android.content.Context;

import com.wrk.mvp_demo.base.BasePresenterImpl;
import com.wrk.mvp_demo.model.PhoneModelImpl;
import com.wrk.mvp_demo.model.PhoneNumInfo;
import com.wrk.mvp_demo.view.PhoneNumInfoView;

/**
 * Created by MrbigW on 2017/1/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class PhonePresenterImpl extends BasePresenterImpl<PhoneNumInfoView, PhoneNumInfo> {

    private PhoneModelImpl mPhoneModel;
    private Context mContext;

    public PhonePresenterImpl(PhoneNumInfoView view, Context context) {
        super(view);
        this.mContext = context;
        mPhoneModel = new PhoneModelImpl(mContext);
    }

    public void getPhoneNumInfo(String phoneNum,int requestTag){
        onResume();
//        beforeRequest(requestTag);
        mPhoneModel.loadPhoneNumInfo(phoneNum,this,requestTag);
    }

    public void unPhoneNumSubscribe(){
       mPhoneModel.unSubscribe();
    }

}














