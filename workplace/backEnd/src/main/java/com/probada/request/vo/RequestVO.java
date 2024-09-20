package com.probada.request.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestVO {
	private String reqNo;
	private String userFrom;
	private String userTo;
	private String projNo;
	private String title;
	private String content;
	private String url;
	private String status;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String reqRes = "";
	
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	public String getUserTo() {
		return userTo;
	}
	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getReqRes() {
		return reqRes;
	}
	public void setReqRes(String reqRes) {
		this.reqRes = reqRes;
	}
}
