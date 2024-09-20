package com.probada.collabo.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.probada.user.vo.UserVO;

public class CollaboVO {

	private String notice;
	private String status;
	private String startdate;
	private String privacy;
	private String noticeCont;
	private String cprojNo;
	private String title;
	private String updatedate;
	private String likeCount;
	private String enddate;
	private String intro;
	
	//콜라보 제안 메일 
	private int mailNo = 0;
	private String userTo;
	private String userFrom;
	private String userToProjNo;
	private String userFromProjNo;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String regdate;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String dist;
	private String recvDel;
	private String sentDel;
	
	//콜라보프로젝트 관계성 테이블
	private String userId;
	private String projNo;
	private String role = "";
	
	//For ProjectTagService
	private Object tagNames;
	private Object tagNo;
	private List<UserVO> member;
	
	public Object getTagNames() {
		return tagNames;
	}
	public void setTagNames(Object tagNames) {
		this.tagNames = tagNames;
	}
	public Object getTagNo() {
		return tagNo;
	}
	public void setTagNo(Object tagNo) {
		this.tagNo = tagNo;
	}
	public List<UserVO> getMember() {
		return member;
	}
	public void setMember(List<UserVO> member) {
		this.member = member;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getNoticeCont() {
		return noticeCont;
	}
	public void setNoticeCont(String noticeCont) {
		this.noticeCont = noticeCont;
	}
	public String getCprojNo() {
		return cprojNo;
	}
	public void setCprojNo(String cprojNo) {
		this.cprojNo = cprojNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public String getUserToProjNo() {
		return userToProjNo;
	}
	public void setUserToProjNo(String userToProjNo) {
		this.userToProjNo = userToProjNo;
	}
	public String getUserFromProjNo() {
		return userFromProjNo;
	}
	public void setUserFromProjNo(String userFromProjNo) {
		this.userFromProjNo = userFromProjNo;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "CollaboVO [notice=" + notice + ", status=" + status + ", startdate=" + startdate + ", privacy="
				+ privacy + ", noticeCont=" + noticeCont + ", cprojNo=" + cprojNo + ", title=" + title + ", updatedate="
				+ updatedate + ", likeCount=" + likeCount + ", enddate=" + enddate + ", intro=" + intro + ", mailNo="
				+ mailNo + ", userTo=" + userTo + ", userFrom=" + userFrom + ", userToProjNo=" + userToProjNo
				+ ", userFromProjNo=" + userFromProjNo + ", content=" + content + ", regdate=" + regdate + ", dist="
				+ dist + ", recvDel=" + recvDel + ", sentDel=" + sentDel + ", userId=" + userId + ", projNo=" + projNo
				+ ", role=" + role + "]";
	}
	
	
	
}
