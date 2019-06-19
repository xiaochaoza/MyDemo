package com.fzzz.design_mode.proxy.dynamic_proxy;


import io.reactivex.Observable;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-19
 * update:
 */
public interface ApiService {
    @POST("http://www.baidu.com/login")
    Observable<User> login(@Query("username") String username, @Query("password") String password);

    @GET("http://www.baidu.com/checkupdate")
    Observable<CheckUpdate> checkUpdate(@Query("version") String version);

}
