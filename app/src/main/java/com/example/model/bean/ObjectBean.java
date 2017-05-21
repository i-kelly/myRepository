package com.example.model.bean;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:31
 * @desc json解析实体类
 */
public class ObjectBean<T>
        extends Entity {
    /**
     * state : 0
     * obj : {"expiryNumber":"","nonProfitFound":"098195.00","isPopup":"0"}
     */

    public String state;
    public T      obj;

}
