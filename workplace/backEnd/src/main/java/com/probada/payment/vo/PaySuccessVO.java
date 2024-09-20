package com.probada.payment.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaySuccessVO {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private String userId = "";
	private String payMethod = "";
	private String status = "";
	private String planName = "";
	private int price = 0;
	private Date paidDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
	
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPaidDate() {
		return sdf.format(paidDate);
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
}
