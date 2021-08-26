//package com.fzzz.mydemo.ui.push;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.fzzz.mydemo.R;
//import com.orhanobut.logger.Logger;
//import com.umeng.message.UmengNotifyClickActivity;
//
//import org.android.agoo.common.AgooConstants;
//
///**
// * description:
// * author: ShenChao
// * time: 2020/4/10
// * update:
// */
//public class PushTestActivity extends UmengNotifyClickActivity {
//
//    @Override
//    protected void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.activity_push);
//    }
//
//    @Override
//    public void onMessage(Intent intent) {
//        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
//        String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
//        Logger.e("body -----> " + body);
//    }
//}
