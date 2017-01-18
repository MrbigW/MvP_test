package com.wrk.mvp_demo2.model.ip;

import android.content.Context;

import com.wrk.mvp_demo2.base.ibase.IBaseRxJava;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.IPHttpResult;
import com.wrk.mvp_demo2.bean.IpInfo;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IQueryIPModel extends IBaseRxJava {

    void queryIp(String ip, IOnCallBack<IPHttpResult<IpInfo>> callBack);//查询ip地址

    boolean isIp(String ip);//判断是否是正确的ip地址

    String getUserInfo(Context context);    //获取已经保存在sp中的用户信息

}
