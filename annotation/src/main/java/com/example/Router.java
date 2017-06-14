package com.example;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example
 *  @文件名:   Router
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 12:18
 *  @描述：    路由
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Router {
    String value();
}
