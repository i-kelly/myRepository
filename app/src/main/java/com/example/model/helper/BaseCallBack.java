package com.example.model.helper;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:42
 * @desc 获取实体类中泛型
 */
public abstract class BaseCallBack<T> {

    public Type mType;
    public int  flag;

    static Type getSuperclassTypeParmaeter(Class<?> subclass){
        Type superClass = subclass.getGenericSuperclass();

        if (superClass instanceof Class){
           throw  new RuntimeException("Missing type parmaeter... ");
        }

        ParameterizedType parameterize = (ParameterizedType) superClass;
        return  $Gson$Types.canonicalize(parameterize.getActualTypeArguments()[0]);
    }

    public BaseCallBack(){
        mType = getSuperclassTypeParmaeter(getClass());
    }
    public abstract void requestBefore(Request request);
    public abstract void onFailure(Request request, IOException e);

    public abstract void onSuccess(Response response, T t, int flag);
    public abstract void onError(Response response,int code ,Exception e);
    public abstract void onResponse(Response response);

}
