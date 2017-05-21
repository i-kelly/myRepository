package com.example.model.helper;

import android.os.Handler;
import android.os.Looper;

import com.example.R;
import com.example.base.BaseApplication;
import com.example.base.BaseMainApp;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.DetectTool;
import util.L;
import util.T;


public class OkHttpHelper {
    private static OkHttpHelper okHttpHelper;

    private static OkHttpClient okHttpClient;

    private Handler handler;

    private OkHttpHelper() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(60,
                                                                                 TimeUnit.SECONDS).writeTimeout(
                30,
                TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();

        handler = new Handler(Looper.getMainLooper());

    }

    public static synchronized OkHttpHelper getInstance() {
        if (null == okHttpHelper) {
            okHttpHelper = new OkHttpHelper();
        }
        return okHttpHelper;
    }

    /**
     * get方式联网请求数据
     * @param relativeUrl
     * @param params
     * @param baseCallBack
     */
    public void get(String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequest(getAbsoluteUrl(relativeUrl), params, HttpMethodType.GET);
            doRequest(request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * post方式联网请求数据
     * @param relativeUrl
     * @param params
     * @param baseCallBack
     */
    public void post(String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequest(getAbsoluteUrl(relativeUrl),
                                           params,
                                           HttpMethodType.POST);
            doRequest(request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 不加密post请求
     * @param relativeUrl
     * @param params
     * @param baseCallBack
     */
    public void postNoEncrypt(String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequestNo(getAbsoluteUrl(relativeUrl),
                                             params,
                                             HttpMethodType.POST);
            doRequest(request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Request buildRequestNo(String url,
                                          JSONObject params,
                                          HttpMethodType httpMethodType) {
        try {

            Request.Builder builder = new Request.Builder();

            if (httpMethodType == HttpMethodType.GET) {
                if (!url.endsWith("?")) {
                    url += "?";
                }
                if (null != params) {
                    Iterator<String> it = params.keys();
                    while (it.hasNext()) {
                        String key = it.next();
                        url += key + "=" + params.getString(key) + "&";
                    }
                    url = url.substring(0, url.length() - 1);
                }
                builder.url(url);
                builder.get();
                L.i("OkHttp GET url:" + url);

            } else if (httpMethodType == HttpMethodType.POST) {
                FormBody.Builder encodingBuilder = new FormBody.Builder();
                String           relativeUrl     = "";
                if (null != params) {
                    Iterator<String> it = params.keys();
                    while (it.hasNext()) {
                        String key = it.next();
                        encodingBuilder.add(key, params.getString(key));
                        relativeUrl += key + "=" + params.getString(key) + "&";
                    }
                    relativeUrl = relativeUrl.substring(0, relativeUrl.length() - 1);
                }

                RequestBody body = encodingBuilder.build();

                builder.url(url);
                builder.post(body);

                L.i("OkHttp POST url:" + url + "?" + relativeUrl);
            }
            return builder.build();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void doRequest(final Request request, final BaseCallBack baseCallBack) {

        baseCallBack.requestBefore(request);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                baseCallBack.onFailure(request, e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    baseCallBack.onResponse(response);
                    if (response.isSuccessful()) {
                        String resStr = response.body().string();
                        Logger.json(resStr);
                        callBackSuccess(baseCallBack, resStr);
                    } else {
                        callBackError(response, baseCallBack);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (response.body() != null) { response.body().close(); }
                }
            }
        });
    }

    /**
     * 根据网络请求方式构建联网请求时所需的request
     *
     * @param httpMethodType 联网请求方式
     * @return 联网所需的request
     */
    private Request buildRequest(String url, JSONObject params, HttpMethodType httpMethodType)
            throws Exception {

        HashMap<String, String> map      = new HashMap<String, String>();
        JSONObject              paObject = getPaObject(params);
        Iterator<String>        it       = paObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            map.put(key, paObject.getString(key));
        }
        L.i("params: " + paObject);
        /****************数据加密*****************/
        String si = DetectTool.getSign(map);
        String pa = DetectTool.getPara(paObject);
        /****************数据加密*****************/

        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (httpMethodType == HttpMethodType.GET) {
            url += "si=" + si + "&pa=" + pa;
            builder.url(url);
            builder.get();
            L.i("OkHttp GET url:" + url);

        } else if (httpMethodType == HttpMethodType.POST) {


            FormBody.Builder encodingBuilder = new FormBody.Builder();

            encodingBuilder.add("si", si);
            encodingBuilder.add("pa", pa);

            RequestBody body = encodingBuilder.build();


            builder.url(url);
            builder.post(body);

            L.i("OkHttp POST url:" + url + "?si=" + si + "&pa=" + pa);

        }

        return builder.build();
    }


    private void callBackSuccess(final BaseCallBack baseCallBack, final String resStr) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onSuccess(resStr);
            }
        });
    }

    private void callBackFail(final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                T.showShort(msg);
            }
        });
    }

    private void callBackError(final Response response, final BaseCallBack baseCallBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                switch (response.code()) {
                    case 500:
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_server_error));
                        break;
                    case 404:
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_no_param));
                        break;
                    case 400:
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_param_error));
                        break;
                    case 403:
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_no_permission));
                        break;
                    case 10000:  //保存的用户信息过期
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_user_info_outtime));
                        //reLogin(activity);
                        break;
                    case 10001:  //没有操作权限
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_no_permission));
                        break;
                    default:
                        T.showShort(BaseApplication.getInstance().getString(R.string.error_call_back_no_error));
                        break;
                }

                baseCallBack.onError(response, new Exception("httpError"));
            }
        });
    }

    /**
     * 根据相对路径获取全路径
     *
     * @param relativeUrl 相对路径
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BaseMainApp.getInstance().getMainHostUrl(BaseApplication.getInstance()) + relativeUrl;
    }

    private static JSONObject getPaObject(JSONObject params)
            throws JSONException {
        if (null == params) {
            params = new JSONObject();
        }
        params.put("m", DetectTool.getType());
        params.put("u", DetectTool.getToken());
        params.put("v", DetectTool.getVersionName());
        params.put("i", DetectTool.getIMEI(BaseApplication.getInstance()));
        params.put("t", DetectTool.getTS());
        return params;
    }

    /**
     * 枚举，联网请求的联网方式
     */
    enum HttpMethodType {
        GET,
        POST
    }
}