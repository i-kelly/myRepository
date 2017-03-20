package com.example.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import util.SystemUtil;


/**
 * Created by codeest on 2017/2/12.
 */

public class InitializeService extends IntentService {

    public static final String ACTION_INIT = "initApplication";
    private static RefWatcher refWatcher;

    public InitializeService() {
        super("InitializeService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        //初始化日志
        Settings settings = Logger.init(getPackageName());
        settings.logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);

        //初始化错误收集
//        CrashHandler.init(new CrashHandler(getApplicationContext()));
        initBugly();

        //初始化内存泄漏检测
        refWatcher = BuildConfig.DEBUG ? LeakCanary.install(BaseApplication.getInstance()) : RefWatcher.DISABLED;


    }

    private void initBugly() {
        Context                  context     = getApplicationContext();
        String                   packageName = context.getPackageName();
        String                   processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy    = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(context, BaseData.BUGLY_ID, BuildConfig.DEBUG, strategy);
    }

    public static RefWatcher getRefWatcher() {
        return InitializeService.refWatcher;
    }
}
