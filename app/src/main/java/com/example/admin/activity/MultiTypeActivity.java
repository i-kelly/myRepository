/*
 * MIT License
 *
 * Copyright (c) 2016 Alibaba Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.admin.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.R;
import com.example.admin.adapter.MainViewHolder;
import com.example.admin.adapter.PagerAdapter;
import com.example.admin.adapter.SubAdapter;
import com.example.base.BaseActivity;
import com.example.model.bean.AdBean;
import com.example.model.bean.BannerBean;
import com.example.model.bean.BrandBean;
import com.example.model.bean.ExampleBean;
import com.example.present.ExamplePresent;
import com.example.present.contract.MultiTypeContract;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * @author villadora
 */
public class MultiTypeActivity extends BaseActivity<ExamplePresent>
        implements MultiTypeContract.View {

    @BindView(R.id.main_view)
    RecyclerView recyclerView;

    private Runnable                      trigger;
    private List<DelegateAdapter.Adapter> adapters;
    private RecyclerView.RecycledViewPool viewPool;
    private DelegateAdapter delegateAdapter;

    @Override
    protected ExamplePresent getPresenter() {
        return new ExamplePresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    public void showBanner(BannerBean bean) {
        //轮播图
        adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 1) {

            @Override
            public void onViewRecycled(MainViewHolder holder) {
                if (holder.itemView instanceof ViewPager) {
                    ((ViewPager) holder.itemView).setAdapter(null);
                }
            }

            @Override
            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1)
                    return new MainViewHolder(
                            LayoutInflater.from(MultiTypeActivity.this).inflate(R.layout.view_pager, parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

            }

            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                if (holder.itemView instanceof ViewPager) {
                    ViewPager viewPager = (ViewPager) holder.itemView;

                    viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

                    // from position to get adapter
                    viewPager.setAdapter(new PagerAdapter(this, viewPool));
                }
            }
        });
    }

    @Override
    public void showBrandList(BrandBean bean) {
        //通栏布局
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        layoutHelper.setBgColor(Color.rgb(135, 225, 90));
        layoutHelper.setAspectRatio(4);
        layoutHelper.setMargin(10, 20, 10, 20);
        layoutHelper.setPadding(10, 10, 10, 10);
        adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));

    }

    @Override
    public void showAdList(AdBean bean) {
        //grid布局
        adapters.add(new SubAdapter(this, new GridLayoutHelper(4), 0));
        GridLayoutHelper helper = new GridLayoutHelper(4);
        helper.setAspectRatio(4f);
        //helper.setColWeights(new float[]{40, 20, 30, 30});
        // helper.setMargin(0, 10, 0, 10);
        helper.setGap(10);
        adapters.add(new SubAdapter(this, helper, 80) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams lp = (VirtualLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            }
        });
    }

    @Override
    public void onSuccess() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 20);

        delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        adapters = new LinkedList<>();

        delegateAdapter.setAdapters(adapters);


        final Handler mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                // recyclerView.scrollToPosition(22);
                // recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.requestLayout();
                // mainHandler.postDelayed(trigger, 1000);
            }
        };


        mainHandler.postDelayed(trigger, 1000);
    }

    @Override
    public void fail() {

    }

    @Override
    public void showError(String msg) {

    }

}
