package com.probada.spoon.vo;

import java.util.List;

import com.probada.collabo.vo.CollaboVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public class SpoonVO {

	private String notice;
	private String status;
	private String docContNo;
	private String startdate;
	private String privacy;
	private String noticeCont;
	private String intro;
	private String title;
	private String cprojTitle;
	private String updatedate;
	private String likeCount;
	private String projNo;
	private String cprojNo;
	private String enddate;

	//	For ProjectTagService
	private Object tagNames;
	private Object tagNo;
	private List<UserVO> member;

	//For ProjectUserRelation
	private String userId;
	private String role;
	private String joindate;
	private String toPlUserId;
	
	// For spoon
	private List<String> taskNoList;
	private List<TaskVO> taskList;
	private List<CollaboVO> cprojTitleList;
	
	
	
	public List<String> getTaskNoList() {
		return taskNoList;
	}
	public void setTaskNoList(List<String> taskNoList) {
		this.taskNoList = taskNoList;
	}
	public List<CollaboVO> getCprojTitleList() {
		return cprojTitleList;
	}
	public void setCprojTitleList(List<CollaboVO> cprojTitleList) {
		this.cprojTitleList = cprojTitleList;
	}
	public String getCprojNo() {
		return cprojNo;
	}
	public void setCprojNo(String cprojNo) {
		this.cprojNo = cprojNo;
	}
	public String getCprojTitle() {
		return cprojTitle;
	}
	public void setCprojTitle(String cprojTitle) {
		this.cprojTitle = cprojTitle;
	}
	public List<TaskVO> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<TaskVO> taskList) {
		this.taskList = taskList;
	}
	public Object getTagNo() {
		return tagNo;
	}
	public void setTagNo(Object tagNo) {
		this.tagNo = tagNo;
	}
	public String getToPlUserId() {
		return toPlUserId;
	}

	public void setToPlUserId(String toPlUserId) {
		this.toPlUserId = toPlUserId;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public List<UserVO> getMember() {
		return member;
	}
	public void setMember(List<UserVO> member) {
		this.member = member;
	}
	public Object getTagNames() {
		return tagNames;
	}
	public void setTagNames(Object tagNames) {
		this.tagNames = tagNames;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getNotice() {
		return notice;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setDocContNo(String docContNo) {
		this.docContNo = docContNo;
	}
	public String getDocContNo() {
		return docContNo;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setNoticeCont(String noticeCont) {
		this.noticeCont = noticeCont;
	}
	public String getNoticeCont() {
		return noticeCont;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIntro() {
		return intro;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getEnddate() {
		return enddate;
	}

}
