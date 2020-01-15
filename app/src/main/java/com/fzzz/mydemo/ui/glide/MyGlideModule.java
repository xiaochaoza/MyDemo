package com.fzzz.mydemo.ui.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.fzzz.mydemo.R;

import java.io.File;
import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * description:
 * author: ShenChao
 * time: 2020-01-14
 * update:
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {

    private CustomedHttpsVerify customedHttpsVerify = new CustomedHttpsVerify();

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(customedHttpsVerify.getSSLContext().getSocketFactory())
                .hostnameVerifier(customedHttpsVerify.getHostnameVerifier())
                .build();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //4.0默认为RGB_8888
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        builder.setDefaultRequestOptions(RequestOptions.formatOf(DecodeFormat.PREFER_ARGB_8888));
        //设置glide内存缓存大小
        //获取系统分配的最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置缓存占用八分之一
        int memoryCacheSize = maxMemory / 8;
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        //设置磁盘缓存大小
        File cacheDir = context.getExternalCacheDir();
        //设置磁盘缓存最大值
        int diskCacheSize = 1024 * 1024 * 20;
        builder.setDiskCache(new DiskLruCacheFactory(cacheDir.getPath(), "glide", diskCacheSize));
        //存放在/data/data/xxxx/cache
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide", diskCacheSize));
    }

    /**
     * 禁止解析清单文件 v3 升级v4用户，提升速度，避免一些错误
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    public static RequestOptions getRequestOptions() {
        return new RequestOptions()
//        CenterCrop 缩放宽和高都到达View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能超过边界
//        CenterInside 如果宽和高都在View的边界内，那就不缩放，否则缩放宽和高都进入View的边界，
//                      有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
//        CircleCrop 圆形且结合了CenterCrop的特性
//        FitCenter 缩放宽和高都进入View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
//        RoundedCorners 圆角
                //按比例填充imageview 可能显示不完整
//                        .centerCrop()
                //图片缩放 显示完整 可能填不满imageview
                .fitCenter()
//                DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
//                DiskCacheStrategy.NONE 不使用磁盘缓存
//                DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
//                DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
//                DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。默认
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //不加载动画
                .dontAnimate()
                //跳过内存缓存
                .skipMemoryCache(true)
                //.override(200,200)
                .placeholder(R.drawable.ic_load)
//                        加载优先级
//                       Priority.LOW
//                       Priority.NORMAL
//                       Priority.HIGH
//                       Priority.IMMEDIAT
                .priority(Priority.HIGH)
                .error(R.drawable.ic_error);
    }
}
