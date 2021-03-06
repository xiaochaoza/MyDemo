package com.fzzz.fragment.ui;

import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fzzz.fragment.R;
import com.fzzz.fragment.R2;
import com.fzzz.fragment.model.MainModel;
import com.fzzz.framework.base.BaseFragment;

import butterknife.BindView;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-25
 * update:
 */
public class MineFragment extends BaseFragment {
    @BindView(R2.id.fragment_mine_content)
    TextView contentTv;
    private MainModel mineFragmentModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void next() {
        mineFragmentModel = ViewModelProviders.of(this).get(MainModel.class);
        mineFragmentModel.setMineContent("mine");
        mineFragmentModel.getMineContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                contentTv.setText(s);
            }
        });
    }
}
