package com.example.present;

import com.example.base.BaseApplication;
import com.example.base.BasePresenter;
import com.example.model.helper.BaseCallBack;
import com.example.model.helper.OkHttpHelper;
import com.example.present.contract.ExampleContract;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-16 20:26
 * @desc  present用例
 */
public class ExamplePresent extends BasePresenter<ExampleContract.View> implements ExampleContract.Presenter {
    private static final String TAG = "ExamplePresent";


    public ExamplePresent() {

    }

    @Override
    public void getDetailData(int id) {
        OkHttpHelper.getInstance().post(BaseApplication.getInstance(), "", null, new BaseCallBack<JSONObject>() {
            @Override
            public void requestBefore(Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e) {
                mView.fail();
            }

            @Override
            public void onSuccess(Response response, JSONObject o, int flag) {
                mView.onSuccess();
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                mView.showError("");
            }

            @Override
            public void onResponse(Response response) {
                mView.showContent(null);
            }
        });
    }

    @Override
    public void getExtraData(int id) {

    }


}
