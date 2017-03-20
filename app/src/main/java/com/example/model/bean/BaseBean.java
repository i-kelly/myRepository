package com.example.model.bean;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.bean
 *  @文件名:   BaseBean
 *  @创建者:   Admin
 *  @创建时间:  2017/3/20 22:45
 *  @描述：    基类bean
 */

import com.google.gson.Gson;

public class BaseBean<T extends Entity> {

    /**
     * message : 提交成功
     * data : {"state":"0","obj":{"expiryNumber":"","nonProfitFound":"098195.00","isPopup":"0"},"msg":"查询成功"}
     * code : 000
     */

    public String message;
    public T      data;
    public String code;

    public static BaseBean objectFromData(String str) {

        return new Gson().fromJson(str, BaseBean.class);
    }
}
