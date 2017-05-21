package com.example.admin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ProductCategory;
import com.example.R;
import com.example.admin.manager.Router;
import com.example.admin.testapp.Product;
import com.example.admin.testapp.ProductItemAdapter;
import com.example.base.BaseActivity;
import com.example.model.bean.Person;
import com.example.present.ExamplePresent;
import com.example.present.IPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import util.T;
import widget.MultiStateView;
import widget.ProductCategoryListAdapter;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class MainActivity
        extends BaseActivity
{

    @BindView(R.id.lv_product_category)
    ListView             mCategoryListView;
    @BindView(R.id.lv_product_list)
    PinnedHeaderListView mProductListView;
    @BindView(R.id.multi_state_view)
    MultiStateView       mMultiStateView;

    private ProductCategoryListAdapter mCategoryItemAdapter;
    private ProductItemAdapter         mProductItemAdapter;
    private boolean                    isClickTrigger;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        // 商品分类的列表
        mCategoryItemAdapter = new ProductCategoryListAdapter();
        mCategoryListView.setAdapter(mCategoryItemAdapter);
        mCategoryListView.setSelection(0);
        mCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int productPos = 0;
                for (int index = 0; index < position; index++) {
                    // 加1是因为section也算一个位置
                    productPos += mProductItemAdapter.getCountForSection(index) + 1;
                }
                isClickTrigger = true;
                mProductListView.setSelection(productPos);
            }
        });
        List<ProductCategory> productCategorys = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ProductCategory category = new ProductCategory();
            List<Product>   products = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Product product = new Product();
                product.setName("分类" + i);
                product.setRate(i);
                product.setDescription("描述" + i);
                product.setPrice("" + i);
                products.add(product);
            }
            category.setName("类目" + i);
            category.setProducts(products);
            productCategorys.add(category);
        }

        // 商品的列表
        mProductItemAdapter = new ProductItemAdapter(this);
        //        mProductItemAdapter.setAnimTargetView(mCartLogoImg);
        mProductListView.setAdapter(mProductItemAdapter);
        mProductListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount)
            {
                if (isClickTrigger) {
                    isClickTrigger = false;
                } else {
                    int section = mProductItemAdapter.getSectionForPosition(firstVisibleItem);
                    mCategoryListView.setItemChecked(section, true);
                }
            }
        });

        //        mMultiStateView.setState(MultiStateView.STATE_LOADING);
        mMultiStateView.setState(MultiStateView.STATE_CONTENT);

        mCategoryItemAdapter.setItems(productCategorys);
        mProductItemAdapter.setItems(productCategorys);


    }

    @Override
    protected IPresenter getPresenter() {
        return new ExamplePresent();
    }

    public void test() {
//        Router.startExampleActivity(MainActivity.this);
        Router.startMultiTypeActivity(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test)
    public void onClick() {
        test();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading() {

    }
}
