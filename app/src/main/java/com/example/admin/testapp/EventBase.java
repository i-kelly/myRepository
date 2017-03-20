package com.example.admin.testapp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 11:59
 * @desc 自定义注解
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    //注解的接口类型
    Class listenerType();

    //set方法的名字
    String listenerSetter();

    //方法的名字，是接口对象调用的方法名
    String methodName();
}
