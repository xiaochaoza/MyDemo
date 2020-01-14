package com.fzzz.framework.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fzzz.framework.R;
import com.fzzz.framework.utils.ScreenUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-25
 * update:
 */
public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.设置当前视图
        int layoutID = getLayoutId();
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        initView(layoutID, view);
        //视图注入
        unbinder = ButterKnife.bind(this, view);
        next();
        return view;
    }

    protected abstract void next();

    private void initView(int layoutID, View view) {
        View statusView = view.findViewById(R.id.status_view);
        ViewGroup.LayoutParams params = statusView.getLayoutParams();
//        params.height = ScreenUtil.getStatusHeight(getActivity());
//        statusView.setLayoutParams(params);
        FrameLayout mainContent = view.findViewById(R.id.fragment_content);
        View content = View.inflate(getActivity(), layoutID, null);
        mainContent.addView(content);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public abstract int getLayoutId();
}
