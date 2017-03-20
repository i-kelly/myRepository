package com.example.base;

import android.app.Application;

import com.example.admin.manager.Router;


/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-02-15 14:37
 * @desc
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    private static BaseApplication instance;


    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //在子线程中初始化
        Router.startInitializeService(this);
    }
}
