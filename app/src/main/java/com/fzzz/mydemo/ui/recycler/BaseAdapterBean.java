package com.fzzz.mydemo.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * description:
 * author: ShenChao
 * time: 2020/7/8
 * update:
 */
public class BaseAdapterBean implements MultiItemEntity, Serializable {
    public String content;
    public int itemType;

    @Override
    public int getItemType() {
        return itemType;
    }
}
