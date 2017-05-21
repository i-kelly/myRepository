package com.example.model;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model
 *  @文件名:   IResult
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 14:57
 *  @描述：    解析后回调
 */

public interface IResult<T> {

    void onBefore(int tag);

    void onSuccess(T bean, int tag);

    void onFailure(String msg, int tag);

    void onAfter(int tag);

}
