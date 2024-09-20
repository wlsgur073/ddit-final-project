package com.probada.document.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FileVO {

	private String DOC_NO;
	private String PROJ_NO;
	private String CPROJ_NO; //콜라보 프로젝트 넘버 추가
	private String USER_ID;
	private String DESC="desc??";
	private String STATUS="STATUS??";
	private String DIST="DIST??";
	private String name;
	private boolean isDirectory;
    private boolean hasDirectories;
    private String path;
    private String extension=" ";
	private String etc="1";
	private int size =  20;

	private String projTitle = "";

	private String cprojTitle = "";

	@JsonFormat(pattern = "yyyy/MM/dd")
    private Date createdUtc = new Date();

	@JsonFormat(pattern = "yyyy/MM/dd")
    private Date created = new Date();

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date modified = new Date();

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date modifiedUtc = new Date();
    
    
    @Override
	public String toString() {
		return "FileVO [DOC_NO=" + DOC_NO + ", PROJ_NO=" + PROJ_NO + ", CPROJ_NO=" + CPROJ_NO + ", USER_ID=" + USER_ID
				+ ", DESC=" + DESC + ", STATUS=" + STATUS + ", DIST=" + DIST + ", name=" + name + ", isDirectory="
				+ isDirectory + ", hasDirectories=" + hasDirectories + ", path=" + path + ", extension=" + extension
				+ ", etc=" + etc + ", size=" + size + ", projTitle=" + projTitle + ", cprojTitle=" + cprojTitle
				+ ", createdUtc=" + createdUtc + ", created=" + created + ", modified=" + modified + ", modifiedUtc="
				+ modifiedUtc + "]";
	}

	public String getCprojTitle() {
    	return cprojTitle;
    }
    
    public void setCprojTitle(String cprojTitle) {
    	this.cprojTitle = cprojTitle;
    }

    public String getCPROJ_NO() {
    	return CPROJ_NO;
    }
    
    public void setCPROJ_NO(String cPROJ_NO) {
    	CPROJ_NO = cPROJ_NO;
    }
    public String getProjTitle() {
 		return projTitle;
 	}

 	public void setProjTitle(String projTitle) {
 		this.projTitle = projTitle;
 	}

    public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}



	public String getDOC_NO() {
		return DOC_NO;
	}

	public void setDOC_NO(String dOC_NO) {
			DOC_NO = dOC_NO;
	}

	public String getPROJ_NO() {
		return PROJ_NO;
	}

	public void setPROJ_NO(String pROJ_NO) {
		PROJ_NO = pROJ_NO;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getDESC() {
		return DESC;
	}

	public void setDESC(String dESC) {
		DESC = dESC;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getDIST() {
		return DIST;
	}

	public void setDIST(String dIST) {
		DIST = dIST;
	}


	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isisDirectory() {
		return isDirectory;
	}


	public void setIsDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public boolean isHasDirectories() {
		return hasDirectories;
	}

	public void setHasDirectories(boolean hasDirectories) {
		this.hasDirectories = hasDirectories;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getCreatedUtc() {
		return createdUtc;
	}

	public void setCreatedUtc(Date createdUtc) {
		this.createdUtc = createdUtc;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getModifiedUtc() {
		return modifiedUtc;
	}

	public void setModifiedUtc(Date modifiedUtc) {
		this.modifiedUtc = modifiedUtc;
	}











	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}


























}

