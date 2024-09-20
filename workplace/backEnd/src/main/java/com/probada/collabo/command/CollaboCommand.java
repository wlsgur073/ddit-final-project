package com.probada.collabo.command;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CollaboCommand {
	
	private String userId;
	private String projNo;
	private String title;
	
	//콜라보 제안 메일 
	private int mailNo = 0;
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
	
	private List<MultipartFile> attachFile;	//첨부파일
	
	@Override
	public String toString() {
		return "CollaboCommand [userId=" + userId + ", projNo=" + projNo + ", title=" + title + ", mailNo=" + mailNo
				+ ", userTo=" + userTo + ", userFrom=" + userFrom + ", content=" + content + ", regDate=" + regDate
				+ ", status=" + status + ", dist=" + dist + ", recvDel=" + recvDel + ", sentDel=" + sentDel
				+ ", attachFile=" + attachFile + ", getAttachFile()=" + getAttachFile() + ", getMailNo()=" + getMailNo()
				+ ", getUserTo()=" + getUserTo() + ", getUserFrom()=" + getUserFrom() + ", getContent()=" + getContent()
				+ ", getRegDate()=" + getRegDate() + ", getStatus()=" + getStatus() + ", getDist()=" + getDist()
				+ ", getRecvDel()=" + getRecvDel() + ", getSentDel()=" + getSentDel() + ", getUserId()=" + getUserId()
				+ ", getProjNo()=" + getProjNo() + ", getTitle()=" + getTitle() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public List<MultipartFile> getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(List<MultipartFile> attachFile) {
		this.attachFile = attachFile;
	}
	public int getMailNo() {
		return mailNo;
	}
	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
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
	
	
	
	
}
