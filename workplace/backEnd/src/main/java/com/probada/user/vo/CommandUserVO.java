package com.probada.user.vo;

import java.util.Arrays;

public class CommandUserVO {

	
	private String title;
	private String Ptitle;
	private String[] userId;
	
	
	@Override
	public String toString() {
		return "CommandUserVO [title=" + title + ", Ptitle=" + Ptitle + ", userId=" + Arrays.toString(userId) + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPtitle() {
		return Ptitle;
	}
	public void setPtitle(String ptitle) {
		Ptitle = ptitle;
	}
	public String[] getUserId() {
		return userId;
	}
	public void setUserId(String[] userId) {
		this.userId = userId;
	}
	
	
	
	
}
