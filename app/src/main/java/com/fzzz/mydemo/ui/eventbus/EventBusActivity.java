package com.fzzz.mydemo.ui.eventbus;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
@Route(path = Constants.PATH_APP_EVENTBUS, group = Constants.GROUP_APP_NEED_LOGIN)
public class EventBusActivity extends BaseActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_eventbus;
    }

    @OnClick({R.id.bt_register, R.id.bt_send, R.id.bt_unregister, R.id.bt_send_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                if (EventBus.getDefault().isRegistered(this)) {
                    show("已注册，不能多次注册");
                    return;
                }
                EventBus.getDefault().register(this);
                show("注册成功");
                break;
            case R.id.bt_send:
                EventBus.getDefault().post("12345");
                show("发送成功");
                break;
            case R.id.bt_unregister:
                EventBus.getDefault().unregister(this);
                show("释放成功");
                break;
            case R.id.bt_send_sticky:
                EventBus.getDefault().postSticky("23456");
                show("发送黏性成功");
                break;
            default:
                break;
        }
    }

    /**
     * 处理事件优先级priority，越高越先执行
     * cancelEventDelivery 可以取消事件，但一定要在发生线程，其他线程无效
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.POSTING, priority = 100)
    public void onEvent(String message) {
        show(message);
        EventBus.getDefault().cancelEventDelivery(message);
        show("cancel");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onEvent2(String message) {
        show(message);
    }

    /**
     * 黏性事件先发消息后订阅也会生效
     * 黏性事件发送后，每次注册都会走一次处理，会产生很多问题，黏性事件尽量不用
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1, sticky = true)
    public void onEventSticky(String message) {
        show("sticky: " + message);
    }

    private void show(String message) {
        tvShow.append("\n");
        tvShow.append(message);
    }
}
