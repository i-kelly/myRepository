package com.example.model.helper;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.model.helper
 *  @文件名:   DataManager
 *  @创建者:   Admin
 *  @创建时间:  2017/5/21 0:42
 *  @描述：    网络请求管理
 */

import android.util.Log;

import com.example.model.bean.BaseBean;

import net.Callback;
import net.OkHttpUtils;

import org.json.JSONObject;

import java.io.IOException;

import hugo.weaving.DebugLog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import util.GsonUtil;
import util.L;

public class DataManager {

    private static DataManager manager;

    public static synchronized DataManager getInstance() {
        if (null == manager) {
            manager = new DataManager();
        }
        return manager;
    }

    @DebugLog
    public <T> void getData(final HUDCallBack<T> hudCallBack,
                            int tag) {
        OkHttpHelper.getInstance()
                    .post(hudCallBack.getRelativeUrl(), new JSONObject(), new BaseCallBack() {
                        @Override
                        public void requestBefore(Request request) {
                            hudCallBack.onBefore();
                        }

                        @Override
                        public void onError(Response response,
                                            Exception e) {
                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onFailure(Request request,
                                              IOException e) {
                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onResponse(Response response) {
                            //                            hudCallBack.onAfter();
                        }

                        @Override
                        public void onSuccess(String str) {
                            BaseBean<T> bean = GsonUtil.fromJsonObject(str, BaseBean.class,
                                                                       hudCallBack.mType);
                            if (!"000".equals(bean.code)) {
                                hudCallBack.onFailure(bean.message);
                            } else {
                                hudCallBack.onSuccess(bean.data);
                            }
                            hudCallBack.onAfter();
                        }
                    }, tag);
    }

    public void cancelRequest(int tag) {
        OkHttpHelper.getInstance()
                    .cancelTag(tag);
    }

    public <T> void getData2(final HUDCallBack<T> hudCallBack,
                         int tag) {
        OkHttpUtils.getInstance()
                   .post()
                   .addParamsEncry(null)
                   .url(hudCallBack.getAbsoluteUrl())
                   .build()
                   .execute(new Callback<String>() {
                       @Override
                       public String parseNetworkResponse(Response response,
                                                          int id)
                               throws Exception {
                           return response.body()
                                          .string();
                       }

                       @Override
                       public void onError(Call call,
                                           Exception e,
                                           int id) {

                       }

                       @Override
                       public void onResponse(String response,
                                              int id) {
                           Log.e("===========", "onResponse：complete");
                           L.e("onResponse:" + response);
                           BaseBean<T> bean = GsonUtil.fromJsonObject(response, BaseBean.class,
                                                                      hudCallBack.mType);
                           if (!"000".equals(bean.code)) {
                               hudCallBack.onFailure(bean.message);
                           } else {
                               hudCallBack.onSuccess(bean.data);
                           }
                           hudCallBack.onAfter();
                       }
                   });

    }

}
