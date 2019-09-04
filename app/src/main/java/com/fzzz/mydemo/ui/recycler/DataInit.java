package com.fzzz.mydemo.ui.recycler;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-09-03
 * update:
 */
public class DataInit {

    public static List<String> list;

    public static List<String> getData(int pageNum, int pageCount) {
        if (null == list) {
            list = new ArrayList<>();
        }
        int start = (pageNum - 1) * pageCount + 1;
        int end = pageNum * pageCount;
        for (int i = start; i <= end; i++) {
            list.add("第" + i + "条数据");
        }
        return list;
    }
}
