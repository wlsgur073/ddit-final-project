package com.probada.issue.vo;

import com.probada.user.vo.UserVO;

public class IssueReplyVO {

	private String issueResNo;
	private String issueNo;
	private String userId;
	private String projNo;
	private String content;
	private String regdate;
	private String updatedate;
	private UserVO userVO;

	public String getIssueResNo() {
		return issueResNo;
	}
	public void setIssueResNo(String issueResNo) {
		this.issueResNo = issueResNo;
	}
	public String getIssueNo() {
		return issueNo;
	}
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}	
}
