package com.wrk.mvp_demo.base;

/**
 * Created by MrbigW on 2017/1/11.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 代理对象的基础实现
 * -------------------=.=------------------------
 */

/**
 * @param <T>   视图接口对象（View）具体业务各自继承自IBaseView
 * @param <V>   业务请求返回的具体对象
 */
public class BasePresenterImpl<T extends IBaseView, V> implements IBasePresenter, IBaseRequestCallBack<V> {

    public IBaseView mIBaseView;

    public BasePresenterImpl(T view) {
        this.mIBaseView = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void beforeRequest(int requestTag) {
        //  显示Loading
        mIBaseView.showProgress(requestTag);
    }

    @Override
    public void requestError(Throwable e, int requestTag) {
        //  通知UI具体的错误信息
        mIBaseView.loadDataError(e,requestTag);
    }

    @Override
    public void requestComplete(int requestTag) {
        // 隐藏Loading
        mIBaseView.hideProgress(requestTag);
    }

    @Override
    public void requestSuccess(V callback, int requestTag) {
        // 将获取的数据回调给UI（activity或者fragment）
        mIBaseView.loadDataSuccess(callback,requestTag);
        mIBaseView.hideProgress(requestTag);
    }

}
