package com.example.retrofit;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.retrofit
 *  @文件名:   RetrofitHelper
 *  @创建者:   Admin
 *  @创建时间:  2017/7/3 0:22
 *  @描述：    TODO
 */

import com.example.model.bean.BaseBean;
import com.example.model.bean.MultiTypeBean;

import io.reactivex.Flowable;

public class RetrofitHelper {
    private Apis mApis;

    public RetrofitHelper(Apis apis) {
        this.mApis = apis;
    }

    public Flowable<BaseBean<MultiTypeBean>> getData() {
        return mApis.getMultiTypeData();
    }
}
