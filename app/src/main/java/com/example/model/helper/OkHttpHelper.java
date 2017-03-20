package com.example.model.helper;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import com.example.R;
import com.example.base.BaseMainApp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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


/**
 * 类作用：okhttp联网
 * Created by liubp on 16/1/10.
 */
public class OkHttpHelper {
    private static OkHttpHelper okHttpHelper;

    private static OkHttpClient okHttpClient;

    private Handler handler;
    private Gson    gson;

    private OkHttpHelper() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();

        gson = new Gson();
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
     */
    public void get(Context context, String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequest(context, getAbsoluteUrl(context, relativeUrl), params, HttpMethodType.GET);
            doRequest(context, request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * post方式联网请求数据
     */
    public void post(Context context, String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequest(context, getAbsoluteUrl(context, relativeUrl), params, HttpMethodType.POST);
            doRequest(context, request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void postNoEncrypt(Context context, String relativeUrl, JSONObject params, BaseCallBack baseCallBack) {
        try {
            Request request = buildRequestNo(context, getAbsoluteUrl(context, relativeUrl), params, HttpMethodType.POST);
            doRequest(context, request, baseCallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Request buildRequestNo(Context context, String url, JSONObject params, HttpMethodType httpMethodType) {
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


    private void doRequest(final Context context, final Request request, final BaseCallBack baseCallBack) {

        baseCallBack.requestBefore(request);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                baseCallBack.onFailure(request, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                baseCallBack.onResponse(response);
                if (response.isSuccessful() && context != null) {
                    try {
                        String resStr = response.body().string();
                        Logger.json(resStr);
                        JSONObject jsonObject = new JSONObject(resStr);
                        if (!jsonObject.getString("code").equals("000")) {
                            callBackFail(context, jsonObject.getString("message"));
                            return;
                        }
                        if (baseCallBack.mType == String.class) {

                            callBackSuccess(response, baseCallBack, resStr);
                        } else if (baseCallBack.mType == JSONObject.class) {

                            callBackSuccess(response, baseCallBack, jsonObject);
                        } else {


                            Object object = null;

                            if ((jsonObject.getJSONObject("data").getString("state")).equals("0")) {
                                String objStr = jsonObject.getJSONObject("data").getString("obj");

                                object = gson.fromJson(objStr, baseCallBack.mType);
                            }
                            callBackSuccess(response, baseCallBack, object);
                        }

                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackError(context, response, baseCallBack);
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
    private Request buildRequest(Context context, String url, JSONObject params, HttpMethodType httpMethodType) throws Exception {

        String si = null;
        String pa = null;

        HashMap<String, String> param = new HashMap<String, String>();
        if (null == params) {
            params = new JSONObject();
        }
        JSONObject paObject = getPaObject(context, params);


        Iterator<String> it = paObject.keys();
        while (it.hasNext()) {
            String key = it.next().toString();
//            param.put(key, DetectTool.chinaToUnicode(paObject.getString(key)));
            param.put(key, paObject.getString(key));
        }
        L.i("params: " + paObject);
        /****************数据加密*****************/
        si = DetectTool.getSign(param);
        pa = DetectTool.getPara(paObject);
        /****************数据加密*****************/

        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (httpMethodType == HttpMethodType.GET) {
            if (null != params) {

                url += "si=" + si + "&pa=" + pa;
            }

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


    private void callBackSuccess(final Response response, final BaseCallBack baseCallBack, final Object object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onSuccess(response, object, baseCallBack.flag);
            }
        });
    }

    private void callBackFail(final Context context, final String msg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                T.showShort(msg);
            }
        });
    }

    private void callBackError(final Context context, final Response response, final BaseCallBack baseCallBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (null != context) {
                    switch (response.code()) {
                        case 500:
                            T.showShort(context.getString(R.string.error_call_back_server_error));
                            break;
                        case 404:
                            T.showShort(context.getString(R.string.error_call_back_no_param));
                            break;
                        case 400:
                            T.showShort(context.getString(R.string.error_call_back_param_error));
                            break;
                        case 403:
                            T.showShort(context.getString(R.string.error_call_back_no_permission));
                            break;
                        case 10000:  //保存的用户信息过期
                            T.showShort(context.getString(R.string.error_call_back_user_info_outtime));
                            //reLogin(activity);
                            break;
                        case 10001:  //没有操作权限
                            T.showShort(context.getString(R.string.error_call_back_no_permission));
                            break;
                        default:
                            T.showShort(context.getString(R.string.error_call_back_no_error));
                            break;
                    }
                }

                baseCallBack.onError(response, 900, new Exception("httpError"));
            }
        });
    }

    /**
     * 根据相对路径获取全路径
     *
     * @param context     上下文对象
     * @param relativeUrl 相对路径
     * @return
     */
    private static String getAbsoluteUrl(Context context, String relativeUrl) {
        return BaseMainApp.getInstance().getMainHostUrl(context) + relativeUrl;
    }

    private static JSONObject getPaObject(Context context, JSONObject params) throws JSONException {
        JSONObject       allDataObject = new JSONObject();
        Iterator<String> it            = params.keys();
        while (it.hasNext()) {
            String key = it.next().toString();
//            allDataObject.put(key, DetectTool.chinaToUnicode(params.getString(key)));
            allDataObject.put(key, params.getString(key));
        }

        String ts = DetectTool.getTS();
        allDataObject.put("m", DetectTool.getType());
        allDataObject.put("u", DetectTool.getToken());
        allDataObject.put("v", DetectTool.getVersionName());
        allDataObject.put("i", DetectTool.getIMEI(context));
        allDataObject.put("t", ts);
        return allDataObject;
    }

    /**
     * 枚举，联网请求的联网方式
     */
    enum HttpMethodType {
        GET, POST
    }
}