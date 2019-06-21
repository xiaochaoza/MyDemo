package com.fzzz.framework.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-21
 * update:
 */
public class ActivityManager {
    private static ActivityManager instance;

    /**
     * Activity栈
     */
    private Stack<Activity> activityStack;

    /**
     * 结束到指定activity为止的所有Activity
     */
    private Stack<Activity> betweenStark = new Stack<>();


    private ActivityManager() {
        activityStack = new Stack<>();
    }

    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 栈内是否包含某个activity
     */
    public boolean containActivityClass(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 压栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        /**
         * ResultActivity不能为第一个
         */
        if (ActivityManager.getInstance().activityStack != null &&
                !ActivityManager.getInstance().activityStack.isEmpty()) {
            Activity activity0 = ActivityManager.getInstance().activityStack.get(0);
            if (null != activity0 && !activity0.isFinishing()) {
                String activityName = activity0.getClass().getSimpleName();
                if (activityName.equals("ResultActivity")) {
                    activity0.finish();
                }
            }
        }
        activityStack.add(activity);
    }

//    /**
//     * 加入dialogFragment
//     *
//     * @param dialogFragment
//     */
//    public void addDialogFragment(RxAppCompatDialogFragment dialogFragment) {
//        fragmentStack.add(dialogFragment);
//    }

//    /**
//     * 获取栈顶dialogfragment
//     *
//     * @return
//     */
//    public RxAppCompatDialogFragment getTopFragmentDialog() {
//        if (!fragmentStack.empty()) {
//            return fragmentStack.peek();
//        }
//        return null;
//    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    public Activity currentActivity() {
        if (activityStack.isEmpty()) {
            return null;
        }
        //stack.peek()返回栈顶数据，但是不出栈
        //stack.pop()返回栈顶数据，但是出栈
        Activity activity = activityStack.peek();
        return activity;
    }

    /**
     * 结束指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

//    /**
//     * 移除DialogFragment
//     *
//     * @param dialogFragment
//     */
//    public void removeDialogFragment(RxAppCompatDialogFragment dialogFragment) {
//        if (null != dialogFragment) {
//            fragmentStack.remove(dialogFragment);
//            Activity activity = dialogFragment.getActivity();
//            if (null != activity && !activity.isFinishing() && !dialogFragment.isRemoving()) {
//                dialogFragment.dismissAllowingStateLoss();
//            }
//        }
//    }

//    /**
//     * RxAppCompatDialogFragment
//     */
//    public void finishAllDialogFragment() {
//        for (RxAppCompatDialogFragment dialogFragment : fragmentStack) {
//            if (null != dialogFragment) {
//                Activity activity = dialogFragment.getActivity();
//                if (null != activity && !activity.isFinishing() && !dialogFragment.isRemoving()) {
//                    dialogFragment.dismissAllowingStateLoss();
//                }
//            }
//        }
//    }


    /**
     * 包括当前Activity
     *
     * @param cls
     */
    public void finishBetweenActivity(Class<?> cls) {
        betweenStark.clear();
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            if (activity != null) {
                if (activity.getClass().equals(cls)) {
                    betweenStark.add(activity);
                    break;
                } else {
                    betweenStark.add(activity);
                }
            }
        }
        for (Activity activity : betweenStark) {
            if (activity != null) {
                removeActivity(activity);
            }
        }
    }

    /**
     * 不包括当前Activity
     *
     * @param cls
     */
    public void finishBetweenActivityExclude(Class<?> cls) {
        betweenStark.clear();
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            if (activity != null) {
                if (activity.getClass().equals(cls)) {
                    break;
                } else {
                    betweenStark.add(activity);
                }
            }
        }
        for (Activity activity : betweenStark) {
            if (activity != null) {
                removeActivity(activity);
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */

    public void exit() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivityClass(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    removeActivity(activity);
                    break;
                }
            }
        }
    }

    /**
     * 是否只包含一个Activity并且在栈顶
     *
     * @param cls
     * @return
     */
    public boolean isOnlyTop(Class<?> cls) {
        return activityStack.size() == 1 && activityStack.peek().getClass().equals(cls);
    }


    public void gotoMainTab() {
        for (int i = activityStack.size() - 1; i > 0; i--) {
            removeActivity(activityStack.get(i));
        }
    }
}
