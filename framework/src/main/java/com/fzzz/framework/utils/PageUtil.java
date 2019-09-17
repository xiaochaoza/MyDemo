package com.fzzz.framework.utils;

import android.app.Activity;
import android.content.Context;

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

    public static void toActivityGroup(String path, String group) {
        ARouter.getInstance().build(path, group).navigation();
    }

    public static void toActivityForResult(Activity activity, String path, int requestCode) {
        ARouter.getInstance().build(path).navigation(activity, requestCode);
    }

}
