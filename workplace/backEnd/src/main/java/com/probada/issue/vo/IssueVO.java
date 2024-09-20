package com.probada.issue.vo;

import java.util.ArrayList;
import java.util.List;

import com.probada.document.vo.FileVO;
import com.probada.user.vo.UserVO;

public class IssueVO {

	private String issueNo;
	private String projNo;
	private String userId;
	private String title;
	private String content;
	private String important;
	private String regdate;
	private String startdate;
	private String enddate;
	private String updatedate;
	private String status;
	private String docContNo;

	private UserVO member;
	private Object mileNo;
	private String nickname;
	private String projTitle;
	
	private List<FileVO> fileList = new ArrayList<FileVO>();
	
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdatedate() {
		return updatedate;
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
	public UserVO getMember() {
		return member;
	}
	public void setMember(UserVO member) {
		this.member = member;
	}
	public void setMileNo(Object mileNo) {
		this.mileNo = mileNo;
	}
	public Object getMileNo() {
		return mileNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProjTitle() {
		return projTitle;
	}
	public void setProjTitle(String projTitle) {
		this.projTitle = projTitle;
	}
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
}
