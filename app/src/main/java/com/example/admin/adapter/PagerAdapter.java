package com.example.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.R;
import com.example.model.bean.MultiTypeBean;
import com.example.soulrelay.utils.imageloader.ImageLoaderUtil;

import java.util.List;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-04-08 16:50
 * @desc TODO 类作用
 */
public class PagerAdapter
        extends RecyclablePagerAdapter<MainViewHolder>
{

    List<MultiTypeBean.ObjBean.BannerListBean> bean;

    public PagerAdapter(SubAdapter adapter,
                        RecyclerView.RecycledViewPool pool,
                        List<MultiTypeBean.ObjBean.BannerListBean> bean)
    {
        super(adapter, pool);
        this.bean = bean;
    }

    @Override
    public int getCount() {
        if (bean == null) { return 0; }
        return bean.size();
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        // only vertical
        viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                                  ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoaderUtil.getInstance()
                       .loadImage(bean.get(position).adImgUrl,
                                  ((ImageView) viewHolder.itemView.findViewById(R.id.icon)));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

}
