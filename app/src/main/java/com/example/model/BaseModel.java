package com.example.model;

import com.example.model.bean.BaseBean;
import com.example.model.helper.BaseCallBack;
import com.example.model.helper.OkHttpHelper;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;
import util.GsonUtil;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:33
 * @desc Model基类
 */
public abstract class BaseModel<B extends BaseBean, C extends CallBack>
        implements IModel<C>
{
    private static final String TAG  = "BaseModel";
    protected            Type   type = GsonUtil.getSuperclassTypeParmaeter(getClass(),
                                                                           BaseBean.class);
    protected C mCallBack;

    @Override
    public void getData(final C callBack)
    {
        this.mCallBack = callBack;
        OkHttpHelper.getInstance()
                    .post(getRelativeUrl(), getRequestParams(), new BaseCallBack() {

                        @Override
                        public void requestBefore(Request request) {

                        }

                        @Override
                        public void onFailure(Request request, IOException e) {
                            callBack.onFailure(request.body()
                                                      .toString());
                        }

                        @Override
                        public void onResponse(Response response) {
                        }

                        @Override
                        public void onSuccess(String str) {
                            B bean = GsonUtil.GsonToBean(str, type);
                            callBack.onSuccess(bean.data);
                        }

                        @Override
                        public void onError(Response response, int code, Exception e) {
                            callBack.onFailure(response.body()
                                                       .toString());
                        }
                    });
    }


    protected abstract String getRelativeUrl();

    protected abstract JSONObject getRequestParams();

}
