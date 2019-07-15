package com.fzzz.interview.t22;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.interview.BaseActivity;
import com.fzzz.interview.R;
import com.fzzz.interview.R2;

import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-15
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T22)
public class T22Activity extends BaseActivity {
    /**
     * 参数设置
     */
    T22Service countService;

    private ServiceConnection conn = new ServiceConnection() {
        /**
         * 获取服务对象时的操作
         */
        public void onServiceConnected(ComponentName name, IBinder service) {
            countService = ((T22Service.ServiceBinder) service).getService();

        }

        /**
         * 无法获取到服务对象时的操作
         */
        public void onServiceDisconnected(ComponentName name) {
            countService = null;
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.activity_t22;
    }

    @OnClick(R2.id.bt_start_service)
    public void startService() {
        Intent intent = new Intent(this, T22Service.class);
        startService(intent);
    }

    @OnClick(R2.id.bt_stop_service)
    public void stopService() {
        Intent intent = new Intent(this, T22Service.class);
        stopService(intent);
    }

    @OnClick(R2.id.bt_bind_service)
    public void bindService() {
        Intent intent = new Intent(this, T22Service.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @OnClick(R2.id.bt_unbind_service)
    public void unbindService() {
        unbindService(conn);
    }


}
