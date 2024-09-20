package com.probada.payment.vo;

public class PaymentsPlanVO {

	private String planNo = "";
	private String title = "";
	private int price = 0;
	private int day = 0;
	private int memoryCapacity = 0;
	private int maxUploadCapacity = 0;
	private int userCapacity = 0;
	
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	public int getMaxUploadCapacity() {
		return maxUploadCapacity;
	}
	public void setMaxUploadCapacity(int maxUploadCapacity) {
		this.maxUploadCapacity = maxUploadCapacity;
	}
	public int getUserCapacity() {
		return userCapacity;
	}
	public void setUserCapacity(int userCapacity) {
		this.userCapacity = userCapacity;
	}
	
	
}
