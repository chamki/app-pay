/**
 * 
 */
package com.chamki.wxpay;

import java.util.Random;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:23:25
 */
public class WXUtil {

	/**
	 * 生成随机字符串
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "utf8");
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
