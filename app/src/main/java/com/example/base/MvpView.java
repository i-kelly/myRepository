package com.example.base;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.base
 *  @文件名:   MvpView
 *  @创建者:   Admin
 *  @创建时间:  2017/5/23 23:36
 *  @描述：    v层接口
 */

import com.example.present.IPresenter;

public interface MvpView<T extends IPresenter> {
    T getPresenter();
}
