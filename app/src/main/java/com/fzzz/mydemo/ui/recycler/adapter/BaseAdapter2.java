package com.fzzz.mydemo.ui.recycler.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.ui.recycler.BaseAdapterBean;

import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2020/6/4
 * update:
 */
public class BaseAdapter2 extends BaseMultiItemQuickAdapter<BaseAdapterBean, BaseViewHolder> {

    public BaseAdapter2(@Nullable List<BaseAdapterBean> data) {
        super(data);
        addItemType(0, R.layout.activity_recycler_item);
        addItemType(1, R.layout.activity_recycler_item2);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseAdapterBean item) {
        switch (item.itemType) {
            case 0:
                helper.setText(R.id.tv_recycler_item, item.content);
                helper.addOnClickListener(R.id.fl_root);
                break;
            case 1:
                helper.setText(R.id.tv_recycler_item2, item.content);
                helper.addOnClickListener(R.id.fl_root2);
                break;
        }
    }
}
