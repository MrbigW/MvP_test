package com.wrk.mvp_demo.base;

import com.wrk.mvp_demo.helper.RetrofitManager;

/**
 * Created by MrbigW on 2017/1/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 业务对象的基类
 * -------------------=.=------------------------
 */

public class BaseModel {

    // retrofit请求数据的管理类
    public RetrofitManager mRetrofitManager;

    public BaseModel() {
        // 初始化retrofit
        mRetrofitManager = RetrofitManager.builder();
    }
}


