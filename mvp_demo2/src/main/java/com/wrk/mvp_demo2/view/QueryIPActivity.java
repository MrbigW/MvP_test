package com.wrk.mvp_demo2.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wrk.mvp_demo2.R;
import com.wrk.mvp_demo2.base.BaseActivty;
import com.wrk.mvp_demo2.bean.IPHttpResult;
import com.wrk.mvp_demo2.bean.IpInfo;
import com.wrk.mvp_demo2.presenter.QueryIPPresenter;
import com.wrk.mvp_demo2.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrbigW on 2017/1/16.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class QueryIPActivity extends BaseActivty implements IQueryIPView {


    QueryIPPresenter mQueryIPPresenter;

    ProgressDialog mProgressDialog;

    @BindView(R.id.et_ip_input)
    EditText etIpInput;
    @BindView(R.id.btn_ip_query)
    Button btnIpQuery;
    @BindView(R.id.tv_ip_input)
    TextView tvIpInput;
    @BindView(R.id.tv_ip_country)
    TextView tvIpCountry;
    @BindView(R.id.tv_ip_area)
    TextView tvIpArea;
    @BindView(R.id.tv_ip_province)
    TextView tvIpProvince;
    @BindView(R.id.tv_ip_city)
    TextView tvIpCity;
    @BindView(R.id.tv_ip_isp)
    TextView tvIpIsp;
    @BindView(R.id.btn_ip_movie)
    Button btnIpMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        mQueryIPPresenter = new QueryIPPresenter(this);
        mQueryIPPresenter.showInfo();
    }

    private void initView() {
        setContentView(R.layout.activity_ip);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading…………");
    }

    @Override
    public String getInputIp() {
        return etIpInput.getText().toString();
    }

    @Override
    public Context getCurContext() {
        return mActivity;
    }

    @Override
    public void toastInfo(String info) {
        ToastUtils.showToast(mActivity, info);
    }

    @Override
    public void showDataSuccess(Object data) {
        IPHttpResult<IpInfo> ipInfo = (IPHttpResult<IpInfo>) data;
        tvIpInput.setText(ipInfo.getData().getIp());
        tvIpArea.setText(ipInfo.getData().getArea());
        tvIpCity.setText(ipInfo.getData().getCity());
        tvIpCountry.setText(ipInfo.getData().getCountry());
        tvIpProvince.setText(ipInfo.getData().getRegion());
        tvIpIsp.setText(ipInfo.getData().getIsp());

    }

    @Override
    public void showDataError(Throwable throwable) {
        ToastUtils.showToast(mActivity, throwable.getMessage());
    }

    @Override
    public void showProgress() {
        if (null != mProgressDialog && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @OnClick({R.id.btn_ip_query, R.id.btn_ip_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ip_query:
                mQueryIPPresenter.queryIp();
                break;
            case R.id.btn_ip_movie:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }

        if (null != mQueryIPPresenter) {
            mQueryIPPresenter.onDestroy();
        }

    }
}
