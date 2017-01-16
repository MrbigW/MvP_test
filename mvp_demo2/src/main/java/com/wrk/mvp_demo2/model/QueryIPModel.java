package com.wrk.mvp_demo2.model;

import android.content.Context;

import com.wrk.mvp_demo2.base.BaseModel;
import com.wrk.mvp_demo2.base.GlobalField;
import com.wrk.mvp_demo2.base.ibase.APIService;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.IPHttpResult;
import com.wrk.mvp_demo2.bean.IpInfo;
import com.wrk.mvp_demo2.http.RetrofitUtils;

import java.util.regex.Pattern;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class QueryIPModel extends BaseModel implements IQueryIPModel {

    @Override
    public void queryIp(String ip, IOnCallBack<IPHttpResult<IpInfo>> callBack) {


        rxDeploy(RetrofitUtils.newInstance(GlobalField.IP_QUERY_URL)
                .create(APIService.class)
                .queryIp(ip), callBack);

    }

    @Override
    public boolean isIp(String ip) {
        String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return Pattern.compile(regex).matcher(ip).matches();
    }

    @Override
    public String getUserInfo(Context context) {
        return "wrk-----yeah!";
    }



}
