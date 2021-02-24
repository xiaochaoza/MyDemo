package com.fzzz.mydemo.ui.recycler;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.data.DataInit;
import com.fzzz.mydemo.ui.recycler.adapter.BaseAdapter;
import com.fzzz.mydemo.ui.recycler.adapter.BaseAdapter2;
import com.fzzz.mydemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description: base adapter加载recyclerview
 * author: ShenChao
 * time: 2020-06-04
 * update:
 */
@Route(path = Constants.PATH_APP_RECYCLER_BASE2)
public class RecyclerBaseActivity2 extends BaseActivity {

    @BindView(R.id.rlv_content)
    RecyclerView rlvContent;
    private List<BaseAdapterBean> mDatas = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rlvContent.setLayoutManager(new LinearLayoutManager(this));
        BaseAdapter2 adapter = new BaseAdapter2(mDatas);
        rlvContent.setAdapter(adapter);


        for (int i = 0; i < 10; i++) {
            BaseAdapterBean bean = new BaseAdapterBean();
            bean.content = "第" + i + "条";
            if (i % 3 == 0) {
                bean.itemType = 0;
            } else {
                bean.itemType = 1;
            }
            mDatas.add(bean);
        }

        adapter.notifyDataSetChanged();

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show(mDatas.get(position).content);
            }
        });
    }
}
