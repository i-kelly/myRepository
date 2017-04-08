package com.example.nav;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


import com.example.R;
import com.example.base.BaseFragment;
import com.example.fragment.FourFragment;
import com.example.fragment.OneFragment;
import com.example.fragment.ThreeFragment;
import com.example.fragment.TwoFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Admin on 2017/1/5.
 * 首页底部导航栏
 */
public class NavFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.nav_item_one)
    NavigationButton navItem1;
    @BindView(R.id.nav_item_two)
    NavigationButton navItem2;
    @BindView(R.id.nav_item_three)
    NavigationButton navItem3;
    @BindView(R.id.nav_item_four)
    NavigationButton navItem4;

    private View                         view;
    private Context                      mContext;
    private FragmentManager              mFragmentManager;
    private NavigationButton             mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;
    private int                          mContainerId;
    private NoticeManager                mNoticeManager;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        Class<? extends Fragment>[] cls     = new Class[]{OneFragment.class, TwoFragment.class, ThreeFragment.class, FourFragment.class};
        NavigationButton[]          buttons = {navItem1, navItem2, navItem3, navItem4};
        String[]                    titles  = {"1", "2", "3", "4"};
        String[] icons = {"http://h.hiphotos.baidu.com/news/q%3D100/sign=a8032e3ad033c895a07e9c7be1127397/29381f30e924b8990e26209567061d950a7bf601.jpg",
                "http://d.hiphotos.baidu.com/news/w%3D638/sign=46d7044688cb39dbc1c06455e81709a7/79f0f736afc379319146e685e2c4b74543a9116b.jpg",
                "http://b.hiphotos.baidu.com/news/q%3D100/sign=e5f9e1c99deef01f4b141cc5d0ff99e0/bba1cd11728b4710e864a561cacec3fdfd0323d7.jpg",
                "http://b.hiphotos.baidu.com/news/q%3D100/sign=5de57d30c2ef76093a0b9d9f1edca301/574e9258d109b3decd939dc5c5bf6c81800a4c6a.jpg"};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].init(icons[i], titles[i], cls[i]);
        }
    }


    @OnClick({R.id.nav_item_one, R.id.nav_item_two,
            R.id.nav_item_three, R.id.nav_item_four})
    @Override
    public void onClick(View v) {
        if (v instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) v;
            doSelect(nav);
        }
    }

    public void setup(Context context, FragmentManager fragmentManager, int contentId, OnNavigationReselectListener listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;

        // do clear
        clearOldFragment();

        doSelect(navItem1);
    }

    public void select(MainTab tab) {
        switch (tab) {
            case ONE:
                doSelect(navItem1);
                break;
            case TWO:
                doSelect(navItem2);
                break;
            case THREE:
                doSelect(navItem3);
                break;
            case FOUR:
                doSelect(navItem4);
                break;
        }
    }

    @SuppressWarnings("RestrictedApi")
    private void clearOldFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        List<Fragment>      fragments   = mFragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }

    private void doSelect(NavigationButton newNavButton) {

        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }

    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null && oldNavButton.getFragment().isAdded()) {
                ft.hide(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext,
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.show(newNavButton.getFragment());
            }
        }
        ft.commit();
    }

    /**
     * 重复选择
     *
     * @param navigationButton
     */
    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }


    public void onNoticeArrived(Class<? extends Fragment> clx, int count) {
        if (clx.equals(navItem1.getClx())) {
            navItem1.showRedDot(count);
        } else if (clx.equals(navItem2.getClx())) {
            navItem2.showRedDot(count);
        }
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }


    /**
     * 返回键
     *
     * @return
     */
    public boolean onBackPressed(NavigationButton navItem) {
        if (mCurrentNavButton != navItem) {
            doSelect(navItem);
            return true;
        }
        return false;
    }

    public enum MainTab {
        ONE,
        TWO,
        THREE,
        FOUR,
    }
}
