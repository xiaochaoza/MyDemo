package com.fzzz.mydemo.ui.forum;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fzzz.mydemo.R;
import com.fzzz.mydemo.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-31
 * update:
 */
public class FormView extends RelativeLayout {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.content)
    TextView content;

    public int nheight;

    public FormView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_forum_view, this);
        ButterKnife.bind(this, this);
    }

    public void set(User user) {
        name.setText(user.id);
        content.setText(user.content);
    }

    public int getNheight() {
        return nheight;
    }
}
