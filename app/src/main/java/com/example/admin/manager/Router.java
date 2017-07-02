package com.example.admin.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.CheckLogin;
import com.example.admin.activity.ExampleActivity;
import com.example.admin.activity.MainActivity;
import com.example.admin.activity.MultiTypeActivity;
import com.example.base.InitializeService;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-17 09:56
 * @desc 路由（页面跳转统一管理）
 */
public class Router {
    private static final String TAG = "Router";

    /**
     * 服务中application启动服务初始化
     *
     * @param context
     */
    public static void startInitializeService(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(InitializeService.ACTION_INIT);
        context.startService(intent);
    }

    /**
     * 跳转example页面
     *
     * @param context
     */
    public static void startExampleActivity(Context context) {
        Intent intent = new Intent(context, ExampleActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转multitype页面
     * @param context
     */
    @CheckLogin
    public static void startMultiTypeActivity(Context context) {
        //        TRouter.go();
        Intent intent = new Intent(context, MultiTypeActivity.class);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(0, 0);
    }

    public static void startMainActivity(Context context) {
        //        TRouter.go();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(0, 0);
    }
}
