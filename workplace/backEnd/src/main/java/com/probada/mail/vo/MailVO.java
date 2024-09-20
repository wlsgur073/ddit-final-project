package com.probada.mail.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MailVO {
	private int mailNo = 0;
	private String title;
	private String userTo;
	private String userFrom;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date regDate;
	private String status;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String dist;
	private String recvDel;
	private String sentDel;

	private List<AttachVO> attachList;
	private int memoryCapacity;

	public int getMailNo() {
		return mailNo;
	}
	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserTo() {
		return userTo;
	}
	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getRecvDel() {
		return recvDel;
	}
	public void setRecvDel(String recvDel) {
		this.recvDel = recvDel;
	}
	public String getSentDel() {
		return sentDel;
	}
	public void setSentDel(String sentDel) {
		this.sentDel = sentDel;
	}

	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	public int getMemoryCapacity() {
		return memoryCapacity;
	}
	public void setMemoryCapacity(int memoryCapacity) {
		this.memoryCapacity = memoryCapacity;
	}
	@Override
	public String toString() {
		return "MailVO [mailNo=" + mailNo + ", title=" + title + ", userTo=" + userTo + ", userFrom=" + userFrom
				+ ", content=" + content + ", regDate=" + regDate + ", status=" + status + ", dist=" + dist
				+ ", recvDel=" + recvDel + ", sentDel=" + sentDel + ", attachList=" + attachList + ", memoryCapacity="
				+ memoryCapacity + "]";
	}


}
