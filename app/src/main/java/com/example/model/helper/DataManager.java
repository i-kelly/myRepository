package com.example.model.helper;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.helper
 *  @文件名:   DataManager
 *  @创建者:   Admin
 *  @创建时间:  2017/5/21 0:42
 *  @描述：    网络请求管理
 */

import com.example.model.bean.BaseBean;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import util.GsonUtil;

public class DataManager {

    private static DataManager manager;

    public static synchronized DataManager getInstance() {
        if (null == manager) {
            manager = new DataManager();
        } return manager;
    }

    public <T> void getData(final HUDCallBack<T> hudCallBack) {
        OkHttpHelper.getInstance()
                    .post(hudCallBack.getRelativeUrl(), new JSONObject(), new BaseCallBack() {
                        @Override
                        public void requestBefore(Request request) {
                            hudCallBack.onBefore();
                        }

                        @Override
                        public void onError(Response response, Exception e) {
                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onFailure(Request request, IOException e) {
                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onResponse(Response response) {
                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onSuccess(String str) {
                            BaseBean<T> bean = GsonUtil.fromJsonObject(str, BaseBean.class,
                                                                       hudCallBack.mType);
                            if (!"000".equals(bean.code)) {
                                hudCallBack.onFailure(bean.message);
                            } else {
                                hudCallBack.onSuccess(bean.data);
                            } hudCallBack.onAfter();
                        }
                    });
    }


}
