/**
 * 
 */
package com.chamki.wxpay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**微信支付请求处理类，包括发送统一下单参数，查询参数等
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:29:16
 */
public class TenpayRequestHandler extends RequestHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(TenpayRequestHandler.class);

	public TenpayRequestHandler(HttpServletRequest request,
	            HttpServletResponse response) {
	        super(request, response);
	    }

	public String createMD5Sign() {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = super.getAllParameters().entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.append("key=" + Constants.API_KEY).substring(0);
		String sign = MD5Util.MD5Encode(params, "utf8");
		return sign.toUpperCase();
	}

	/**
	 * 发送请求
	 * @return
	 */
	public String sendParams() {
		Set<Entry<String, String>> es = super.getAllParameters().entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		StringBuffer sb = new StringBuffer("<xml>");
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append("<" + k + ">" + v + "</" + k + ">");
		}
		sb.append("</xml>");
		String params = sb.substring(0);
		System.out.println("请求参数：" + params);
		String requestUrl = super.getGateUrl();
		System.out.println("请求url：" + requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
System.out.println("请求接口得到的返回值：" + resContent);
			
		}
		return resContent;
	}
	
	/**
	 * 发送预支付请求
	 * @param totalFee 商品总金额
	 * @param nonceStr 随机字符串
	 * @param timeStamp 时间撮
	 * @return
	 */
	public String sendPrepay(String totalFee, String nonceStr, String timeStamp) {
		
		this.setParameter("appid", Constants.APP_ID);
		this.setParameter("body", Constants.BODY);
		this.setParameter("mch_id", Constants.MCH_ID);
		this.setParameter("nonce_str", nonceStr);
		this.setParameter("notify_url", Constants.NOTIFY_URL);
		String outTradeNo = String.valueOf(UUID.next());
		this.setParameter("out_trade_no", outTradeNo);
		this.setParameter("spbill_create_ip", request.getRemoteAddr());
		this.setParameter("time_start", timeStamp);
		this.setParameter("total_fee", totalFee);
		this.setParameter("trade_type", "APP");
		/**
		 * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，最后接上APP_KEY,转化成大写）
		 */
		this.setParameter("sign", this.createMD5Sign());
		this.setGateUrl(Constants.UNIFIED_ORDER_URL);
		String resContent = this.sendParams();
		String prepayId = "";
		Map<String, String> map;
		try {
			map = XMLUtil.doXMLParse(resContent);
			if (map.containsKey("prepay_id"))
				prepayId = map.get("prepay_id");
		} catch (JDOMException | IOException e) {
			logger.warn("解析预支付订单信息失败");
			e.printStackTrace();
		}
		
		return prepayId;
	}
	
	/**
	 * 发送查询请求
	 * @param transactionId 微信订单号
	 * @param outTradeNo 商户系统订单号
	 * @return
	 */
	public Map<String, String> sendQuery(String transactionId, String outTradeNo) {
		this.setParameter("appid", Constants.APP_ID);
		if(transactionId != null) {
			this.setParameter("transaction_id", transactionId);
		}else {
			this.setParameter("out_trade_no", outTradeNo);
		}
		
		this.setParameter("mch_id", Constants.MCH_ID);
		this.setParameter("nonce_str", WXUtil.getNonceStr());
		this.setParameter("sign", this.createMD5Sign());
		String resContent = this.sendParams();
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = XMLUtil.doXMLParse(resContent);
		} catch (JDOMException | IOException e) {
			logger.warn("解析微信服务器返回的支付查询信息失败");
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
}
