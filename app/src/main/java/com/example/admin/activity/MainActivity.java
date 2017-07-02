package com.example.admin.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ProductCategory;
import com.example.R;
import com.example.admin.manager.Router;
import com.example.admin.service.CheckUpdateManager;
import com.example.admin.service.DialogHelper;
import com.example.admin.service.DownloadService;
import com.example.admin.testapp.Product;
import com.example.admin.testapp.ProductItemAdapter;
import com.example.base.BaseActivity;
import com.example.present.ExamplePresent;
import com.example.present.IPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import util.ActivityStack;
import widget.AVLoadingIndicatorView;
import widget.MultiStateView;
import widget.ProductCategoryListAdapter;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;

public class MainActivity
        extends BaseActivity
        implements EasyPermissions.PermissionCallbacks, CheckUpdateManager.RequestPermissions {

    @BindView(R.id.lv_product_category)
    ListView               mCategoryListView;
    @BindView(R.id.lv_product_list)
    PinnedHeaderListView   mProductListView;
    @BindView(R.id.multi_state_view)
    MultiStateView         mMultiStateView;
    @BindView(R.id.bnv_bottom)
    BottomNavigationView   mBottomNavigationItemView;
    @BindView(R.id.load_view)
    AVLoadingIndicatorView loadview;

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
        loadview.show();
        mBottomNavigationItemView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return true;
                    }
                });
        // 商品分类的列表
        mCategoryItemAdapter = new ProductCategoryListAdapter();
        mCategoryListView.setAdapter(mCategoryItemAdapter);
        mCategoryListView.setSelection(0);
        mCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
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
            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
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


     /*   Resources      resources = getResources();
        DisplayMetrics dm        = resources.getDisplayMetrics();
        Configuration  config    = resources.getConfiguration();
        // 应用用户选择语言
        if (!isEN) {
            config.locale = Locale.ENGLISH;
            isEN = true;
        }else {
            config.locale = Locale.SIMPLIFIED_CHINESE;
            isEN = false;
        }
        resources.updateConfiguration(config, dm);

        Router.startMainActivity(MainActivity.this);*/

    }

    public static boolean isEN = false;

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

    @AfterPermissionGranted(0x0100)
    public void requestExternalStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            DownloadService.startService(this, "");
        } else {
            EasyPermissions.requestPermissions(this, "", 0x0100,
                                               Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void call(int version) {

    }

    @Override
    public void onPermissionsGranted(int requestCode,
                                     List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode,
                                    List<String> perms) {
        for (String perm : perms) {
            if (perm.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                DialogHelper.getConfirmDialog(this, "温馨提示", "需要开启开源中国对您手机的存储权限才能下载安装，是否现在开启", "去开启",
                                              "取消", true, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                            }
                        }, null)
                            .show();

            } else {
                //                Setting.updateLocationPermission(getApplicationContext(), false);
            }
        }

    }

    @Override
    public void onBackPressed() {
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (3 * 1000)) {
            ActivityStack.appExit();
        } else {
            mBackPressedTime = curTime;
            Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG)
                 .show();
        }
    }

    private long mBackPressedTime;
}
