package com.wrk.mvp_demo2.base;

import com.wrk.mvp_demo2.base.ibase.IBaseView;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Presenter模块的基类，持有对应的View模块对象
 * -------------------=.=------------------------
 */

public abstract class BasePresenter<M extends BaseModel, V extends IBaseView> {

    protected V mView;
    protected M mModel;


    public BasePresenter(V v) {
        this.mView = v;
        this.mModel = createModel();
    }

    protected abstract M createModel();

}
