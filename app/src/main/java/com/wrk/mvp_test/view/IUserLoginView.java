package com.wrk.mvp_test.view;

import com.wrk.mvp_test.bean.User;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Presenter与View交互是通过此接口
 *
 * 对于View的接口，去观察功能上的操作，然后考虑：
 *该操作需要什么？（getUserName, getPassword）
 * 该操作的结果，对应的反馈？(toMainActivity, showFailedError)
 * 该操作过程中对应的友好的交互？(showLoading, hideLoading)
 *
 * -------------------=.=------------------------
 */

public interface IUserLoginView {

    //  得到用户名和密码
    String getUserName();
    String getUserPassword();

    //  清空用户名和密码
    void clearUserName();
    void clearUserPassword();

    //  显示和隐藏进度
    void showLoading();
    void hideLoading();

    //  登陆成功与失败的处理
    void toMainActivity(User user);
    void showFaileError();

}
