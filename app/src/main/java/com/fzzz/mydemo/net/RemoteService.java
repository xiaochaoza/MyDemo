package com.fzzz.mydemo.net;

import com.fzzz.mydemo.bean.NewsReturnBean;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<NewsReturnBean> getNewsPostJson(@Body RequestBody requestBody);
}
