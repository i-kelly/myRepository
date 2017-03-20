package com.example.base;

import android.app.Activity;
import android.content.Context;


import com.example.R;

import java.util.HashSet;
import java.util.Set;

import util.EmptyUtils;


public class BaseMainApp {

    private static BaseMainApp instance;

    private Set<Activity>  allActivities;

    private String mainHostUrl;


    public static synchronized BaseMainApp getInstance() {
        if (null == instance) {
            instance = new BaseMainApp();
        }

        return instance;

    }



    public String getMainHostUrl(Context mContext) {
        if (EmptyUtils.isEmpty(mainHostUrl)) {
            initHostUrl(mContext);
        }
        return mainHostUrl;
    }

    /**
     * 初始化主机地址
     *
     * @param mContext
     */
    private void initHostUrl(Context mContext) {
        if (null == mContext)
            return;

        setMainHostUrl(mContext);
    }

    private void setMainHostUrl(Context mContext) {
        mainHostUrl = mContext.getString(R.string.main_url);

        if (!mainHostUrl.endsWith("/"))
            mainHostUrl += "/";
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    /**
     * App退出时擦除对象
     */
    public void resetInstance() {
        if (null != instance) {
            instance = null;
        }
    }
}
