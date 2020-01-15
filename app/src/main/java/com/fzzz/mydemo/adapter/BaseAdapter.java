package com.fzzz.mydemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fzzz.mydemo.R;
import com.fzzz.mydemo.bean.UserReturnBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-28
 * update:
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private List<UserReturnBean.User> userList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String userName, String password);
    }

    public BaseAdapter(UserReturnBean userReturnBean, OnItemClickListener onItemClickListener) {
        this.userList = userReturnBean.data;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String userName = userList.get(position).userName;
        String passWord = userList.get(position).passWord;

        holder.tvUsername.setText(userName);
        holder.tvPassword.setText(passWord);

        holder.iitemLayout.setOnClickListener((view) -> {
            if (null != onItemClickListener) {
                onItemClickListener.onItemClick(userName, passWord);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_password)
        TextView tvPassword;
        @BindView(R.id.item_layout)
        LinearLayout iitemLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
