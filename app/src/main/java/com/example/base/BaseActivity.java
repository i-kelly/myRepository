package com.example.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.R;
import com.example.present.IPresenter;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import util.L;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-02-15 13:52
 * @desc Activity基类
 */
public abstract class BaseActivity<T extends IPresenter>
        extends AppCompatActivity
        implements IView {
    private static final String TAG = "BaseActivity";
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    protected T            mPresenter;
    private   Unbinder     mUnBinder;
    private   KProgressHUD mKProgressHUD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this); initToolbar(); if (mPresenter == null) {
            mPresenter = getPresenter(); mPresenter.attachView(this);
        } BaseMainApp.getInstance()
                     .addActivity(this); initViews(savedInstanceState);
    }

    protected void initViews(Bundle savedInstanceState) {

    }

    protected abstract T getPresenter();

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume(); L.i("onResume：" + getClass().getName());
    }

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            //getTitle()的值是activity的android:lable属性值
            mToolbar.setTitle(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // App Logo
            //            mToolbar.setLogo(R.drawable.ic_right);
            // Title
            mToolbar.setTitle("My Title");
            //设置主标题颜色
            mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
            //修改主标题的颜色、外观
            mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);
            //设置副标题
            mToolbar.setSubtitle("Subtitle");
            //设置副标题颜色
            mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
            //修改副标题的颜色、外观
            mToolbar.setSubtitleTextAppearance(this, R.style.Theme_ToolBar_Base_Subtitle);
            //设置菜单弹出样式的文本颜色和背景
            mToolbar.setPopupTheme(R.style.PopupMenu);
            mToolbar.inflateMenu(R.menu.menu_example);//设置右上角的填充菜单

            if (isShowBack()) {
                //决定左上角的图标是否可以点击
                getSupportActionBar().setHomeButtonEnabled(true);
                // 给左上角图标的左边加上一个返回的图标
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                //使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                // Navigation Icon 要設定在 setSupoortActionBar 才有作用
                // 否則會出現 back button
                mToolbar.setNavigationIcon(R.mipmap.back);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }

        }
    }

    /**
     * 是否显示返回按钮
     *
     * @return
     */
    protected boolean isShowBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); if (mPresenter != null) { mPresenter.detachView(); } mUnBinder.unbind();
        BaseMainApp.getInstance()
                   .removeActivity(this);
    }


    @Override
    public void onSuccess() {
//        if (mKProgressHUD != null) { mKProgressHUD.dismiss(); }
    }

    @Override
    public void showError(String msg) {
//        util.T.showShort(msg);
    }

    @Override
    public void showLoading() {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("正在加载中...")
                                        .setDetailsLabel("Downloading data")
                                        .setCancellable(true)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
        }
    }
}
