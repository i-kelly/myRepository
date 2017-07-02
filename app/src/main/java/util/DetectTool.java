package util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import com.example.base.BaseApplication;
import com.example.base.BaseData;

import org.json.JSONObject;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


/**
 * @author liubp
 * @function 检测工具类
 */
public class DetectTool {
    static TelephonyManager tm;


    public static int getVersionSdk() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取版本名称(非版本号versoinCode)
     *
     * @param act 上下文对象
     * @return
     */
    public static String getVersionCode(Activity act) {

        PackageManager manager = (PackageManager) act.getPackageManager();

        PackageInfo packageInfo = null;

        try {
            packageInfo = manager.getPackageInfo(act.getPackageName(), 0);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if (null != packageInfo) {
            return packageInfo.versionName;
        }

        return null;

    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTS() {
        return System.currentTimeMillis() + "";

    }

    /**
     * 获取手机唯一串号IMEI
     *
     * @return imei
     */
    public static String getIMEI() {
        if (null == tm) {
            tm = (TelephonyManager) BaseApplication.getInstance()
                                                   .getSystemService(Context.TELEPHONY_SERVICE);
        }

        return tm.getDeviceId();

    }


    public static String getSign(Map<String, String> params) {

        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String>        sortedParams = new TreeMap<String, String>(params);
        Set<Entry<String, String>> entrys       = sortedParams.entrySet();


        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Entry<String, String> param : entrys) {
            basestring.append(param.getKey())
                      .append("=")
                      .append(param.getValue());
        }
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        String hex = EncryptUtils.encryptMD5ToString(basestring.toString());
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        //返回md5加密后的字符串注意统一转化为大写
        return hex.toString()
                  .toUpperCase();

    }


    /**
     * 获取凭证(Token)
     *
     * @return
     */
    public static String getToken() {
        return BaseData.UID;
    }

    /**
     * 写死的版本号，对应versionName
     *
     * @return
     */
    public static String getVersionName() {
        return "1.0.0";
    }


    /**
     * 获取设备类型，1-Android，2-IOS
     *
     * @return
     */
    public static String getType() {
        return "1";
    }


    public static String getPara(@NonNull JSONObject paObject) {
        return EncryptUtils.encryptDES2Base64(paObject.toString());
    }

    public static String getPara(@NonNull String paStr) {
        return EncryptUtils.encryptDES2Base64(paStr);
    }
}
