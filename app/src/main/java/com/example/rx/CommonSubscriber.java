package com.example.rx;

import android.text.TextUtils;

import com.example.base.IView;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;
import util.L;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private IView  mView;
    private String mErrorMsg;

    protected CommonSubscriber(IView view){
        this.mView = view;
    }

    protected CommonSubscriber(IView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null)
            return;
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showError(mErrorMsg);
        } else if (e instanceof Exception) {
            mView.showError(e.toString());
        } else if (e instanceof HttpException) {
            mView.showError("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showError("未知错误ヽ(≧Д≦)ノ");
            L.d(e.toString());
        }
    }
}
