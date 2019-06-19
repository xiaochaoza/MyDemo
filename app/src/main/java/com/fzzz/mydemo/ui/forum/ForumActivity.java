package com.fzzz.mydemo.ui.forum;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-31
 * update:
 */
public class ForumActivity extends BaseActivity {

    @BindView(R.id.root_view)
    RelativeLayout rootView;

    private List<User> list = new ArrayList<>();

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

    int p;
    int hi = 0;
    List<FormView> viewList = new ArrayList<>();
    FormView temp = null;
    private void initView() {

        for (int i = 0; i < list.size(); i++) {
            FormView formView = new FormView(this);
            formView.set(list.get(0));
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) formView.getLayoutParams();


            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            formView.measure(w, h);
            hi+= formView.getMeasuredHeight();
//            lp.height =i*50;
            lp.leftMargin = i * 6;
            lp.topMargin = i * 50;
            lp.rightMargin = i * 6;
            formView.setLayoutParams(lp);
            formView.nheight = hi;
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_forum;
    }

}
