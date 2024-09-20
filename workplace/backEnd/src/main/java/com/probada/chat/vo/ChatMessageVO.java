package com.probada.chat.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChatMessageVO {

	private String chatroomMsgNo;
	private String userId;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date regdate = new Date();
	private String docContNo;
	private String realRoom;
	private String nickname;
	private String picture;
	
	
	
	
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getChatroomMsgNo() {
		return chatroomMsgNo;
	}
	public void setChatroomMsgNo(String chatroomMsgNo) {
		this.chatroomMsgNo = chatroomMsgNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDocContNo() {
		return docContNo;
	}
	public void setDocContNo(String docContNo) {
		this.docContNo = docContNo;
	}
	public String getRealRoom() {
		return realRoom;
	}
	public void setRealRoom(String realRoom) {
		this.realRoom = realRoom;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "ChatMessageVO [chatroomMsgNo=" + chatroomMsgNo + ", userId=" + userId + ", content=" + content
				+ ", regdate=" + regdate + ", docContNo=" + docContNo + ", realRoom=" + realRoom + ", nickname="
				+ nickname + ", picture=" + picture + "]";
	}
	
	

	
	
	
}

