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
import com.fzzz.fragment.model.HomeFragmentModel;
import com.fzzz.framework.base.BaseFragment;

import butterknife.BindView;


/**
 * description:
 * author: ShenChao
 * time: 2019-07-25
 * update:
 */
public class HomeFragment extends BaseFragment {
    @BindView(R2.id.fragment_home_content)
    TextView contentTv;
    private HomeFragmentModel homeFragmentModel;
    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void next() {
        homeFragmentModel = ViewModelProviders.of(this).get(HomeFragmentModel.class);
        homeFragmentModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                contentTv.setText(s);
            }
        });
    }
}
