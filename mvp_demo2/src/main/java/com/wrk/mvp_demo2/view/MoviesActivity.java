package com.wrk.mvp_demo2.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wrk.mvp_demo2.R;
import com.wrk.mvp_demo2.base.BaseActivty;
import com.wrk.mvp_demo2.bean.Movies;
import com.wrk.mvp_demo2.presenter.movie.MoviePresenter;
import com.wrk.mvp_demo2.utils.ToastUtils;
import com.wrk.mvp_demo2.view.movie.IMovieView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MoviesActivity extends BaseActivty implements IMovieView {


    private MoviePresenter mMoviePresenter;

    private TextView load_more; //加载更多

    private ProgressDialog mProgressDialog;

    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @BindView(R.id.store_house_ptr_frame)
    PtrFrameLayout storeHousePtrFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        mMoviePresenter = new MoviePresenter(this);
        mMoviePresenter.getMovie();     //  启动软件时默认加载
    }

    private void initView() {
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading…………");

        initPtr();
        rvMovieList.setLayoutManager(new LinearLayoutManager(mActivity));   //设置为线性的布局
        rvMovieList.setItemAnimator(new DefaultItemAnimator());     //  设置动画
        rvMovieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    /**
     * 初始化（配置）下拉刷新控件
     */
    private void initPtr() {
        //下面是一些基础的配置,直接拿来用就可以 不用深究
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(1000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
        StoreHouseHeader header = new StoreHouseHeader(this);
        float scale = getResources().getDisplayMetrics().density;
        header.setPadding(0, (int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f));
        header.initWithString("WRK");//自定义头显示的字样,设置图片的话看另外的api
        header.setTextColor(Color.BLACK);
        header.setBackgroundColor(Color.parseColor("#11000000"));
        storeHousePtrFrame.setHeaderView(header);//添加头
        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mMoviePresenter.loadMoreMovie();//下拉刷新的时候加载更多数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
            }
        });
    }

    @Override
    public void showBottom(int lastIndex) {
        load_more.setText("加载更多");
        rvMovieList.scrollToPosition(lastIndex);
    }

    @Override
    public Context getCurContext() {
        return this;
    }

    @Override
    public void toastInfo(String info) {
        ToastUtils.showToast(this, info);
    }

    @Override
    public void showDataSuccess(Movies.SubjectsBean data) {
    }

    @Override
    public void showListDataSuccess(List<Movies.SubjectsBean> datas) {
        // 鸿洋大神的通用适配器
        CommonAdapter<Movies.SubjectsBean> commonAdapter = new CommonAdapter<Movies.SubjectsBean>(this, R.layout.movie_item, datas) {
            @Override
            protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
                String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
                holder.setText(R.id.tv_movie_title, title);//设置电影名
                String doc = "";
                for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
                    doc += directorsBean.getName() + "  ";
                }
                holder.setText(R.id.tv_movie_doc, "导演:" + doc);
                String casts = "";
                for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
                    casts += castsBean.getName() + "  ";
                }

                holder.setText(R.id.tv_movie_art, "主演:" + casts);
                String genres = "";
                for (String genre : subjectsBean.getGenres()) {
                    genres += genre + " ";
                }
                holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
                holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
                ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
                Glide.with(mActivity)
                        .load(subjectsBean.getImages().getSmall())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
                        .into(iv_pic);//图片
            }
        };

        /**
         * 配置加载更多（通用适配器里的类）
         */
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
        View view = View.inflate(mActivity, R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_more.setText("加载中...");//点击加载更多-->加载中
                mMoviePresenter.loadMoreMovie();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦
    }

    @Override
    public void showDataError(Throwable throwable) {

    }

    @Override
    public void showProgress() {
        if (null != mProgressDialog && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mProgressDialog) {
            mProgressDialog = null;
        }

        if (null != mMoviePresenter) {
            mMoviePresenter.onDestroy();
            mMoviePresenter = null;
        }

    }
}





