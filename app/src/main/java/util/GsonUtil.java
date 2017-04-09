package util;

/*
 *  @项目名：  myRepository 
 *  @包名：    util
 *  @文件名:   GsonUtil
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 13:49
 *  @描述：    Json转换工具类
 */

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtil() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
        }
        return map;
    }

    /**
     * 获取泛型类型
     * @param subclass
     * @return
     */
    public static Type getSuperclassTypeParmaeter(Class<?> subclass, Class cls) {
        Type superClass = subclass.getGenericSuperclass();

        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parmaeter... ");
        }
        ParameterizedType parameterize = (ParameterizedType) superClass;
        return $Gson$Types.canonicalize(parameterize.getActualTypeArguments()[0]);
    }

    public static <T> T GsonToBean(String gsonString, @NonNull Type type) {
        T target = null;
        if (gson != null) {
            target = gson.fromJson(gsonString, type);
        }

        return target;
    }

}
