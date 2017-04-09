package com.example.admin.adapter;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.admin.adapter
 *  @文件名:   GridAdpter
 *  @创建者:   Admin
 *  @创建时间:  2017/4/10 0:18
 *  @描述：    TODO
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.R;
import com.example.model.bean.MultiTypeBean;
import com.example.soulrelay.utils.imageloader.ImageLoaderUtil;

import java.util.List;

public class GridAdpter
        extends SubAdapter
{
    private List<MultiTypeBean.ObjBean.AdHomePageListBean> bean;

    public GridAdpter(Context context,
                      LayoutHelper layoutHelper,
                      int count,
                      List<MultiTypeBean.ObjBean.AdHomePageListBean> bean)
    {
        super(context,
              layoutHelper,
              count,
              new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
        this.bean = bean;
    }

    public GridAdpter(Context context,
                      LayoutHelper layoutHelper,
                      int count,
                      @NonNull VirtualLayoutManager.LayoutParams layoutParams)
    {
        super(context, layoutHelper, count, layoutParams);
    }

    @Override
    public int getItemCount() {
        if (bean == null) { return 0; }
        return bean.size();
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext)
                                                .inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                              ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoaderUtil.getInstance()
                       .loadImage(bean.get(position).adImgUrl,
                                  ((ImageView) holder.itemView.findViewById(R.id.icon)));
    }

    @Override
    protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal)
    {}
}
