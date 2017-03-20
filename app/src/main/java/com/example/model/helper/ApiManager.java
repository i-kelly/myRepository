package com.example.model.helper;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * @version V1.0
 * @project:MyApplication
 * @author: Admin
 * @date: 2017-03-20 15:39
 * @desc TODO 类作用
 */
public class ApiManager
        implements BaseApiManager
{
    private static final String TAG = "ApiManager";

    public static final String EXAMPLE_URL = "p/p_001";

    public static JSONObject getExampleParams(String id) {
        JSONObject params = new JSONObject();
        try {
            params.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}
