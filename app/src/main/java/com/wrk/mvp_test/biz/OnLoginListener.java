package com.wrk.mvp_test.biz;

import com.wrk.mvp_test.bean.User;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();

}
