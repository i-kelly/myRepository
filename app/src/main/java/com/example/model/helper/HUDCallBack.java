package com.example.model.helper;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 类作用：
 * Created by liubp on 16/1/10.
 */
public abstract class HUDCallBack<T> extends BaseCallBack<T> {


    public Context context;
    //    public KProgressHUD kProgressHUD ;
    private String msg = "";
    private AlertDialog mDialog;

    /**
     * 联网成功的回调
     *
     * @param context 上下文对象
     * @param msg     加载时显示的文字 内容不为空显示为空则不显示
     */
    public HUDCallBack(Context context, int flag, String msg) {
        this.context = context;
//        kProgressHUD = new KProgressHUD(context);
        this.msg = msg;
        this.flag = flag;
    }

    @Override
    public void requestBefore(Request request) {
        showHUD();
    }

    @Override
    public void onFailure(Request request, IOException e) {
        dismissHUD();

    }


    @Override
    public void onResponse(Response response) {
        dismissHUD();
    }

    private void showHUD() {

    }


    private void dismissHUD() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
