package com.example.base;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.base
 *  @文件名:   MvpPresent
 *  @创建者:   Admin
 *  @创建时间:  2017/5/21 21:07
 *  @描述：    mvppresent
 */

import android.support.annotation.UiThread;

import com.example.present.IPresenter;

import java.lang.ref.WeakReference;

public class MvpPresent<T extends IView>
        implements IPresenter<T> {

    private WeakReference<T> viewRef;

    @UiThread
    @Override
    public void attachView(T view) {
        viewRef = new WeakReference<T>(view);
    }

    @UiThread
    public T getView() {
        return viewRef == null
               ? null
               : viewRef.get();
    }

    @UiThread
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }
}
