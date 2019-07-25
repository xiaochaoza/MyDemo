package com.fzzz.fragment.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fzzz.fragment.R;
import com.fzzz.fragment.R2;
import com.fzzz.fragment.model.MineFragmentModel;
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
    private MineFragmentModel mineFragmentModel;
    @Override
    public int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void next() {
        mineFragmentModel = ViewModelProviders.of(this).get(MineFragmentModel.class);
        mineFragmentModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                contentTv.setText(s);
            }
        });
    }
}
