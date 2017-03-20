package com.example.model.bean;

import java.io.Serializable;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:42
 * @desc 基bean
 */
public class BaseBean<T> implements Serializable {
    private static final String TAG = "BaseBean";
    T data;
}
