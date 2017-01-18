package com.wrk.mvp_demo2.view.movie;

import com.wrk.mvp_demo2.base.ibase.IBaseView;
import com.wrk.mvp_demo2.bean.Movies;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public interface IMovieView extends IBaseView<Movies.SubjectsBean> {

    void showBottom(int lastIndex);

}
