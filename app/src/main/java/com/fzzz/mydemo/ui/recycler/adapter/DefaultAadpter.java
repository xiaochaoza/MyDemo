package com.fzzz.mydemo.ui.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fzzz.mydemo.R;
import com.fzzz.mydemo.ui.recycler.RecyclerDefaultActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2020/6/4
 * update:
 */
public class DefaultAadpter extends RecyclerView.Adapter<DefaultAadpter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;
    private OnItemClickListener onItemClickListener;

    public DefaultAadpter(Context context, List<String> data) {
        this.mDatas = data;
        this.mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.activity_recycler_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));

        holder.flRoot.setOnClickListener((v) -> {
            onItemClickListener.onItemClick(mDatas.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fl_root)
        FrameLayout flRoot;
        @BindView(R.id.tv_recycler_item)
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
