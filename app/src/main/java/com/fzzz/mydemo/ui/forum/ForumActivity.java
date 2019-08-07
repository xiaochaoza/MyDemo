package com.fzzz.mydemo.ui.forum;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-31
 * update:
 */
@Route(path = Constants.PATH_APP_FORUM)
public class ForumActivity extends BaseActivity {

    @BindView(R.id.root_view)
    RelativeLayout rootView;

    private List<User> list = new ArrayList<>();

    @Override
    public int getLayoutID() {
        return R.layout.activity_forum;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.id = i + "";
            user.content = "评论" + i;
            list.add(user);
        }
        initView();

    }

    int hi = 0;

    private void initView() {

        for (int i = list.size(); i >= 0; i--) {
            FormView formView = new FormView(this);
            formView.set(list.get(0));
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) formView.getLayoutParams();

            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            formView.measure(w, h);
            hi += formView.getMeasuredHeight();
            lp.height = i * 50;
            lp.leftMargin = i * 6;
            lp.topMargin = i * 50;
            lp.rightMargin = i * 6;
            formView.setLayoutParams(lp);
//            formView.nheight = hi;
//            if (temp!=null) {
//                temp.addView(formView);
//                rootView.addView(temp);
//
//            }
//            temp = formView;
//            viewList.add(formView);
            rootView.addView(formView);


        }

//        for (int i = 0; i < viewList.size(); i++) {
//            FormView formView = viewList.get(i);
//            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) viewList.get(i).getLayoutParams();
//            lp.height = viewList.get(viewList.size() - i - 1).getNheight();
//            formView.setLayoutParams(lp);
//            rootView.addView(formView);
//        }

        System.out.println();
    }

}
