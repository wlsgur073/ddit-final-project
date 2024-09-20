package com.probada.mail.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.probada.mail.vo.MailVO;

public class TempMailRegistCommand {
	private int mailNo;				//메일번호			
	private String title;			//제목
	private String content;			//내용
	private String userTo;			//받는사람
	private String userFrom;		//보내는사람
	private String dist;			//구분(내게 쓴 메일인지)
	private int[] deleteFile;		//삭제할 파일
	
	private List<MultipartFile> attachFile;	//첨부파일
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	
	public List<MultipartFile> getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(List<MultipartFile> attachFile) {
		this.attachFile = attachFile;
	}
	public int[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	public MailVO toMailVO() {
		MailVO mailVO = new MailVO();
		mailVO.setMailNo(this.mailNo);
		mailVO.setTitle(this.title);
		mailVO.setContent(this.content);
		mailVO.setUserTo(this.userTo);
		mailVO.setUserFrom(this.userFrom);
		mailVO.setDist(this.dist);
		
		return mailVO;
	}
}
