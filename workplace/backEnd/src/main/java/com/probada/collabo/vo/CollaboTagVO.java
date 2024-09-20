package com.probada.collabo.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CollaboTagVO {
	
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String tagNo;
	private String tagName;
	private String cprojNo;
	private String cprojectTitle = "";
	private Date startDate;
	private Date endDate;
	private String leader = "";
	private String status = "";
	
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
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
	public String getCprojNo() {
		return cprojNo;
	}
	public void setCprojNo(String cprojNo) {
		this.cprojNo = cprojNo;
	}
	public String getCprojectTitle() {
		return cprojectTitle;
	}
	public void setCprojectTitle(String cprojectTitle) {
		this.cprojectTitle = cprojectTitle;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
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
	@Override
	public String toString() {
		return "CollaboTagVO [sdf=" + sdf + ", tagNo=" + tagNo + ", tagName=" + tagName + ", cprojNo=" + cprojNo
				+ ", cprojectTitle=" + cprojectTitle + ", startDate=" + startDate + ", endDate=" + endDate + ", leader="
				+ leader + ", status=" + status + "]";
	}
	
	
}
