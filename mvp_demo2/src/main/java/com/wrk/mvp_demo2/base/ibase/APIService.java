package com.wrk.mvp_demo2.base.ibase;

import com.wrk.mvp_demo2.bean.IPHttpResult;
import com.wrk.mvp_demo2.bean.IpInfo;
import com.wrk.mvp_demo2.bean.Movies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MrbigW on 2017/1/13.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: API---接口服务【这里处理的是同一返回格式 resultCode resultInfo Data<T> --->这里的data才是返回的结果，T就是泛型    具体返回的bean对象或集合】
 * -------------------=.=------------------------
 */

public interface APIService {

    /**
     * 查询ip地址信息的接口
     *
     * @param ip 需查询的ip
     * @return RxJava对象
     */
    @GET("service/getIpInfo.php")
    Observable<IPHttpResult<IpInfo>> queryIp(@Query("ip") String ip);


    /**
     * 查询ip地址信息的接口
     * @param start     需查询的ip
     * @param count     RxJava对象
     * @return
     */
    @GET("v2/movie/top250")
    Observable<Movies> getMovies(@Query("start") int start
            , @Query("count") int count);

}
