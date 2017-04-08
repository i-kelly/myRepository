package com.example.base;

import com.example.model.BaseModel;
import com.example.present.IPresenter;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:44
 * @desc Presenter基类
 */
public abstract class BasePresenter<T extends BaseView, M extends BaseModel> implements IPresenter<T> {

    protected T mView;
    protected M mModel;

    @Override
    public void attachView(T view) {
        this.mView = view;
        this.mModel = getModel();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    protected abstract M getModel();
}
