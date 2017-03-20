package com.example.model;

import com.example.base.BaseApplication;
import com.example.model.helper.BaseCallBack;
import com.example.model.helper.OkHttpHelper;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:35
 * @desc TODO 类作用
 */
public class ExampleModel extends BaseModel {
    private static final String TAG = "ExampleModel";

    public void getNetData(){
        OkHttpHelper.getInstance().post(BaseApplication.getInstance(), "", null, new BaseCallBack<JSONObject>() {
            @Override
            public void requestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e){
            }

            @Override
            public void onSuccess(Response response, JSONObject o, int flag) {
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
            }
        });

    }
}
