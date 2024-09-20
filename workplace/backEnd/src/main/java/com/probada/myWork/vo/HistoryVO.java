package com.probada.myWork.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HistoryVO {
	private String historyNo;
	private String msg;
	private String url;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String projNo;
	private String dist;
	private String userId;
	private String act;
	
	public String getHistoryNo() {
		return historyNo;
	}
	public void setHistoryNo(String historyNo) {
		this.historyNo = historyNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
}
