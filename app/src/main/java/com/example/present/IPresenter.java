package com.example.present;

import com.example.base.IView;

/**
 * Presenter基类
 */
public interface IPresenter<T extends IView>{

    void attachView(T view);

    void detachView();
}
