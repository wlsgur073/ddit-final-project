package com.probada.chat.vo;

import java.util.Date;

public class models {

	
	private String chatroomNo;
	private String userId;
	private String projNo;
	private String title;
	private String regdate;
	private String realRoom;
	
	@Override
	public String toString() {
		return "ChatVO [chatroomNo=" + chatroomNo + ", userId=" + userId + ", projNo=" + projNo + ", title=" + title
				+ ", regdate=" + regdate + ", realRoom=" + realRoom + "]";
	}
	public String getChatroomNo() {
		return chatroomNo;
	}
	public void setChatroomNo(String chatroomNo) {
		this.chatroomNo = chatroomNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRealRoom() {
		return realRoom;
	}
	public void setRealRoom(String realRoom) {
		this.realRoom = realRoom;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
