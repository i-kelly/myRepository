package com.example.model.helper;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:42
 * @desc 请求回调接口
 */
public abstract class BaseCallBack {
    /**
     * 发送请求前
     * @param request
     */
    public abstract void requestBefore(Request request);

    /**
     * 请求失败
     * @param request
     * @param e
     */
    public abstract void onFailure(Request request, IOException e);

    /**
     * 请求成功
     * @param response
     */
    public abstract void onResponse(Response response);

    /**
     * 返回正确数据
     * @param str
     */
    public abstract void onSuccess(String str);

    /**
     * 返回错误数据
     * @param response
     * @param code
     * @param e
     */
    public abstract void onError(Response response, int code, Exception e);


}
