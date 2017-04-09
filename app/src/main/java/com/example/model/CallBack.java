package com.example.model;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model
 *  @文件名:   CallBack
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 14:57
 *  @描述：    解析后回调
 */

import com.example.model.bean.Entity;


public interface CallBack<T extends Entity> {


    void onSuccess(T bean);

    void onFailure(String msg);
}
