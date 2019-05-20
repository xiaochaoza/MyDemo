package com.fzzz.mydemo.helper;

import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.net.RemoteService;
import com.fzzz.mydemo.ui.okhttp.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-14
 * update:
 */
public class RetrofitHelper {
    private static RemoteService remoteService;

    private RetrofitHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_JUHE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        remoteService = retrofit.create(RemoteService.class);
    }

    public static RemoteService get() {
        if (null == remoteService) {
            synchronized (RetrofitHelper.class) {
                if (null == remoteService) {
                    new RetrofitHelper();
                }
            }
        }
        return RetrofitHelper.remoteService;
    }
}
