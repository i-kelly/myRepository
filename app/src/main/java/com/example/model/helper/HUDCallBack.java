package com.example.model.helper;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.helper
 *  @文件名:   HUDCallBack
 *  @创建者:   Admin
 *  @创建时间:  2017/5/21 20:27
 *  @描述：    网络请求回调实现
 */

import com.example.base.BaseApplication;
import com.example.base.BaseMainApp;
import com.example.model.IResult;

import java.lang.reflect.Type;

import util.GsonUtil;


public abstract class HUDCallBack<T> {
    public int     mFlag;
    public IResult mIResult;
    public Type    mType;

    public HUDCallBack(IResult IResult,
                       int flag) {
        this.mIResult = IResult;
        this.mFlag = flag;
        mType = GsonUtil.getSuperclassTypeParmaeter(getClass());
    }

    public abstract void onSuccess(T bean);

    public void onBefore() {
        if (mIResult != null) { mIResult.onBefore(mFlag); }
    }

    public void onAfter() {
        mIResult.onAfter(mFlag);
    }

    public void onFailure(String msg) {
        if (mIResult != null) { mIResult.onFailure(msg, mFlag); }
    }

    public String getRelativeUrl() {
        return ApiManager.sUrlMap.get(mFlag);
    }

    public String getAbsoluteUrl() {
        return BaseMainApp.getInstance()
                          .getMainHostUrl(BaseApplication.getInstance()) + ApiManager.sUrlMap.get(
                mFlag);
    }
}
