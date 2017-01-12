package com.wrk.mvp_test.biz;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IUserBiz {

    void login(String username, String password, OnLoginListener loginListener);

}
