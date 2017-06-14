package com.example.base;

import com.example.model.IResult;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:44
 * @desc Presenter基类
 */
public abstract class BasePresenter<T extends IView>
        extends MvpPresent<T>
        implements IResult {


    @Override
    public void onBefore(int tag) {
        if (isViewAttached()) {
            getView().showLoading();
        }
    }

    @Override
    public void onAfter(int tag) {
        if (isViewAttached()) {
            getView().hideLoading();
        }
    }

    @Override
    public void onSuccess(Object bean, int tag) {
        if (isViewAttached()) {
            getView().onSuccess();
        }
    }


    @Override
    public void onFailure(String msg, int tag) {
        if (isViewAttached()) {
            getView().showError(msg);
        }
    }
}
