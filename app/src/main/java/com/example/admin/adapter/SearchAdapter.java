package com.example.admin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.R;


/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.admin.adapter
 *  @文件名:   SearchAdapter
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 23:04
 *  @描述：    TODO
 */

public class SearchAdapter
        extends SubAdapter
{

    public SearchAdapter(Context context, LayoutHelper layoutHelper, int count)
    {
        super(context,
              layoutHelper,
              count,
              new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public SearchAdapter(Context context,
                         LayoutHelper layoutHelper,
                         int count,
                         @NonNull VirtualLayoutManager.LayoutParams layoutParams)
    {
        super(context, layoutHelper, count, layoutParams);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext)
                                                .inflate(R.layout.include_title_bar_search,
                                                         parent,
                                                         false));
    }

    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal)
    {
    }
}
