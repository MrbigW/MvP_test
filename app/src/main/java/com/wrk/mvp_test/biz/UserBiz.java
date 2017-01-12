package com.wrk.mvp_test.biz;

import com.wrk.mvp_test.bean.User;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        //  模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 模拟登陆成功
                if ("wrk".equals(username) && "123".equals(password)) {

                    User user = new User();
                    user.setPassword(password);
                    user.setUsername(username);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }

            }
        }.start();
    }
}
