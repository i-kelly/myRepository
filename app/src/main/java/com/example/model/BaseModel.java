package com.example.model;

/**
 * @version V1.0
 * @project: MyApplication
 * @author: Admin
 * @date: 2017-03-20 18:33
 * @desc Model基类
 */
public  class BaseModel{
//        implements IModel {
//
//    @Override
//    public void getData(final int flag, final IResult callBack) {
//        OkHttpHelper.getInstance().post(ApiManager.sUrlMap.get(flag),
//                                        callBack.getRequestParams(),
//                                        new BaseCallBack() {
//
//                                            @Override
//                                            public void requestBefore(Request request) {
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Request request, IOException e) {
//                                                callBack.onFailure(request.body().toString());
//                                            }
//
//                                            @Override
//                                            public void onResponse(Response response) {
//                                            }
//
//                                            @Override
//                                            public void onSuccess(String resStr) {
//                                                BaseBean bean = GsonUtil.fromJsonObject(resStr,
//                                                                                        ApiManager.sBeanMap.get(
//                                                                                                flag));
//                                                if (bean != null) { callBack.onSuccess(bean.data); }
//                                            }
//
//                                            @Override
//                                            public void onError(Response response,
//                                                                int code,
//                                                                Exception e) {
//                                                callBack.onFailure(response.body().toString());
//                                            }
//                                        });
//    }

}
