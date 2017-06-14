package com.example;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example
 *  @文件名:   Extra
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 13:18
 *  @描述：    TODO
 */
@Retention(CLASS)
@Target(FIELD)
public @interface SceneTransition {
    String value();
}
