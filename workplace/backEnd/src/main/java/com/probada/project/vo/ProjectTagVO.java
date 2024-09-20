package com.probada.project.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTagVO {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String tagNo;
	private String tagName;
	private String projNo;
	private String projectTitle = "";
	private Date startDate;
	private Date endDate;
	private String leader = "";
	private String status = "";

	public String getTagNo() {
		return tagNo;
	}
	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getStartDate() {
		return sdf.format(startDate);
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return sdf.format(endDate);
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
