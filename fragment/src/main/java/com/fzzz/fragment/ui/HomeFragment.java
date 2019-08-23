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
public class HomeFragment extends BaseFragment {
    @BindView(R2.id.fragment_home_content)
    TextView contentTv;
    private MainModel homeFragmentModel;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void next() {
        homeFragmentModel = ViewModelProviders.of(this).get(MainModel.class);
        homeFragmentModel.setHomeContent("home");
        homeFragmentModel.getHomeContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                contentTv.setText(s);
            }
        });
    }
}
