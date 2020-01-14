package com.fzzz.mydemo.ui.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-09-03
 * update:
 */
@Route(path = Constants.PATH_APP_RECYCLER_LOAD)
public class RecyclerViewLoadActivity extends BaseActivity {

    @BindView(R.id.rlv_content)
    RecyclerView rlvContent;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = DataInit.getData(page, 20);
        rlvContent.setLayoutManager(new LinearLayoutManager(this));
        rlvContent.setAdapter(mAdapter = new HomeAdapter());
        rlvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                        page++;
                        DataInit.list = null;
                        mDatas.addAll(DataInit.getData(page, 20));
                        Logger.d(mDatas.size());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示正在向下滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向上滚动
                    isSlidingToLast = false;
                }
            }
        });
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(RecyclerViewLoadActivity.this)
                    .inflate(R.layout.activity_recycler_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_recycler_item)
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataInit.list = null;
    }
}
