package com.probada.user.vo;

public class UserVO {

//	nullException을 막기 위해서 디폴트값으로 초기화가 필요하다.
	private String nickname;
	private String privacy;
	private String picture;
	private String intro;
	private String authkey;
	private String regdate;
	private String userId = "";
	private String pwd = "";
	private int likeCount;
	private int authStatus;
	private int userUploadUsage;
	
	private int taskCount;
	private int projectCount;
	private int collaboCount;

	

	//	업무 상태별 count
	private int completeTaskCount;
	private int delayTaskCount;
	private int ongoingTaskCount;
	private int beforeTaskCount;

	private String projNo;

	private String cprojNo;

	private String isDeleted;
	
	
	@Override
	public String toString() {
		return "UserVO [nickname=" + nickname + ", privacy=" + privacy + ", picture=" + picture + ", intro=" + intro
				+ ", authkey=" + authkey + ", regdate=" + regdate + ", userId=" + userId + ", pwd=" + pwd
				+ ", likeCount=" + likeCount + ", authStatus=" + authStatus + ", userUploadUsage=" + userUploadUsage
				+ ", taskCount=" + taskCount + ", projectCount=" + projectCount + ", collaboCount=" + collaboCount
				+ ", completeTaskCount=" + completeTaskCount + ", delayTaskCount=" + delayTaskCount
				+ ", ongoingTaskCount=" + ongoingTaskCount + ", beforeTaskCount=" + beforeTaskCount + ", projNo="
				+ projNo + ", cprojNo=" + cprojNo + ", isDeleted=" + isDeleted + "]";
	}

	public int getCollaboCount() {
		return collaboCount;
	}

	public void setCollaboCount(int collaboCount) {
		this.collaboCount = collaboCount;
	}
	public String getCprojNo() {
		return cprojNo;
	}

	public void setCprojNo(String cprojNo) {
		this.cprojNo = cprojNo;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture() {
		return picture;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIntro() {
		return intro;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd() {
		return pwd;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	public int getUserUploadUsage() {
		return userUploadUsage;
	}
	public void setUserUploadUsage(int userUploadUsage) {
		this.userUploadUsage = userUploadUsage;
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

	public int getCompleteTaskCount() {
		return completeTaskCount;
	}

	public void setCompleteTaskCount(int completeTaskCount) {
		this.completeTaskCount = completeTaskCount;
	}

	public int getDelayTaskCount() {
		return delayTaskCount;
	}

	public void setDelayTaskCount(int delayTaskCount) {
		this.delayTaskCount = delayTaskCount;
	}

	public int getOngoingTaskCount() {
		return ongoingTaskCount;
	}

	public void setOngoingTaskCount(int ongoingTaskCount) {
		this.ongoingTaskCount = ongoingTaskCount;
	}

	public int getBeforeTaskCount() {
		return beforeTaskCount;
	}

	public void setBeforeTaskCount(int beforeTaskCount) {
		this.beforeTaskCount = beforeTaskCount;
	}



}
