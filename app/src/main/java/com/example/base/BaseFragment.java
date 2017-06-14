package com.example.base;

import android.content.Context;
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
import com.example.present.IPresenter;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import util.L;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-02-16 11:33
 * @desc Fragment基类
 */
public abstract class BaseFragment
        extends Fragment
        implements IView, MvpView {
    private static final String TAG = "BaseFragment";


    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private BaseActivity mActivity;
    private Unbinder     mUnBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public final void onViewCreated(View view,
                                    @Nullable Bundle savedInstanceState) {
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
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        RefWatcher refWatcher = InitializeService.getRefWatcher();
        if (refWatcher != null) {
            refWatcher.watch(this);
        }
        super.onDestroy();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            //            activity.onFragmentAttached();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showError(String msg) {
        if (mActivity != null) {
            mActivity.showError(msg);
        }
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public IPresenter getPresenter() {
        return null;
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
