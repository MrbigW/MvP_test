package com.wrk.mvp_demo2.presenter.movie;

import com.wrk.mvp_demo2.base.BasePresenter;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.Movies;
import com.wrk.mvp_demo2.model.movie.MovieModel;
import com.wrk.mvp_demo2.view.movie.IMovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MoviePresenter extends BasePresenter<MovieModel, IMovieView> implements IMoviePresenter {

    public int start = 0;   //  从第几个开始
    public int count = 8;   //  请求多少个

    List<Movies.SubjectsBean> mMovies = new ArrayList<>();    //  请求到的电影信息对象集合

    public MoviePresenter(IMovieView iMovieView) {
        super(iMovieView);
    }

    @Override
    protected MovieModel createModel() {
        return new MovieModel();
    }

    @Override
    public void getMovie() {
        mView.showProgress();   //  通知V层显示对话框
        //  每次刷新加载4个
        mModel.getMovie(start, count, new IOnCallBack<Movies>() {
            @Override
            public void onSuccessful(Movies movies) {   //  获取电影信息成功了，返回movies对象
                mView.hideProgress();
                mMovies.addAll(movies.getSubjects());
                mView.showListDataSuccess(mMovies);
                mView.showBottom(start - 5);
            }

            @Override
            public void onFailed(String errorMsg) {
                mView.hideProgress();
                mView.toastInfo(errorMsg);
            }
        });
        start += 4;     //改变请求的起点
    }

    @Override
    public void loadMoreMovie() {
        getMovie();
    }

    @Override
    public void onDestroy() {
        mModel.unSubscribe();
    }
}
