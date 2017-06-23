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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.R;
import com.example.model.bean.MultiTypeBean;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import widget.ZoomOutPageTransformer;

public class BannerAdapter
        extends SubAdapter {

    private List<MultiTypeBean.ObjBean.BannerListBean> bean;
    private RecyclerView.RecycledViewPool              mPool;

    public BannerAdapter(Context context,
                         LayoutHelper layoutHelper,
                         int count,
                         RecyclerView.RecycledViewPool pool,
                         List<MultiTypeBean.ObjBean.BannerListBean> bean) {


        super(context, layoutHelper, count,
              new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
        this.mPool = pool;
        this.bean = bean;
    }

    public BannerAdapter(Context context,
                         LayoutHelper layoutHelper,
                         int count,
                         @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
        super(context, layoutHelper, count, layoutParams);
    }

    @Override
    public void onViewRecycled(MainViewHolder holder) {
        if (holder.itemView instanceof ViewPager) {
            ((ViewPager) holder.itemView).setAdapter(null);
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
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
    protected void onBindViewHolderWithOffset(MainViewHolder holder,
                                              int position,
                                              int offsetTotal) {

    }

    @Override
    public void onBindViewHolder(MainViewHolder holder,
                                 int position) {
        if (holder.itemView instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) holder.itemView;

            viewPager.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                          200));

            // from position to get adapter
            viewPager.setAdapter(new PagerAdapter(this, mPool, bean));
            viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
        }

        //        DiscreteScrollView scrollView = (DiscreteScrollView) holder.itemView.findViewById(
        //                R.id.picker);
                List<String> list = new ArrayList<>();
                for(MultiTypeBean.ObjBean.BannerListBean urlBean:bean){
                    list.add(urlBean.adImgUrl);
                }
        //        scrollView.setAdapter(new GalleryAdapter(list));
        ////        scrollView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        //        scrollView.setItemTransformer(new ScaleTransformer.Builder()
        //                                              .setMinScale(0.8f)
        //                                              .build());

        ConvenientBanner convenientBanner = (ConvenientBanner) holder.itemView.findViewById(
                R.id.convenientBanner);

        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, list)
                        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                        .setPageIndicator(new int[]{R.drawable.ic_page_indicator,
//                                                    R.drawable.ic_page_indicator_focused})
                        //设置指示器的方向
                        .setPageIndicatorAlign(
                                ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
//        .setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //        convenientBanner.setManualPageable(false);//设置不能手动影响


 /*       Banner banner = (Banner) holder.itemView.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> list = new ArrayList<>();
        for(MultiTypeBean.ObjBean.BannerListBean urlBean:bean){
            list.add(urlBean.adImgUrl);
        }
        banner.setImages(list);
        banner.
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(list);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();*/
    }

    public class LocalImageHolderView
            implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context,
                             final int position,
                             Integer data) {
            imageView.setImageResource(data);
        }
    }

    public class GlideImageLoader
            extends ImageLoader {
        @Override
        public void displayImage(Context context,
                                 Object path,
                                 ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //            eg：

            //Glide 加载图片简单用法
            Glide.with(context)
                 .load(path)
                 .into(imageView);

            //Picasso 加载图片简单用法
            //            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            //            Uri uri = Uri.parse((String) path);
            //            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        //        @Override
        //        public ImageView createImageView(Context context) {
        //            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        //            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        //            return simpleDraweeView;
        //        }
    }
}
