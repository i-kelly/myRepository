package com.example.model.helper;

import com.example.R;
import com.example.base.BaseApplication;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:27
 * @desc
 */
public class BaseApi {
    private static final   String TAG     = "BaseApi";
    protected static final String HostUrl = BaseApplication.getInstance().getResources().getString(R.string.main_url);
}
