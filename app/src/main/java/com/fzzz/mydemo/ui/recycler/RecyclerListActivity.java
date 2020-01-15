package com.fzzz.mydemo.ui.recycler;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-09-03
 * update:
 */
@Route(path = Constants.PATH_APP_RECYCLER_LIST)
public class RecyclerListActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.bt_recycler_load, R.id.bt_recycler_sort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_recycler_load:
                PageUtil.toActivity(Constants.PATH_APP_RECYCLER_LOAD);
                break;
            case R.id.bt_recycler_sort:
                PageUtil.toActivity(Constants.PATH_APP_RECYCLER_SORT);
                break;
            default:
                break;
        }
    }
}
