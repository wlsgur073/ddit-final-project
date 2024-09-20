package com.probada.chat.vo;

public class ChatUserVO {

	
	
	
	private String chatroomNo;
	private String userId;
	
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
	
	@Override
	public String toString() {
		return "ChatUserVO [chatroomNo=" + chatroomNo + ", userId=" + userId + "]";
	}
	
	
	
	
	
	
	
	
}
