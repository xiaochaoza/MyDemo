package com.fzzz.mydemo.ui.floating;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
@Route(path = Constants.PATH_APP_FLOATING)
public class FloatingActionActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_floating;
    }

}
