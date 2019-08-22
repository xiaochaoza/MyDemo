package com.fzzz.mydemo.ui.wifi;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-22
 * update:
 */
@GlideModule
public class OkHttpAppGlideModule extends AppGlideModule {
    CustomedHttpsVerify customedHttpsVerify = new CustomedHttpsVerify();

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(customedHttpsVerify.getSSLContext().getSocketFactory())
                .hostnameVerifier(customedHttpsVerify.getHostnameVerifier())
                .build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}
