package com.probada.user.vo;

public class UserTotalCountVO {

//	해당 유저의 분량을 가져온다.
//	DB에는 없는 컬럼
	private String userId = "";
	private int taskCount;
	private int projectCount;
	private int issueCount;
	private int requestCount;
	private int mailCount;
	private int collaboCount;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	public int getProjectCount() {
		return projectCount;
	}
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public int getMailCount() {
		return mailCount;
	}
	public void setMailCount(int mailCount) {
		this.mailCount = mailCount;
	}
	public int getCollaboCount() {
		return collaboCount;
	}
	public void setCollaboCount(int collaboCount) {
		this.collaboCount = collaboCount;
	}
	
	
}
