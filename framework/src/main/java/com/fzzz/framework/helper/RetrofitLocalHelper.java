package com.fzzz.framework.helper;

import androidx.annotation.NonNull;

import com.fzzz.framework.BuildConfig;
import com.fzzz.framework.net.RemoteService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-14
 * update:
 */
public class RetrofitLocalHelper {
    private static RemoteService remoteService;

    private RetrofitLocalHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new RequestInterceptor())
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_LOCAL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        remoteService = retrofit.create(RemoteService.class);
    }

    public static RemoteService get() {
        if (null == remoteService) {
            synchronized (RetrofitLocalHelper.class) {
                if (null == remoteService) {
                    new RetrofitLocalHelper();
                }
            }
        }
        return RetrofitLocalHelper.remoteService;
    }

    private class RequestInterceptor implements Interceptor{

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept-Type", "application/json")
                    .build();
            return chain.proceed(request);
        }
    }
}
