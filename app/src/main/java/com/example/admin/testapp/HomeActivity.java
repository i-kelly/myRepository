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

package com.example.admin.testapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
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

import java.util.LinkedList;
import java.util.List;

/**
 * @author villadora
 */
public class HomeActivity extends Activity {

    private static final boolean BANNER_LAYOUT = true;

    private static final boolean FIX_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean SINGLE_LAYOUT = true;

    private static final boolean FLOAT_LAYOUT = true;

    private static final boolean ONEN_LAYOUT = true;

    private static final boolean COLUMN_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean STAGGER_LAYOUT = true;


    private Runnable trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_view);

//        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText position = (EditText) findViewById(R.id.position);
//                if (!TextUtils.isEmpty(position.getText())) {
//                    try {
//                        int pos = Integer.parseInt(position.getText().toString());
//                        recyclerView.scrollToPosition(pos);
//                    } catch (Exception e) {
//                        Log.e("VlayoutActivity", e.getMessage(), e);
//                    }
//                } else {
//                    recyclerView.requestLayout();
//                }
//            }
//        });

        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
//                mFirstText.setText("First: " + layoutManager.findFirstVisibleItemPosition());
//                mLastText.setText("Existing: " + MainViewHolder.existing + " Created: " + MainViewHolder.createdTimes);
//                mCountText.setText("Count: " + recyclerView.getChildCount());
//                mTotalOffsetText.setText("Total Offset: " + layoutManager.getOffsetToStart());
            }
        });


        recyclerView.setLayoutManager(layoutManager);

        // layoutManager.setReverseLayout(true);

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };


        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        // recyclerView.addItemDecoration(itemDecoration);

        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();


        if (BANNER_LAYOUT) {
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
                                LayoutInflater.from(HomeActivity.this).inflate(R.layout.view_pager, parent, false));

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

//        {
//            GridLayoutHelper helper = new GridLayoutHelper(10);
//            helper.setAspectRatio(4f);
//            helper.setGap(10);
//            adapters.add(new SubAdapter(this, helper, 80));
//        }

        if (FLOAT_LAYOUT) {
            FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
            layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
            layoutHelper.setDefaultLocation(100, 400);
            VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(150, 150);
            adapters.add(new SubAdapter(this, layoutHelper, 1, layoutParams));
        }

        if (LINEAR_LAYOUT) {
            LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
            layoutHelper1.setAspectRatio(2.0f);
            LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
            layoutHelper2.setAspectRatio(4.0f);
            layoutHelper2.setDividerHeight(10);
            layoutHelper2.setMargin(10, 30, 10, 10);
            layoutHelper2.setPadding(10, 30, 10, 10);
            layoutHelper2.setBgColor(0xFFF5A623);
            adapters.add(new SubAdapter(this, layoutHelper1, 1));
            adapters.add(new SubAdapter(this, layoutHelper2, 6) {

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (position % 2 == 0) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = 5;
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            layoutHelper.setOffset(100);
            layoutHelper.setAspectRatio(4);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (SINGLE_LAYOUT) {
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            layoutHelper.setBgColor(Color.rgb(135, 225, 90));
            layoutHelper.setAspectRatio(4);
            layoutHelper.setMargin(10, 20, 10, 20);
            layoutHelper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (COLUMN_LAYOUT) {
            ColumnLayoutHelper layoutHelper = new ColumnLayoutHelper();
            layoutHelper.setBgColor(0xff00f0f0);
            layoutHelper.setWeights(new float[]{40.0f, Float.NaN, 40});
            adapters.add(new SubAdapter(this, layoutHelper, 5) {

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (position == 0) {
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                        layoutParams.mAspectRatio = 4;
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                }

            });
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setAspectRatio(4.0f);
            helper.setColWeights(new float[]{40f, 45f});
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 2));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xffef8ba3);
            helper.setAspectRatio(2.0f);
            helper.setColWeights(new float[]{40f});
            helper.setRowWeight(30f);
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 4) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams lp = (VirtualLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                    if (position == 0) {
                        lp.rightMargin = 1;
                    } else if (position == 1) {

                    } else if (position == 2) {
                        lp.topMargin = 1;
                        lp.rightMargin = 1;
                    }
                }
            });
        }

        if (ONEN_LAYOUT) {
            adapters.add(new SubAdapter(this, new OnePlusNLayoutHelper(), 0));
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff87e543);
            helper.setAspectRatio(1.8f);
            helper.setColWeights(new float[]{33.33f, 50f, 40f});
            helper.setMargin(10, 20, 10, 20);
            helper.setPadding(10, 10, 10, 10);
            VirtualLayoutManager.LayoutParams lp = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            adapters.add(new SubAdapter(this, helper, 3, lp) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams lp = (VirtualLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                    if (position == 0) {
                        lp.rightMargin = 1;
                    }
                }
            });
        }

        if (COLUMN_LAYOUT) {
            adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 0));
            adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 4));
        }

        if (FIX_LAYOUT) {
            FixLayoutHelper layoutHelper = new FixLayoutHelper(10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 0));

            layoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 20, 20);

            adapters.add(new SubAdapter(this, layoutHelper, 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper(false);
            adapters.add(new SubAdapter(this, layoutHelper, 0));
            layoutHelper = new StickyLayoutHelper(false);
            layoutHelper.setOffset(100);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }

        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper = new GridLayoutHelper(2);
            layoutHelper.setMargin(7, 0, 7, 0);
            layoutHelper.setWeights(new float[]{46.665f});
            layoutHelper.setHGap(3);
            adapters.add(new SubAdapter(this, layoutHelper, 2));

            layoutHelper = new GridLayoutHelper(4);
            layoutHelper.setWeights(new float[]{20f, 26.665f});
            layoutHelper.setMargin(7, 0, 7, 0);
            layoutHelper.setHGap(3);
            adapters.add(new SubAdapter(this, layoutHelper, 8));
        }


        if (GRID_LAYOUT) {
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
                    // lp.bottomMargin = 1;
                    // lp.rightMargin = 1;
                }
            });
        }

        if (FIX_LAYOUT) {
            adapters.add(new SubAdapter(this, new ScrollFixLayoutHelper(20, 20), 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(200, 200);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (LINEAR_LAYOUT)
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 10));

        if (GRID_LAYOUT) {
            GridLayoutHelper helper = new GridLayoutHelper(3);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 3));
        }

        if (STAGGER_LAYOUT) {
            // adapters.add(new SubAdapter(this, new StaggeredGridLayoutHelper(2, 0), 0));
            final StaggeredGridLayoutHelper helper = new StaggeredGridLayoutHelper(2, 10);
            helper.setMargin(20, 10, 10, 10);
            helper.setPadding(10, 10, 20, 10);
            helper.setBgColor(0xFF86345A);
            adapters.add(new SubAdapter(this, helper, 27) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                    if (position % 2 == 0) {
                        layoutParams.mAspectRatio = 1.0f;
                    } else {
                        layoutParams.height = 340 + position % 7 * 20;
                    }
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (COLUMN_LAYOUT) {
            // adapters.add(new SubAdapter(this, new ColumnLayoutHelper(), 3));
        }

        if (GRID_LAYOUT) {
            // adapters.add(new SubAdapter(this, new GridLayoutHelper(4), 24));
        }

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

    // RecyclableViewPager

    static class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {
        public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public void onBindViewHolder(MainViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

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
}