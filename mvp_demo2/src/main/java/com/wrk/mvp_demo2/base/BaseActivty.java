package com.wrk.mvp_demo2.base;

import android.app.Activity;
import android.os.Bundle;

import com.wrk.mvp_demo2.utils.ZTLUtils;

/**
 * Created by MrbigW on 2017/1/16.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class BaseActivty extends Activity {

    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mActivity = this;

        new ZTLUtils(mActivity).setTranslucentStatus();

    }
}
