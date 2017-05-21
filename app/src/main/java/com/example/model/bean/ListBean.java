package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   ListBean
 *  @创建者:   Admin
 *  @创建时间:  2017/5/21 22:13
 *  @描述：    TODO
 */

import java.util.List;

public class ListBean<T> {
    public List<T> list;

    public List<T> getList() {
        return list;
    }
}
