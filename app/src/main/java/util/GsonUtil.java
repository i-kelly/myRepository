package util;

/*
 *  @项目名：  myRepository 
 *  @包名：    util
 *  @文件名:   GsonUtil
 *  @创建者:   Admin
 *  @创建时间:  2017/4/9 13:49
 *  @描述：    Json转换工具类
 */

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
        String gsonString = null; if (gson != null) {
            gsonString = gson.toJson(object);
        } return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null; if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        } return t;
    }

    public static <T> T GsonToBean(String gsonString, Type type) {
        T t = null; if (gson != null) {
            t = gson.fromJson(gsonString, type);
        } return t;
    }

    /**
     * 转成list
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null; if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
        } return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null; if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {}.getType());
        } return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null; if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {}.getType());
        } return map;
    }

    /**
     * 获取泛型类型
     * @param subclass
     * @return
     */
    public static Type getSuperclassTypeParmaeter(Class<?> subclass) {
        Type superClass = subclass.getGenericSuperclass();

        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parmaeter... ");
        } ParameterizedType parameterize = (ParameterizedType) superClass;
        return $Gson$Types.canonicalize(parameterize.getActualTypeArguments()[0]);
    }


    public static <T, K> K fromJsonObject(String gsonString, Class rawClz, Type argType) {
        Type type = new ParameterizedTypeImpl(rawClz, new Type[]{argType});
        return gson.fromJson(gsonString, type);
    }

    public static <T, K> K fromJsonObject(String gsonString, Class rawClz, Class<T> argClz) {
        Type type = new ParameterizedTypeImpl(rawClz, new Class[]{argClz});
        return gson.fromJson(gsonString, type);
    }

    public static <T, K> K fromJsonArray(String gsonString, Class<K> rawClz, Class<T> argClz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{argClz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(rawClz, new Type[]{listType});
        return gson.fromJson(gsonString, type);
    }
}

/**
 * 简易ParameterizedType
 */
class ParameterizedTypeImpl
        implements ParameterizedType {
    private final Class  raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw; this.args = args != null
                                    ? args
                                    : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {return null;}
}

