package com.example.model;

import com.example.model.bean.BaseBean;
import com.example.model.bean.ExampleBean;
import com.example.model.helper.ApiManager;

import org.json.JSONObject;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:35
 * @desc Model用例
 */
public class ExampleModel
        extends BaseModel<BaseBean<ExampleBean>, GetCallBack.GetTaskCallback<ExampleBean>>
{
    private static final String TAG = "ExampleModel";

    @Override
    protected String getRelativeUrl() {
        return ApiManager.Url.EXAMPLE_URL;
    }

    @Override
    protected JSONObject getRequestParams() {
        return ApiManager.getExampleParams(mCallBack.getParams());
    }
}
