package com.wrk.mvp_demo2.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Retrofit网络请求工具类
 * -------------------=.=------------------------
 */

public class RetrofitUtils {

    private static final int READ_TIMEOUT = 60; //  读取超时时间，单位   秒
    private static final int CONN_TIMEOUT = 12; //  连接超时时间，单位   秒

    private static Retrofit sRetrofit;
    private static OkHttpClient sOkhttpClient;


    public static Retrofit newInstance(String url) {
        sRetrofit = null;
        initOkHttpClient();

        sRetrofit = new Retrofit.Builder()
                .client(sOkhttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return sRetrofit;
    }

    private static void initOkHttpClient() {
        sOkhttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

    }


}




























