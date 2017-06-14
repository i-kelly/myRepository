package com.example.admin.impl;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.admin.impl
 *  @文件名:   IDataAdapter
 *  @创建者:   Admin
 *  @创建时间:  2017/6/1 22:46
 *  @描述：    列表中分页接口
 */

public interface IDataAdapter<DATA> {

    public abstract void notifyDataChanged(DATA data, boolean isRefresh);

    public abstract DATA getData();

    public boolean isEmpty();
}
