package com.fzzz.mydemo.net;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-11
 * update:
 */
public interface RemoteService {

    @GET("/toutiao/{type}")
    Call<ResponseBody> getNews(@Path("type") String type);
}
