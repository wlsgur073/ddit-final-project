package com.probada.alert.vo;

import java.util.Date;

import com.probada.util.StrFormatTime;

public class AlertVO {
	
	private String userId;
	private String nickName;
	private Date writeTime;
	private String content;
	private String mailFrom;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickName;
	}
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getmailFrom() {
		return mailFrom;
	}
	public void setmailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	
	public String getWriteTimeDisplay() {
		String writeTimeDisplay = "";
		if(this.writeTime != null) {
			writeTimeDisplay = StrFormatTime.formatTimeString(this.writeTime);
//			System.out.println("writeTimeDisplay => " + writeTimeDisplay);
//			writeTimeDisplay = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(this.writeTime);
		}
		return writeTimeDisplay;
	}
	
}
