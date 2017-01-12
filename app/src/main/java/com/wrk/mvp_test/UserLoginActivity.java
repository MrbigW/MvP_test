package com.wrk.mvp_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wrk.mvp_test.bean.User;
import com.wrk.mvp_test.presenter.UserLoginPresenter;
import com.wrk.mvp_test.view.IUserLoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {

    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.ll_login_name)
    LinearLayout llLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.ll_login_password)
    LinearLayout llLoginPassword;
    @BindView(R.id.btn_main_login)
    Button btnMainLogin;
    @BindView(R.id.btn_main_clear)
    Button btnMainClear;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.pb_main_login)
    ProgressBar pbMainLogin;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_main_login, R.id.btn_main_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_login:
                mUserLoginPresenter.login();
                break;
            case R.id.btn_main_clear:
                mUserLoginPresenter.clear();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etLoginName.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return etLoginPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        etLoginName.setText("");
    }

    @Override
    public void clearUserPassword() {
        etLoginPassword.setText("");
    }

    @Override
    public void showLoading() {
        pbMainLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbMainLogin.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, "login success,to MainActivity...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFaileError() {
        Toast.makeText(this, "login failed,try again...", Toast.LENGTH_SHORT).show();
    }



}
