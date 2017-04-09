package com.example.admin.adapter;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.admin.adapter
 *  @文件名:   BannerAdapter
 *  @创建者:   Admin
 *  @创建时间:  2017/4/10 0:08
 *  @描述：    TODO
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.R;
import com.example.model.bean.MultiTypeBean;

import java.util.List;

public class BannerAdapter
        extends SubAdapter
{

    private List<MultiTypeBean.ObjBean.BannerListBean> bean;
    private RecyclerView.RecycledViewPool              mPool;

    public BannerAdapter(Context context,
                         LayoutHelper layoutHelper,
                         int count,
                         RecyclerView.RecycledViewPool pool,
                         List<MultiTypeBean.ObjBean.BannerListBean> bean)
    {


        super(context,
              layoutHelper,
              count,
              new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
        this.mPool = pool;
        this.bean = bean;
    }

    public BannerAdapter(Context context,
                         LayoutHelper layoutHelper,
                         int count,
                         @NonNull VirtualLayoutManager.LayoutParams layoutParams)
    {
        super(context, layoutHelper, count, layoutParams);
    }

    @Override
    public void onViewRecycled(MainViewHolder holder) {
        if (holder.itemView instanceof ViewPager) {
            ((ViewPager) holder.itemView).setAdapter(null);
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MainViewHolder(LayoutInflater.from(mContext)
                                                    .inflate(R.layout.view_pager, parent, false));
        }

        return new MainViewHolder(LayoutInflater.from(mContext)
                                                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal)
    {

    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if (holder.itemView instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) holder.itemView;

            viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                            200));

            // from position to get adapter
            viewPager.setAdapter(new PagerAdapter(this, mPool, bean));
        }
    }
}
