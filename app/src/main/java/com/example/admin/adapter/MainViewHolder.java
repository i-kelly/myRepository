package com.example.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-04-08 16:48
 * @desc TODO 类作用
 */
public class MainViewHolder extends RecyclerView.ViewHolder {

    public static volatile int existing     = 0;
    public static          int createdTimes = 0;

    public MainViewHolder(View itemView) {
        super(itemView);
        createdTimes++;
        existing++;
    }

    @Override
    protected void finalize() throws Throwable {
        existing--;
        super.finalize();
    }
}
