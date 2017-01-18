package com.wrk.mvp_demo2.model.movie;

import com.wrk.mvp_demo2.base.BaseModel;
import com.wrk.mvp_demo2.base.GlobalField;
import com.wrk.mvp_demo2.base.ibase.APIService;
import com.wrk.mvp_demo2.base.ibase.IOnCallBack;
import com.wrk.mvp_demo2.bean.Movies;
import com.wrk.mvp_demo2.http.RetrofitUtils;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MovieModel extends BaseModel implements IMovieMedel {
    @Override
    public void getMovie(int start, int count, IOnCallBack<Movies> callBack) {
        rxDeploy(RetrofitUtils.newInstance(GlobalField.MOVIE_TOP250_URL)
                .create(APIService.class)
                .getMovies(start, count), callBack);
    }
}
