package com.fzzz.mydemo.ui.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.utils.PageUtil;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.data.DataInit;
import com.fzzz.mydemo.ui.recycler.adapter.DefaultAadpter;
import com.fzzz.mydemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description: 默认adapter加载recyclerview
 * author: ShenChao
 * time: 2020-06-04
 * update:
 */
@Route(path = Constants.PATH_APP_RECYCLER_DEFAULT)
public class RecyclerDefaultActivity extends BaseActivity {

    @BindView(R.id.rlv_content)
    RecyclerView rlvContent;
    private List<String> mDatas;
    private DefaultAadpter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatas = DataInit.getData();

        rlvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DefaultAadpter(this, mDatas);
        rlvContent.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DefaultAadpter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                ToastUtil.show(item);
            }
        });

    }

}
