package com.probada.project.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.project.vo.ProjectVO;

public interface ProjectService {

	public List<ProjectVO> getProjectList() throws SQLException;

	public List<ProjectVO> getProjectListByUserId(String userId) throws SQLException;

	public ProjectVO getProjectByProjNo(String projNo) throws SQLException;

	public String registProject(ProjectVO projectVO) throws SQLException;

	public void modifyProjectDetail(ProjectVO projectVO) throws SQLException;

	public void modifyProjectNotice(ProjectVO projectVO) throws SQLException;

	public int getProjectCountInProjByUserId(String userId) throws SQLException;

	public int getCountDeletedUserByUserId(ProjectVO projectVO) throws SQLException;

	public void registProjectUserRelation(ProjectVO projectVO) throws SQLException;

	public void modifyProjectUserRelationToRejoin(ProjectVO projectVO) throws SQLException;

	public void removeProjectUserRelation(ProjectVO projectVO) throws SQLException;

	public String getProjectNameByProjNo(String projNo) throws SQLException;

	public void modifyUserRole(ProjectVO projectVO) throws SQLException;

	public String getUserRole(ProjectVO projectVO) throws SQLException;
}