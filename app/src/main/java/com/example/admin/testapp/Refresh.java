package com.example.admin.testapp;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 11:46
 * @desc 刷新注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EventBase(
        listenerType = View.OnClickListener.class,
        listenerSetter = "setOnClickListener",
        methodName = "onRefresh")
public @interface Refresh {

    int[] value();

    int[] parentId() default {0};
}

