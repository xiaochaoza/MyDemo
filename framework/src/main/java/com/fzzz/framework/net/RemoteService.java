package com.fzzz.framework.net;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-11
 * update:
 */
public interface RemoteService {

    @GET("/")
    Call<ResponseBody> baidu();

    @GET("/toutiao/{type}")
    Call<ResponseBody> getNews(@Path("type") String type);

    @GET("/toutiao/index")
    Call<ResponseBody> getNewsGetWithKey(@Query("key") String key);

    @FormUrlEncoded
    @POST("/toutiao/index")
    Call<ResponseBody> getNewsPostWithKey(@Field("key") String key, @Field("type") String type);

    @POST("/toutiao/index")
    Call<ResponseBody> getNewsPostJson(@Body RequestBody requestBody);

    @HTTP(method = "GET", path = "/toutiao/index")
    Observable<ResponseBody> getNewsGet(@Query("key") String key);

    @FormUrlEncoded
    @POST("/toutiao/index")
    Observable<ResponseBody> getNewsPost(@Field("key") String key, @Field("type") String type);

    @POST("/user/add1")
    Observable<ResponseBody> add(@Body RequestBody requestBody);

    @POST("/user/delete")
    Observable<ResponseBody> delete(@Body RequestBody requestBody);

    @POST("/user/update")
    Observable<ResponseBody> update(@Body RequestBody requestBody);

    @POST("/user/findAll")
    Observable<ResponseBody> findAll(@Body RequestBody requestBody);

    @POST("/user/findUserByUserName")
    Observable<ResponseBody> findUserByUserName(@Body RequestBody requestBody);

    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    @Streaming
    @GET("/app.apk")
    Observable<ResponseBody> downloadFile();

}
