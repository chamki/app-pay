/**
 * 
 */
package com.chamki.wxpay;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:08:56
 */
public class Constants {

	/**
     * 微信开发平台应用ID
     */
    public static final String APP_ID="wx6b0e47323f46f9a1";
    
    /**
     * API密钥
     */
    public static final String API_KEY="GuangZhouShiShaoNianGongAPIKeyWX";
    
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1489932752";
    
    /**
     * 商品描述
     */
    public static final String BODY = "少年宫旧生回读";
    
    /**
     * 获取预支付id的接口url
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    /**
     * 查询订单URL
     */
    public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    
    /**
     * 远程服务器IP
     */
    public static final String REMOTE_IP = "http://112.94.22.2:8082";
    /**
     * 微信服务器回调通知url
     */
    public static final String NOTIFY_URL = REMOTE_IP + "/app/wxpay/notify.jhtml";
}
