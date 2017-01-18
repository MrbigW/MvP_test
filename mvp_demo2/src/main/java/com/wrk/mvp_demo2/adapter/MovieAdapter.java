package com.wrk.mvp_demo2.adapter;

import android.content.Context;

import com.wrk.mvp_demo2.bean.Movies;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by MrbigW on 2017/1/17.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class MovieAdapter extends CommonAdapter<Movies.SubjectsBean> {

    public MovieAdapter(Context context, int layoutId, List<Movies.SubjectsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {

    }
}
