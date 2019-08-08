package com.fzzz.mydemo.ui.viewstub;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-08
 * update:
 */
@Route(path = Constants.PATH_APP_VIEWSTUB)
public class ViewStubActivity extends BaseActivity {
    @BindView(R.id.bt_viewstub)
    Button btViewstub;
    @BindView(R.id.vs_item)
    ViewStub vsItem;

    @Override
    public int getLayoutID() {
        return R.layout.activity_viewstub;
    }

    @OnClick(R.id.bt_viewstub)
    public void onViewClicked() {
        vsItem.setVisibility(View.VISIBLE);
    }
}
