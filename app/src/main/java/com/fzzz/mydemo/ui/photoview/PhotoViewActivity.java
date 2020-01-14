package com.fzzz.mydemo.ui.photoview;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.ui.wifi.GlideApp;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2020-01-14
 * update:
 */
@Route(path = Constants.PATH_APP_PHOTO_VIEW)
public class PhotoViewActivity extends BaseActivity {

    @BindView(R.id.photo_view)
    PhotoView photoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this)
                .load("http://appcs.zwfw.nx.gov.cn/license-api-release/m/v2/license/image_file?fileId=5e0ee169c409870a8c9380df")
                .into(photoView);
    }
}
