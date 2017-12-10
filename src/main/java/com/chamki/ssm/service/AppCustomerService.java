/**
 * 
 */
package com.chamki.ssm.service;

import com.chamki.ssm.model.AppCustomer;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:45:12
 */
public interface AppCustomerService {

	AppCustomer getByPhone(String phone);
	
	void update(AppCustomer customer);
}
