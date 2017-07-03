package com.example.retrofit;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.retrofit
 *  @文件名:   RetrofitHelper
 *  @创建者:   Admin
 *  @创建时间:  2017/7/3 0:22
 *  @描述：    TODO
 */

import com.example.model.bean.BaseBean;
import com.example.model.bean.MultiTypeBean;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Flowable;
import util.DetectTool;
import util.GsonUtil;

public class RetrofitHelper {
    private Apis mApis;

    public RetrofitHelper(Apis apis) {
        this.mApis = apis;
    }

    public Flowable<BaseBean<MultiTypeBean>> getData() {
        Map<String, String> par = new LinkedHashMap<>();
        par.put("m", DetectTool.getType());
        par.put("u", DetectTool.getToken());
        par.put("v", DetectTool.getVersionName());
        par.put("i", DetectTool.getIMEI());
        par.put("t", DetectTool.getTS());

        String              si   = DetectTool.getSign(par);
        String              pa   = DetectTool.getPara(GsonUtil.mapToJson(par));
        Map<String, String> par2 = new LinkedHashMap<>();
        par2.put("si", si);
        par2.put("pa", pa);
        return mApis.getMultiTypeData(par2);
    }
}
