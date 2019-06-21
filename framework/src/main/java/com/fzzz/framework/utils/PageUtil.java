package com.fzzz.framework.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fzzz.framework.Constants;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class PageUtil {

    public static void toResultPage(String result) {
        ARouter.getInstance().build(Constants.PATH_APP_RESULT).withString("result", result).navigation();
    }

    public static void toActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

}
