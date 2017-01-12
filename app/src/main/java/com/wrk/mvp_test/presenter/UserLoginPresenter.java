package com.wrk.mvp_test.presenter;

import android.os.Handler;

import com.wrk.mvp_test.bean.User;
import com.wrk.mvp_test.biz.IUserBiz;
import com.wrk.mvp_test.biz.OnLoginListener;
import com.wrk.mvp_test.biz.UserBiz;
import com.wrk.mvp_test.view.IUserLoginView;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class UserLoginPresenter {

    private IUserBiz mUserBiz;
    private IUserLoginView mUserLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.mUserLoginView = userLoginView;
        this.mUserBiz = new UserBiz();
    }


    public void login() {
        mUserLoginView.showLoading();
        mUserBiz.login(mUserLoginView.getUserName(),
                mUserLoginView.getUserPassword(),
                new OnLoginListener() {
                    @Override
                    public void loginSuccess(final User user) {
                        // 需要在UI线程中执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mUserLoginView.toMainActivity(user);
                                mUserLoginView.hideLoading();
                            }
                        });
                    }

                    @Override
                    public void loginFailed() {
                        // 需要在UI线程中执行
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mUserLoginView.showFaileError();
                                mUserLoginView.hideLoading();
                            }
                        });
                    }
                });
    }

    public void clear() {
        mUserLoginView.clearUserName();
        mUserLoginView.clearUserPassword();
    }

}
