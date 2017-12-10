/**
 * 
 */
package com.chamki.ssm.service;

import com.chamki.ssm.model.PayRecord;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:43:55
 */
public interface PayRecordService {

	void insert (PayRecord payRecord);
	
	PayRecord get(Long outTradeNo);
	
	void update(PayRecord payRecord);
}
