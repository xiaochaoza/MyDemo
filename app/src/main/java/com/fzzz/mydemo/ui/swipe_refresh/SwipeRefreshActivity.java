package com.fzzz.mydemo.ui.swipe_refresh;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-06
 * update:
 */
@Route(path = Constants.PATH_APP_SWIPE_REFRESH)
public class SwipeRefreshActivity extends BaseActivity {
    public static final String TAG = "SwipeRefresh";
    //    @BindView(R.id.sv_root)
//    LinearLayout svRoot;
    @BindView(R.id.root_view)
    SwipeRefreshLayout rootView;
    @BindView(R.id.tv_conutdown_timer)
    TextView tvConutdownTimer;
    @BindView(R.id.bt_start)
    Button btStart;

    private int time = 10;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rootView.setRefreshing(false);
            Log.e(TAG, "-------------------------------->auto finish");
        }
    };

    private CountDownTimer countDownTimer = new CountDownTimer(time * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvConutdownTimer.setText(time + "");
            time--;
            if (0 == time) {
                onFinish();
                time = 10;
            }
            Log.e(TAG, "-------------------------------->countDownTimer start");
        }

        @Override
        public void onFinish() {
            countDownTimer.cancel();
            rootView.setRefreshing(false);
            tvConutdownTimer.setText("countDownTimer finish");
            btStart.setClickable(true);
            Log.e(TAG, "-------------------------------->countDownTimer finish");
        }
    };

    @Override
    public int getLayoutID() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (rootView.isRefreshing()) {
                    handler.postDelayed(runnable, 1500);
                    time = 10;
                    tvConutdownTimer.setText("CountDownTimer实现");
                }
            }
        });
    }

    @OnClick({R.id.bt_finish, R.id.bt_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                rootView.setRefreshing(true);
                countDownTimer.start();
                btStart.setClickable(false);
                break;
            case R.id.bt_finish:
                countDownTimer.cancel();
                rootView.setRefreshing(false);
                if (null != runnable) {
                    handler.removeCallbacks(runnable);
                }
                Log.e(TAG, "-------------------------------->click finish");
                btStart.setClickable(true);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
