package com.example.model;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model
 *  @文件名:   MultiTypeModel
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 11:07
 *  @描述：    TODO
 */

import com.example.model.bean.BaseBean;
import com.example.model.bean.MultiTypeBean;
import com.example.model.helper.ApiManager;

import org.json.JSONObject;

public class MultiTypeModel
        extends BaseModel<BaseBean<MultiTypeBean>, GetCallBack.GetTasksCallback<MultiTypeBean>>
{

    @Override
    protected String getRelativeUrl() {
        return ApiManager.Url.SIIGEE_URL;
    }

    @Override
    protected JSONObject getRequestParams() {
        return null;
    }
}
