package com.fzzz.mydemo.ui.result;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.R;

import butterknife.BindView;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
@Route(path = Constants.PATH_RESULT)
public class ResultActivity extends BaseActivity {

    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    public int getLayoutID() {
        return R.layout.activity_result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String result = getIntent().getStringExtra("result");

        tvResult.setText(result);
    }
}
