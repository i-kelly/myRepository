package com.example.base;

import android.view.View;

import java.util.List;

public class BaseData {
	public static final String UID = "666";



	//ListView的pagesize，便于统一修改
	public static final int PAGE_SIZE = 10;

	//联网目的
	/**请求*/
	public static final int REQUEST = 1;
	/**提交*/
	public static final int SUBMIT = 2;

	/** 登陆的 */
	public static final int LOGIN = 1000;
	/** 登陆成功的 */
	public static final int LOGIN_SUCCEED = 10002;

	/** 启动注册的 */
	public static final int REGISTER = 10011;
	/** 注册成功的 */
	public static final int REGISTER_SUCCEED = 10012;
	/** 启动找回密码的 */
	public static final int FIND = 10021;
	/** 找回密码成功的 */
	public static final int FIND_SUCCEED = 10022;

	public static final int SERVICE_CYCLES_TIME = 5;//服务联网间隔事件，单位为分钟。

	//这里保存一个ImageView的list以便图片预览的时候调用，因为Intent之间传递这种特殊对象不是很方便，所以用一个全局变量
	public static List<View> imageViews = null;

	//崩溃日志bugly上传
	public static final String BUGLY_ID = "";
}
