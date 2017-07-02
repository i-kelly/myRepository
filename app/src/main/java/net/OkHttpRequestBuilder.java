package net;

import java.util.LinkedHashMap;
import java.util.Map;

import util.DetectTool;
import util.GsonUtil;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {
    protected String              url;
    protected Object              tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int                 id;

    public T id(int id) {
        this.id = id;
        return (T) this;
    }

    public T url(String url) {
        this.url = url;
        return (T) this;
    }


    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addHeader(String key,
                       String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return (T) this;
    }

    public abstract RequestCall build();

    public OkHttpRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public OkHttpRequestBuilder addParams(String key,
                                          String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    /**
     * 加密后入参
     * @param par 无加密参数
     * @return this
     */
    public OkHttpRequestBuilder addParamsEncry(Map<String, String> par) {
        if (par == null) {
            par = new LinkedHashMap<>();
        }
        par.put("m", DetectTool.getType());
        par.put("u", DetectTool.getToken());
        par.put("v", DetectTool.getVersionName());
        par.put("i", DetectTool.getIMEI());
        par.put("t", DetectTool.getTS());

        String si = DetectTool.getSign(par);
        String pa = DetectTool.getPara(GsonUtil.mapToJson(par));
        addParams("si", si);
        addParams("pa", pa);
        return this;
    }
}
