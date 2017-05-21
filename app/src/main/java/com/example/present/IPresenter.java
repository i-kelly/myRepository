package com.example.present;

import android.support.annotation.UiThread;

import com.example.base.IView;

/**
 * Presenter基类
 */
public interface IPresenter<T extends IView> {

    @UiThread
    void attachView(T view);

    @UiThread
    void detachView();
}
