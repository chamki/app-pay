/**
 * 
 */
package com.chamki.ssm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chamki.ssm.model.AppCustomer;
import com.chamki.ssm.model.PayRecord;
import com.chamki.ssm.service.AppCustomerService;
import com.chamki.ssm.service.PayRecordService;
import com.chamki.wxpay.Constants;
import com.chamki.wxpay.MD5Util;
import com.chamki.wxpay.TenpayRequestHandler;
import com.chamki.wxpay.WXUtil;
import com.chamki.wxpay.XMLUtil;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:36:45
 */
@Controller
public class TenpayController {
	
	private static final Logger logger = LoggerFactory.getLogger(TenpayController.class);

	@Autowired
	private PayRecordService payRecordService;
	
	@Autowired
	private AppCustomerService appCustomerService;

	private String out_trade_no = "";

	/**
	 * 生成预支付订单，获取prepayId
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/wxpay/prepay.jhtml", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取生成预支付订单的请求类
		TenpayRequestHandler prepayReqHandler = new TenpayRequestHandler(request, response);
		String total = request.getParameter("totalFee");
		if(total == null || total.trim().equals("")) {
			logger.warn("参数totalFee为空");
			map.put("code", 2);
			return map;
		}
		int totalCost = (int) (Float.valueOf(total) * 100);
		String totalFee = String.valueOf(totalCost);
		System.out.println("totalFee:" + totalFee);
		
		String nonceStr = WXUtil.getNonceStr();
		String timeStamp = WXUtil.getTimeStamp();
		String prepayid = prepayReqHandler.sendPrepay(totalFee, nonceStr, timeStamp);
		// 若获取prepayid成功，将相关信息返回客户端
		if (prepayid != null && !prepayid.equals("")) {
			PayRecord payRecord = new PayRecord();
			AppCustomer appCustomer = (AppCustomer) request.getSession().getAttribute("appCustomer");
			payRecord.setPhone(appCustomer.getPhone());
			payRecord.setSerialId(Long.valueOf(out_trade_no));
			payRecord.setType((byte) 2);
			payRecord.setGenerateTime(new Date());
			payRecord.setTotalAmount(Float.valueOf(totalFee) / 100);
			payRecordService.insert(payRecord);
			String signs = 
					"appid=" + Constants.APP_ID + 
					"&noncestr=" + nonceStr + 
					"&package=Sign=WXPay"+
					"&prepayid=" + prepayid + 
					"partnerid="+ Constants.MCH_ID+
					"&timestamp=" + timeStamp + 
					"&key="+ Constants.API_KEY;
			map.put("code", 0);
			map.put("info", "success");
			map.put("prepayid", prepayid);
			/**
			 * 签名方式与上面类似
			 */
			map.put("sign", MD5Util.MD5Encode(signs, "utf8").toUpperCase());
			map.put("appid", Constants.APP_ID); //应用ID
			map.put("partnerid", Constants.MCH_ID); //商户号
			map.put("timestamp", timeStamp); // 等于请求prepayId时的time_start
			map.put("noncestr", nonceStr); // 与请求prepayId时值一致
			map.put("package", "Sign=WXPay"); // 固定常量
		} else {
			map.put("code", 1);
			map.put("info", "获取prepayid失败");
		}
		return map;
	}

	/**
	 * 接收微信支付成功通知
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/app/wxpay/notify.jhtml")
	public void getnotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("微信支付回调");
		PrintWriter writer = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		System.out.println("微信支付通知结果：" + result);
		Map<String, String> map = null;
		try {
			/**
			 * 解析微信通知返回的信息
			 */
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=========:" + result);
		// 若支付成功，则告知微信服务器收到通知
		if (map.get("return_code").equals("SUCCESS")) {
			if (map.get("result_code").equals("SUCCESS")) {
				System.out.println("充值成功！");
				PayRecord payRecord = payRecordService.get(Long.valueOf(map.get("out_trade_no")));
				System.out.println("订单号：" + Long.valueOf(map.get("out_trade_no")));
				System.out.println(
						"payRecord.getPayTime():" + payRecord.getPayTime() == null + "," + payRecord.getPayTime());
				// 判断通知是否已处理，若已处理，则不予处理
				if (payRecord.getPayTime() == null) {
					System.out.println("通知微信后台");
					payRecord.setPayTime(new Date());
					String phone = payRecord.getPhone();
					AppCustomer appCustomer = appCustomerService.getByPhone(phone);
					float balance = appCustomer.getBalance();
					balance += Float.valueOf(map.get("total_fee")) / 100;
					appCustomer.setBalance(balance);
					appCustomerService.update(appCustomer);
					payRecordService.update(payRecord);
					String notifyStr = XMLUtil.setXML("SUCCESS", "");
					writer.write(notifyStr);
					writer.flush();
				}
			}
		}
	}
}
