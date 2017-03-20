package com.example.nav;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;
import com.example.soulrelay.utils.imageloader.ImageLoaderConfiguration;
import com.example.soulrelay.utils.imageloader.ImageLoaderUtil;


/**
 * Created by Admin on 2017/1/5.
 * 底部导航按钮
 */

public class NavigationButton extends FrameLayout {

    private Fragment mFragment = null;
    private Class<?>  mClx;
    private ImageView mIconView;
    private TextView  mTitleView;
    private TextView  mDot;
    private String    mTag;

    public NavigationButton(Context context) {
        super(context);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.layout_nav_item, this, true);

        mIconView = (ImageView) findViewById(R.id.nav_iv_icon);
        mTitleView = (TextView) findViewById(R.id.nav_tv_title);
        mDot = (TextView) findViewById(R.id.nav_tv_dot);
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mIconView.setSelected(selected);
        mTitleView.setSelected(selected);
    }

    public void showRedDot(int count) {
        mDot.setVisibility(count > 0 ? VISIBLE : GONE);
        mDot.setText(String.valueOf(count));
    }

    public void init(@DrawableRes int resId, @StringRes int strId, Class<?> clx) {
        mIconView.setImageResource(resId);
        mTitleView.setText(strId);
        mClx = clx;
        mTag = mClx.getName();
    }


    public void holdPlace(Class<?> clx) {
        mIconView.setVisibility(GONE);
        mTitleView.setVisibility(GONE);
        mClx = clx;
        mTag = mClx.getName();
    }

    public void init(String iconUrl, String title, Class<?> clx) {
//        ImageLoader.getInstance().displayImage(iconUrl, mIconView, BaseApplication.options);
        ImageLoaderUtil.getInstance().loadImage(iconUrl, new ImageLoaderConfiguration.Builder().build().getPlaceHolder(), mIconView);

        mTitleView.setText(title);
        mClx = clx;
        mTag = mClx.getName();
    }

    public Class<?> getClx() {
        return mClx;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public String getTag() {
        return mTag;
    }
}
