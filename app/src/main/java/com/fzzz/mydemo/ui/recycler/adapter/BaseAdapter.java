package com.fzzz.mydemo.ui.recycler.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fzzz.mydemo.R;

import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2020/6/4
 * update:
 */
public class BaseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BaseAdapter(@Nullable List<String> data) {
        super(R.layout.activity_recycler_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_recycler_item, item);
        helper.addOnClickListener(R.id.fl_root);
    }
}
