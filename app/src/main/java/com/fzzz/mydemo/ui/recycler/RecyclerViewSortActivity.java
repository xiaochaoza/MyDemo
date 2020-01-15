package com.fzzz.mydemo.ui.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.orhanobut.logger.Logger;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-09-03
 * update:
 */
@Route(path = Constants.PATH_APP_RECYCLER_SORT)
public class RecyclerViewSortActivity extends BaseActivity {

    @BindView(R.id.rlv_content)
    RecyclerView rlvContent;
    private List<String> mDatas;
    private SortAadpter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatas = DataInit.getData();

        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.Callback() {
            /**
             * 设置滑动类型标记
             *
             * @param recyclerView
             * @param viewHolder
             * @return
             *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);
            }

            /**
             * Item是否支持长按拖动
             *
             * @return
             *          true  支持长按操作
             *          false 不支持长按操作
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            /**
             * Item是否支持滑动
             *
             * @return
             *          true  支持滑动操作
             *          false 不支持滑动操作
             */
            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
            }

            /**
             * 拖拽切换Item的回调
             *
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return
             *          如果Item切换了位置，返回true；反之，返回false
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                mAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            /**
             * 滑动item
             * @param viewHolder
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mCallback);
        rlvContent.setLayoutManager(new LinearLayoutManager(this));
        rlvContent.setAdapter(mAdapter = new SortAadpter());
        rlvContent.setItemAnimator(new DefaultItemAnimator());
        mItemTouchHelper.attachToRecyclerView(rlvContent);
    }

    class SortAadpter extends RecyclerView.Adapter<SortAadpter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(RecyclerViewSortActivity.this)
                    .inflate(R.layout.activity_recycler_item, parent, false));
            return holder;
        }

        public void move(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                //从上往下拖动，每滑动一个item，都将list中的item向下交换，向上滑同理。
                for (int i = fromPosition; i < toPosition; i++) {
                    //交换数据源两个数据的位置
                    Collections.swap(mDatas, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    //交换数据源两个数据的位置
                    Collections.swap(mDatas, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            Logger.e(mDatas.toString());
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

}
