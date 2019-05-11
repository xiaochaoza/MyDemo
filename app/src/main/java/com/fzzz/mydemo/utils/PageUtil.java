package com.fzzz.mydemo.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.mydemo.Constants;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class PageUtil {

    public static void toResultPage(String result) {
        ARouter.getInstance().build(Constants.PATH_RESULT).withString("result", result).navigation();
    }
}
