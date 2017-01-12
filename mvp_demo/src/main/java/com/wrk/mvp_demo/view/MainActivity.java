package com.wrk.mvp_demo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wrk.mvp_demo.R;
import com.wrk.mvp_demo.model.PhoneNumInfo;
import com.wrk.mvp_demo.presenter.PhonePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PhoneNumInfoView {

    @BindView(R.id.et_main_number)
    EditText etMainNumber;
    @BindView(R.id.btn_main_get)
    Button btnMainGet;
    @BindView(R.id.tv_main_result)
    TextView tvMainResult;

    // Loading弹窗
    private ProgressDialog mDialog;
    // 获取归属地的代理对象
    private PhonePresenterImpl mPhonePresenter;

    //  获取归属地的请求标识，
    private static final int REQUEESTMSG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initPresenter();
    }

    private void initPresenter() {
        //  初始化Presenter对象
        mPhonePresenter = new PhonePresenterImpl(this, this);
    }

    private void initView() {
        //  初始化Loading
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading");
    }

    @OnClick(R.id.btn_main_get)
    public void onClick() {
        mPhonePresenter.getPhoneNumInfo(etMainNumber.getText().toString(), REQUEESTMSG);
    }

    @Override
    public void toast(String msg, int requestTag) {

    }

    @Override
    public void showProgress(int requestTag) {
        if (null != mDialog && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void hideProgress(int requestTag) {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(PhoneNumInfo data, int requestTag) {
        tvMainResult.setText(data.toString());
    }

    @Override
    public void loadDataError(Throwable e, int requestTag) {
        tvMainResult.setText(e.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhonePresenter.unPhoneNumSubscribe();
    }
}
