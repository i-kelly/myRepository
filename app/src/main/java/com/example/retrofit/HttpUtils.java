package com.example.retrofit;

import android.os.Environment;

import com.example.BuildConfig;
import com.example.base.BaseApplication;

import net.OkHttpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import util.SystemUtil;

/**
 * Created by codeest on 2017/2/26.
 */

public class HttpUtils {

    //================= PATH ====================

    public static final String PATH_DATA = BaseApplication.getInstance()
                                                          .getCacheDir()
                                                          .getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory()
                                                        .getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";
    private volatile static HttpUtils mInstance;

    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    Retrofit provideMyRetrofit() {
        return createRetrofit(provideRetrofitBuilder(), provideClient(provideOkHttpBuilder()),
                              Apis.HOST);
    }

    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File  cacheFile = new File(PATH_CACHE);
        Cache cache     = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain)
                    throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                                     .cacheControl(CacheControl.FORCE_CACHE)
                                     .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control",
                                    "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //        Interceptor apikey = new Interceptor() {
        //            @Override
        //            public Response intercept(Chain chain) throws IOException {
        //                Request request = chain.request();
        //                request = request.newBuilder()
        //                        .addHeader("apikey",Constants.KEY_API)
        //                        .build();
        //                return chain.proceed(request);
        //            }
        //        }
        //        设置统一的请求头部参数
        //        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    public Apis provideMyService() {
        return provideMyRetrofit().create(Apis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder,
                                    OkHttpClient client,
                                    String url) {
        return builder.baseUrl(url)
                      .client(client)
                      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
    }
}
