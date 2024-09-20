package com.probada.task.vo;

import java.util.ArrayList;
import java.util.List;

import com.probada.document.vo.FileVO;
import com.probada.user.vo.UserVO;

public class TaskVO {

	private String taskNo;
	private String projNo;
	private String title;
	private String content;
	private String userId;
	private String important;
	private String regdate;
	private String updatedate;
	private String status;
	private String docContNo;
	private String startdate;
	private String enddate;

	// 출력용
	private String projTitle = "";
	private String nickname = "";
	private List<UserVO> userList = new ArrayList<UserVO>();
	private List<FileVO> fileList = new ArrayList<FileVO>();

	private String sessionId = "";

	// spoon용
	private String cprojNo = "";
	
	
	public String getCprojNo() {
		return cprojNo;
	}
	public void setCprojNo(String cprojNo) {
		this.cprojNo = cprojNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocContNo() {
		return docContNo;
	}
	public void setDocContNo(String docContNo) {
		this.docContNo = docContNo;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getProjTitle() {
		return projTitle;
	}
	public void setProjTitle(String projTitle) {
		this.projTitle = projTitle;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<UserVO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	@Override
	public String toString() {
		return "TaskVO [taskNo=" + taskNo + ", projNo=" + projNo + ", title=" + title + ", content=" + content
				+ ", userId=" + userId + ", important=" + important + ", regdate=" + regdate + ", updatedate="
				+ updatedate + ", status=" + status + ", docContNo=" + docContNo + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", projTitle=" + projTitle + ", nickname=" + nickname + ", userList="
				+ userList + ", fileList=" + fileList + ", sessionId=" + sessionId + ", cprojNo=" + cprojNo
				+ ", getCprojNo()=" + getCprojNo() + ", getSessionId()=" + getSessionId() + ", getTaskNo()="
				+ getTaskNo() + ", getProjNo()=" + getProjNo() + ", getTitle()=" + getTitle() + ", getContent()="
				+ getContent() + ", getUserId()=" + getUserId() + ", getImportant()=" + getImportant()
				+ ", getRegdate()=" + getRegdate() + ", getUpdatedate()=" + getUpdatedate() + ", getStatus()="
				+ getStatus() + ", getDocContNo()=" + getDocContNo() + ", getStartdate()=" + getStartdate()
				+ ", getEnddate()=" + getEnddate() + ", getProjTitle()=" + getProjTitle() + ", getNickname()="
				+ getNickname() + ", getUserList()=" + getUserList() + ", getFileList()=" + getFileList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
