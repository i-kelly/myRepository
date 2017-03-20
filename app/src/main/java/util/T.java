package util;

import android.content.Context;
import android.widget.Toast;

import com.example.base.BaseApplication;

public class T {

	private T(){
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	 public static boolean isShow = true;

	    /**
	     * 短时间显示Toast
	     *
	     * @param message 需要显示的字符串信息
	     */
	    public static void showShort(CharSequence message)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
	    }

	    /**
	     * 短时间显示Toast
	     *
	     * @param message 资源ID
	     */
	    public static void showShort(int message)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
	    }

	    /**
	     * 长时间显示Toast
	     *
	     * @param message 需要显示的字符串信息
	     */
	    public static void showLong( CharSequence message)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
	    }

	    /**
	     * 长时间显示Toast
	     *
	     * @param message 资源ID
	     */
	    public static void showLong(int message)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show();
	    }

	    /**
	     * 自定义显示Toast时间
	     *
	     * @param message 需要显示的字符串信息
	     * @param duration 需要显示的时间，单位毫秒
	     */
	    public static void show( CharSequence message, int duration)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
	    }

	    /**
	     * 自定义显示Toast时间
	     *
	     * @param message 资源ID
	     * @param duration 需要显示的时间，单位毫秒
	     */
	    public static void show( int message, int duration)
	    {
	        if (isShow)
	            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
	    }
}
