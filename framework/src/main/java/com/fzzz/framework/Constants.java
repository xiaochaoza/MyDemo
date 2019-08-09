package com.fzzz.framework;

import android.Manifest;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
public class Constants {
    public static final String PATH_APP_OKHTTP = "/ui/okhttp/OkHttpActivity";
    public static final String PATH_APP_RETROFIT = "/ui/retrofit/RetrofitActivity";
    public static final String PATH_APP_RESULT = "/ui/result/ResultActivity";
    public static final String PATH_APP_RXJAVA = "/ui/rxjava/RxJxvaActivity";
    public static final String PATH_APP_RETROFIT_RXJAVA = "/ui/rxjava/RetrofitRxjavaActivity";
    public static final String PATH_APP_WEBVIEW = "/ui/webview/WebViewActivity";
    public static final String PATH_APP_JS_BRIDGE = "/ui/webview/JsWebActivity";
    public static final String PATH_APP_JS_CAMERA = "/ui/webview/JsCameraActivity";
    public static final String PATH_APP_LAUNCHMODE = "/ui/launchmode/LaunchModeActivity";
    public static final String PATH_APP_ACTIVITY1 = "/ui/launchmode/Activity1";
    public static final String PATH_APP_ACTIVITY2 = "/ui/launchmode/Activity2";
    public static final String PATH_APP_ACTIVITY3 = "/ui/lunchmode/Activity3";
    public static final String PATH_APP_CUSTOMVIEW = "/ui/customview/CustomViewActivity";
    public static final String PATH_APP_LEAK = "/ui/LeakActivity";
    public static final String PATH_APP_FORUM = "/ui/ForumActivity";
    public static final String PATH_APP_SWIPE_REFRESH = "/ui/SwipeRefreshActivity";
    public static final String PATH_APP_VIEWSTUB = "/ui/ViewStubActivity";
    public static final String PATH_APP_CAMERA = "/ui/CameraActivity";
    public static final String PATH_APP_TAKEPIC_OLD = "/ui/TakePicOldActivity";
    public static final String PATH_APP_TAKEPIC_NEW = "/ui/TakePicNewActivity";

    public static final String PATH_INTERVIEW = "/interview/InterViewActivity";
    public static final String PATH_INTERVIEW_T11_1 = "/interview/T11Activity1";
    public static final String PATH_INTERVIEW_T11_2 = "/interview/T11Activity2";
    public static final String PATH_INTERVIEW_T13 = "/interview/T13Activity";
    public static final String PATH_INTERVIEW_T16 = "/interview/T16Activity";
    public static final String PATH_INTERVIEW_T18 = "/interview/T18Activity";
    public static final String PATH_INTERVIEW_T20 = "/interview/T20Activity";
    public static final String PATH_INTERVIEW_T21 = "/interview/T21Activity";
    public static final String PATH_INTERVIEW_T22 = "/interview/T22Activity";

    public static final String PATH_FRAGMENT_HOME = "/fragment/HomeActivity";

    public static final int CAMERA_OCR = 0;
    public static final int CAMERA_FACE = 1;
    public static final int TAKE_PIC_OLD = 1000;
    public static final int TAKE_PIC_NEW = 1001;

    public static final String[] PERMISSIONS_WRITE_EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};


}
