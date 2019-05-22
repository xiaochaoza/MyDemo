package com.fzzz.mydemo.helper;

import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.net.RemoteService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .addInterceptor(new RequestInterceptor())
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

    private class RequestInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("aa", "aa")
                    .build();
            return chain.proceed(request);
        }
    }
}
