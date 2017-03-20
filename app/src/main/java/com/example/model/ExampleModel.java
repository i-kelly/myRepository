package com.example.model;

import com.example.model.helper.ApiManager;
import com.example.model.helper.HUDCallBack;
import com.example.model.helper.OkHttpHelper;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:35
 * @desc TODO 类作用
 */
public class ExampleModel
        extends BaseModel
{
    private static final String TAG = "ExampleModel";

    /**
     * 获取网络数据
     * @param callBack
     */
    public void getNetData(String id, HUDCallBack callBack) {
        OkHttpHelper.getInstance()
                    .post(ApiManager.EXAMPLE_URL, ApiManager.getExampleParams(id), callBack);
    }

    /**
     * 获取本地数据
     */
    public void getLocalData() {

    }
}
