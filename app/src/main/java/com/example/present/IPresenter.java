package com.example.present;

import com.example.base.BaseView;

/**
 * Presenter基类
 */
public interface IPresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
