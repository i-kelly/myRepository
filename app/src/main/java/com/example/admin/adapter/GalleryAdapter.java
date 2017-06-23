package com.example.admin.adapter;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.admin.adapter
 *  @文件名:   GalleryAdapter
 *  @创建者:   Admin
 *  @创建时间:  2017/6/21 0:32
 *  @描述：    TODO
 */

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.R;

import java.util.List;

public class GalleryAdapter
        extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private int         itemHeight;
    private List<String> data;

    public GalleryAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Activity context          = (Activity) recyclerView.getContext();
        Point    windowDimensions = new Point();
        context.getWindowManager()
               .getDefaultDisplay()
               .getSize(windowDimensions);
        itemHeight = Math.round(windowDimensions.y * 0.6f);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View           v        = inflater.inflate(R.layout.item_image, parent, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
        v.setLayoutParams(params);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        Glide.with(holder.itemView.getContext())
             .load(data.get(position))
             .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder {

        private View      overlay;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.icon);
        }

    }
}
