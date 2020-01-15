package com.fzzz.mydemo.ui.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * description:
 * author: ShenChao
 * time: 2020-01-14
 * update:
 */
@Route(path = Constants.PATH_APP_GLIDE)
public class GlideActivity extends BaseActivity {

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    CustomGlideView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.iv7)
    ImageView iv7;
    @BindView(R.id.iv8)
    ImageView iv8;
    @BindView(R.id.iv9)
    ImageView iv9;
    @BindView(R.id.iv10)
    ImageView iv10;
    @BindView(R.id.iv11)
    ImageView iv11;

    /**
     * 图片地址
     */
    public static final String IMAGE_URL = "http://pic.wenwen.soso.com/p/20100324/20100324180347-1942827600.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.activity_glide;
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9,
            R.id.bt10, R.id.bt11, R.id.bt_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Glide.with(this)
                        .load(IMAGE_URL)
                        //缩放
                        .thumbnail(0.1f)
                        .apply(RequestOptions.fitCenterTransform())
                        //淡入淡出
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(iv1);
                break;
            case R.id.bt2:
                RequestBuilder requestBuilder = Glide.with(this).asBitmap();
                requestBuilder.load(R.mipmap.dong);
                requestBuilder.apply(MyGlideModule.getRequestOptions());
                requestBuilder.into(iv2);
                break;
            case R.id.bt3:
                //不要使用匿名内部类，有可能图片没加载被系统回收
                CustomTarget<Bitmap> simpleTarget = new CustomTarget<Bitmap>(300, 300) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv3.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                };
                //创建一个类继承AppGlideModule 加上@GlideModule 否则没有glideApp
                GlideApp.with(this)
                        .asBitmap()
                        .load(IMAGE_URL)
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .override(230, 230)
                        .fitCenter()
                        .into(simpleTarget);
                break;
            case R.id.bt4:
                CustomViewTarget viewTarget = new CustomViewTarget<CustomGlideView, Drawable>(iv4) {
                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {
                        this.view.setImage(placeholder);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        this.view.setImage(errorDrawable);
                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setImage(resource);
                    }
                };
                Glide.with(this)
                        .load(IMAGE_URL)
                        .into(viewTarget);
                break;
            case R.id.bt5:
                GlideApp.with(this)
                        .load(R.mipmap.dong)
                        .skipMemoryCache(true)
                        .fitCenter()
                        .thumbnail(Glide.with(this)
                                .load(R.mipmap.show))
                        .transition(DrawableTransitionOptions.withCrossFade(1500))
//                        .transition(new DrawableTransitionOptions().dontTransition())
//                        .transition(DrawableTransitionOptions.with(NoTransition.<Drawable>getFactory()))
                        .into(iv5);
                break;
            case R.id.bt6:
                //圆形
                GlideApp.with(this)
                        .load(IMAGE_URL)
                        .circleCrop()
                        .skipMemoryCache(true)
                        .into(iv6);
                break;
            case R.id.bt7:
                GlideApp.with(this)
                        .load(IMAGE_URL)
                        .transform(new BlurTransformation(50))
                        .skipMemoryCache(true)
                        .into(iv7);
                break;
            case R.id.bt8:
                //正方形
                GlideApp.with(this)
                        .load(IMAGE_URL)
                        .transform(new CropSquareTransformation())
                        .skipMemoryCache(true)
                        .into(iv8);
                break;
            case R.id.bt9:
                //加载圆形头像
                Glide.with(this)
                        .load(IMAGE_URL)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(iv9);
                break;
            case R.id.bt10:
                //加载圆角头像
                GlideApp.with(this)
                        .load(IMAGE_URL)
                        .fitCenter()
                        .transform(new RoundedCornersTransformation(50, 0, RoundedCornersTransformation.CornerType.ALL))
                        .skipMemoryCache(true)
                        .into(iv10);
                break;
            case R.id.bt11:
                //加载https
                Glide.with(this)
                        .load("https://218.95.135.201/images/govinfo/544cd2d5-105c-46ca-b961-a429b48ef15b.jpg")
                        .into(iv11);
                break;
            case R.id.bt_clear:
                iv1.setImageBitmap(null);
                iv2.setImageBitmap(null);
                iv3.setImageBitmap(null);
                iv4.setImage(null);
                iv5.setImageBitmap(null);
                iv6.setImageBitmap(null);
                iv7.setImageBitmap(null);
                iv8.setImageBitmap(null);
                iv9.setImageBitmap(null);
                iv10.setImageBitmap(null);
                iv11.setImageBitmap(null);
                break;
            default:
                break;
        }
    }
}
