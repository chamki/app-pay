/**
 * 
 */
package com.chamki.wxpay;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:30:43
 */
public class ClientRequestHandler extends TenpayRequestHandler  {

	public ClientRequestHandler(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	public String getXmlBody() {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = super.getAllParameters().entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				sb.append("<" + k + ">" + v + "<" + k + ">" + "\r\n");
			}
		}
		return sb.toString();
	}
}
