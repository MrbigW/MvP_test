package com.wrk.mvp_demo2.model.movie;

import com.wrk.mvp_demo2.base.ibase.IBaseRxJava;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.Movies;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IMovieMedel extends IBaseRxJava {

    void getMovie(int start, int count, IOnCallBack<Movies> callBack);

}
