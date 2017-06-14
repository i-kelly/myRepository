package com.example.base;

import android.support.annotation.UiThread;

/**
 * View基类
 */
public interface IView {

    @UiThread
    void onSuccess();

    @UiThread
    void showError(String msg);

    @UiThread
    void showLoading();

    @UiThread
    void hideLoading();


}
