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
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.R;
import com.example.Router;
import com.example.admin.adapter.BannerAdapter;
import com.example.admin.adapter.GridAdpter;
import com.example.admin.adapter.SearchAdapter;
import com.example.admin.adapter.SubAdapter;
import com.example.base.BaseActivity;
import com.example.base.BaseFragment;
import com.example.fragment.OneFragment;
import com.example.model.bean.MultiTypeBean;
import com.example.present.MultiTypePresenter;
import com.example.present.contract.MultiTypeContract;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-17 09:56
 * @desc 多类型页面
 */
@Router("MultiTypeActivity")
public class MultiTypeActivity
        extends BaseActivity<MultiTypePresenter>
        implements MultiTypeContract.View, BaseFragment.Callback {

    @BindView(R.id.main_view)
    RecyclerView           recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private Runnable                      trigger;
    private List<DelegateAdapter.Adapter> adapters;
    private RecyclerView.RecycledViewPool viewPool;
    private DelegateAdapter               delegateAdapter;

    @Override
    protected MultiTypePresenter getPresenter() {
        return new MultiTypePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {


        super.initViews(savedInstanceState);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });


        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        delegateAdapter = new

                DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);
        adapters = new LinkedList<>();
        //搜索
        //通栏布局
        //        FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
        //        layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);//吸边
        //        layoutHelper.setDefaultLocation(100, 400);
        StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
        layoutHelper.setStickyStart(true);//吸顶
        adapters.add(new SearchAdapter(this, layoutHelper, 1));
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                mPresenter.getData();
            }
        }, 1000);

     /*   Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    T.showShort("添加数据成功，返回objectId为：" + objectId);
                } else {
                    T.showShort("创建数据失败：" + e.getMessage());
                    L.e(e.getErrorCode()+"");
                }
            }
        });*/
    }

    @Override
    public void showBanner(final List<MultiTypeBean.ObjBean.BannerListBean> bean) {
        //轮播图
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setMargin(0, 10, 0, 10);
        adapters.add(new BannerAdapter(this, new LinearLayoutHelper(), 1, viewPool, bean));
    }

    @Override
    public void showAdList(List<MultiTypeBean.ObjBean.AdHomePageListBean> bean) {
        //grid布局
        GridLayoutHelper helper = new GridLayoutHelper(1);
        helper.setAspectRatio(2f);//宽/高比例
        //helper.setColWeights(new float[]{40, 20, 30, 30});//比重
        // helper.setMargin(0, 10, 0, 10);
        helper.setGap(10);//间隔
        adapters.add(new GridAdpter(this, helper, 8, bean));
    }

    @Override
    public void showRdList(List<MultiTypeBean.ObjBean.RdProductListBean> bean) {

    }

    @Override
    public void showBrandList(List<MultiTypeBean.ObjBean.BrandHomePageListBean> bean) {
        //通栏布局
        SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
        layoutHelper.setBgColor(Color.rgb(135, 225, 90));
        layoutHelper.setAspectRatio(4);
        layoutHelper.setMargin(10, 20, 10, 20);
        layoutHelper.setPadding(10, 10, 10, 10);
        adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 100)));

    }

    @Override
    public void onSuccess() {
        delegateAdapter.setAdapters(adapters);
        //        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment        fragment        = fragmentManager.findFragmentByTag(OneFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(OneFragment.TAG);
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
