package com.probada.milestone.vo;

import java.util.List;

import com.probada.issue.vo.IssueVO;

public class MilestoneVO {

	private String mileNo;
	private String title;
	private String userId;
	private String projNo;
	private String content;
	private String status;
	private String regdate;

	private String projTitle;
	private String nickname;

	private List<IssueVO> issueList;
	private List<String> issueNoList;

	public String getMileNo() {
		return mileNo;
	}
	public void setMileNo(String mileNo) {
		this.mileNo = mileNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
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
	
	public List<IssueVO> getIssueList() {
		return issueList;
	}
	public void setIssueList(List<IssueVO> issueList) {
		this.issueList = issueList;
	}
	public List<String> getIssueNoList() {
		return issueNoList;
	}
	public void setIssueNoList(List<String> issueNoList) {
		this.issueNoList = issueNoList;
	}
	
	@Override
	public String toString() {
		return "MilestoneVO [status=" + status + ", userId=" + userId + ", mileNo=" + mileNo + ", title=" + title
				+ ", content=" + content + ", projNo=" + projNo + ", regdate=" + regdate + ", projTitle=" + projTitle
				+ ", nickname=" + nickname + ", issueList=" + issueList + ", issueNoList=" + issueNoList + "]";
	}
}
