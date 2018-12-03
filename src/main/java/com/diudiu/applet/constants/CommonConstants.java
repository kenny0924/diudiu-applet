package com.diudiu.applet.constants;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {

	public static final String JWT_KEY_USER_ID = "userId";
	public static final String JWT_KEY_NAME = "name";
	public static final String JWT_KEY_UUID = "uuid";
	public static final String JWT_KEY_TEL = "telPhone";

	/** 版本号key */
	public static final String VERSION_CODE = "VERSION-CODE";

	/** 渠道key */
	public static final String CHANNEL = "Channel";

	/** APP类型key */
	public static final String APP_TYPE = "REQ-SOURCE";

	/** redis key 订单失效渠道 */
	public static final String ORDER_CHANNEL = "__keyevent@1__:expired";
}