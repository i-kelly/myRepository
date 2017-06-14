package com.example.model.helper;


import com.example.admin.constants.API;
import com.example.admin.constants.Constants;
import com.example.model.bean.ReqBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-03-20 15:39
 * @desc 接口管理类
 */
public class ApiManager

{


    public static Map<Integer, String> sUrlMap = new HashMap();

    static {
        sUrlMap.put(Constants.EXAMPLE, API.EXAMPLE_URL);
        sUrlMap.put(Constants.SIIGEE, API.SIIGEE_URL);
    }

    /**
     * 请求实体参数
     * @param bean
     * @return
     */
    public static JSONObject getRequestParams(ReqBean bean) {

        Gson       gson   = new Gson();
        String     json   = gson.toJson(bean);
        JSONObject params = null;
        try {
            params = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }


    public static JSONObject getExampleParams(String key,
                                              String value) {
        JSONObject params = new JSONObject();
        try {
            params.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}
