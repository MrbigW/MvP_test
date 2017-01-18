package com.wrk.mvp_demo2.presenter.movie;

import com.wrk.mvp_demo2.base.ibase.IBasePresenter;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IMoviePresenter extends IBasePresenter {

    void getMovie();    //  获取数据

    void loadMoreMovie();   //  加载更多

}
