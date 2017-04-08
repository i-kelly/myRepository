package com.example.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.R;
import com.example.base.BaseActivity;
import com.example.base.InitializeService;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.L;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-16 11:33
 * @desc Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initToolbar();
        handleArguments(getArguments());
        initViews(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("onResume：" + getClass().getName());
    }

    protected abstract int getLayoutResId();

    protected void initToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle(getTitle());
            setSupportActionBar(mToolbar);
            if (isShowBack()) {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected void handleArguments(Bundle arguments) {
    }

    protected void initViews(Bundle savedInstanceState) {
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected String getTitle() {
        return null;
    }

    protected boolean isShowBack() {
        return true;
    }

    protected void setTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    protected void setTitle(@StringRes int titleRes) {
        if (mToolbar != null) {
            mToolbar.setTitle(titleRes);
        }
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
    }

    protected ActionBar getSupportActionBar() {
        return ((BaseActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = InitializeService.getRefWatcher();
        if (refWatcher != null) {
            refWatcher.watch(this);
        }
    }
}
