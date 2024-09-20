package com.probada.totalSearch.vo;

import java.util.List;

public class SearchProjVO {

	
	

	private String plId;
	private String plIdSec;
	private String notice;
	private String status;
	private String docContNo;
	private String startdate;
	private String privacy;
	private String noticeCont;
	private String intro;
	private String title;
	private String updatedate;
	private String likeCount;
	private String projNo;
	private String enddate;
	private List<String> tagName;
	private String picture;
	
	
	
	
	
	
	
	
	public String getPlIdSec() {
		return plIdSec;
	}
	public void setPlIdSec(String plIdSec) {
		this.plIdSec = plIdSec;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "SearchProjVO [plId=" + plId + ", notice=" + notice + ", status=" + status + ", docContNo=" + docContNo
				+ ", startdate=" + startdate + ", privacy=" + privacy + ", noticeCont=" + noticeCont + ", intro="
				+ intro + ", title=" + title + ", updatedate=" + updatedate + ", likeCount=" + likeCount + ", projNo="
				+ projNo + ", enddate=" + enddate + ", tagName=" + tagName + ", picture=" + picture + "]";
	}
	public List<String> getTagName() {
		return tagName;
	}
	public void setTagName(List<String> tagName) {
		this.tagName = tagName;
	}
	public String getPlId() {
		return plId;
	}
	public void setPlId(String plId) {
		this.plId = plId;
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
	
	
	

	
}
