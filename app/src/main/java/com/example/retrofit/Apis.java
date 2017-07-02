package com.example.retrofit;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.retrofit
 *  @文件名:   Apis
 *  @创建者:   Admin
 *  @创建时间:  2017/7/3 0:13
 *  @描述：    TODO
 */

import com.example.base.BaseApplication;
import com.example.base.BaseMainApp;
import com.example.model.bean.BaseBean;
import com.example.model.bean.MultiTypeBean;

import io.reactivex.Flowable;
import retrofit2.http.POST;

public interface Apis {
    String HOST = BaseMainApp.getInstance()
                             .getMainHostUrl(BaseApplication.getInstance())+"p/p2_002";

    /**
     * 技术文章列表
     */
    @POST()
    Flowable<BaseBean<MultiTypeBean>> getMultiTypeData();

}
