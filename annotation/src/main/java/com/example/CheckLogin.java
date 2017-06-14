package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example
 *  @文件名:   CheckLogin
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 12:20
 *  @描述：    检测登录
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface CheckLogin {}
