package com.wrk.mvp_demo.helper;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.wrk.mvp_demo.API.PhoneNumInfoService;
import com.wrk.mvp_demo.MyApplication;
import com.wrk.mvp_demo.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MrbigW on 2017/1/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: Retrofit的管理类
 * -------------------=.=------------------------
 */

public class RetrofitManager {

    //  Base地址
    public static final String BASE_PHONENUMINFO_URL = "http://api.k780.com:88";

    //  短缓存有效期为1min
    public static final int CACHE_STALE_SHORT = 60;

    //  短缓存有效期为7day
    public static final int CACHE_STALE_LONG = 60;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //  查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //  查询网络的Cache-Control设置，头部Cache-Control设为max-age=0是则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private static OkHttpClient sOkHttpClient;

    private final PhoneNumInfoService mPhoneNumInfoService;

    public static RetrofitManager builder() {
        return new RetrofitManager();
    }

    public PhoneNumInfoService getPhoneNumInfoService() {
        return mPhoneNumInfoService;
    }

    private RetrofitManager() {
        initOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_PHONENUMINFO_URL)
                .client(sOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPhoneNumInfoService = retrofit.create(PhoneNumInfoService.class);

    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkHttpClient == null) {
                    // 指定缓存路径，缓存大小100Mb
                    Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 1000);

                    sOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(loggingInterceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();

                }
            }
        }
    }

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkConnected()) {
                // 有网络的情况下读取接口上的@Headers里的配置，可以在此进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale-" + CACHE_STALE_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };

}























