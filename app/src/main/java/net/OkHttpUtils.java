package net;


import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/*
 *  @项目名：  myRepository
 *  @包名：    com.example
 *  @文件名:   OkHttpUtils
 *  @创建者:   Admin
 *  @创建时间:  2017/6/3 12:39
 *  @描述：    okhttp网络请求工具类
 */
public class OkHttpUtils {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    public final static int  CONNECT_TIMEOUT      = 60;//设置连接超时时间
    public final static int  READ_TIMEOUT         = 30;//设置读取超时时间
    public final static int  WRITE_TIMEOUT        = 30;//设置写的超时时间
    private volatile static OkHttpUtils  mInstance;
    private                 OkHttpClient mOkHttpClient;
    private                 Platform     mPlatform;

    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                                                  .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                                                  .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                                                  .addInterceptor(new LoggerInterceptor("TAG"))
                                                  .build();
        mPlatform = Platform.get();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public void execute(final RequestCall requestCall,
                        Callback callback) {
        if (callback == null) {
            callback = Callback.CALLBACK_DEFAULT;
        }
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest()
                                  .getId();
        requestCall.getCall()
                   .enqueue(new okhttp3.Callback() {
                       @Override
                       public void onFailure(Call call,
                                             final IOException e) {
                           sendFailResultCallback(call, e, finalCallback, id);
                       }

                       @Override
                       public void onResponse(final Call call,
                                              final Response response) {
                           try {
                               if (call.isCanceled()) {
                                   sendFailResultCallback(call, new IOException("Canceled!"),
                                                          finalCallback, id);
                                   return;
                               }

                               if (!finalCallback.validateReponse(response, id)) {
                                   sendFailResultCallback(call, new IOException(
                                                                  "request failed , reponse's code is : " + response.code()),
                                                          finalCallback, id);
                                   return;
                               }

                               Object o = finalCallback.parseNetworkResponse(response, id);
                               sendSuccessResultCallback(o, finalCallback, id);
                           } catch (Exception e) {
                               sendFailResultCallback(call, e, finalCallback, id);
                           } finally {
                               if (response.body() != null) {
                                   response.body()
                                           .close();
                               }
                           }

                       }
                   });
    }


    public void sendFailResultCallback(final Call call,
                                       final Exception e,
                                       final Callback callback,
                                       final int id) {
        if (callback == null) {
            return;
        }
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object,
                                          final Callback callback,
                                          final int id) {
        if (callback == null) {
            return;
        }
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher()
                                      .queuedCalls()) {
            if (tag.equals(call.request()
                               .tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher()
                                      .runningCalls()) {
            if (tag.equals(call.request()
                               .tag())) {
                call.cancel();
            }
        }
    }
}

