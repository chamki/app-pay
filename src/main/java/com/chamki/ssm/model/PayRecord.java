/**
 * 
 */
package com.chamki.ssm.model;

import java.util.Date;

/**
 * @author chamki
 * @version 1.0
 * @createtime 2017-12-06 23:39:29
 */
public class PayRecord {

	
	private Long serialId;
	
	private String phone;
	
	private byte type;
	
	private Date generateTime;
	
	private Float totalAmount;
	
	private Date payTime;

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	
	
}
