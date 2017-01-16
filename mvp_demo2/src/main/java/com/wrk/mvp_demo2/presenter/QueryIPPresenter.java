package com.wrk.mvp_demo2.presenter;

import com.socks.library.KLog;
import com.wrk.mvp_demo2.base.BasePresenter;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.IPHttpResult;
import com.wrk.mvp_demo2.bean.IpInfo;
import com.wrk.mvp_demo2.model.QueryIPModel;
import com.wrk.mvp_demo2.view.IQueryIPView;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class QueryIPPresenter extends BasePresenter<QueryIPModel, IQueryIPView> implements IQueryIPPresenter {


    public QueryIPPresenter(IQueryIPView IQueryIPView) {
        super(IQueryIPView);
    }

    @Override
    protected QueryIPModel createModel() {
        return new QueryIPModel();
    }

    @Override
    public void showInfo() {
        mView.toastInfo(mModel.getUserInfo(mView.getCurContext()));
    }

    @Override
    public void queryIp() {
        final String ip = mView.getInputIp();
        if (mModel.isIp(ip)) {   //  判断是否合法的ip地址
            mView.showProgress();
            mModel.queryIp(ip, new IOnCallBack<IPHttpResult<IpInfo>>() {
                @Override
                public void onSuccessful(IPHttpResult<IpInfo> ipInfo) {   //查询成功的回调
                    mView.hideProgress();
                    mView.showDataSuccess(ipInfo);
                    KLog.e("查询到了：" + ipInfo.toString());
                }

                @Override
                public void onFaild(String errorMsg) {  //  查询失败的回调
                    mView.hideProgress();
                    KLog.e(errorMsg);
                    mView.toastInfo(errorMsg);
                }
            });
        } else {
            mView.toastInfo("ip地址格式错误！！！");
        }
    }


    @Override
    public void onDestroy() {

    }
}
