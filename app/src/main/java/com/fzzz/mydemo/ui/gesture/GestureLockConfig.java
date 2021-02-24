package com.fzzz.mydemo.ui.gesture;

/**
 * description:
 * author: ShenChao
 * time: 2019/5/24
 * update:
 */
public class GestureLockConfig {
    public static final int MAXCOUNT = 3; // 图案锁最大试错次数
    public static final int MINLENGTH_PSW = 4; // 密码最小长度
    public static final String INPUT = "绘制解锁图案";
    public static final String INPUT_AGAIN = "请再次绘制手势密码";
    public static final String ERROR_LENGTH = "至少需要连接4个点，请重新绘制";
    public static final String ERROR_INPUT = "与上一次绘制密码不一致，请重新绘制";
    public static final String SET_DONE = "设置手势密码成功";
}
