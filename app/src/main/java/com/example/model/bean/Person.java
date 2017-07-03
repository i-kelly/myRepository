package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   Person
 *  @创建者:   Admin
 *  @创建时间:  2017/5/6 21:06
 *  @描述：    TODO
 */

//import cn.bmob.v3.BmobObject;

public class Person
//        extends BmobObject
{
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
