package com.probada.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.project.vo.ProjectVO;

public interface ProjectDAO {

	public List<ProjectVO> selectProjectList() throws SQLException;

	public List<ProjectVO> selectProjectListByUserId(String userId) throws SQLException;

	public ProjectVO selectProjectByProjNo(String projNo) throws SQLException;

	public int selectProjectSeqNext() throws SQLException;

	public void insertProject(ProjectVO projectVO) throws SQLException;

	public void updateProjectDetail(ProjectVO projectVO) throws SQLException;

	public void updateProjectNotice(ProjectVO projectVO) throws SQLException;

	public int selectProjectCountInProjByUserId(String userId) throws SQLException;

	// ProjectUserRelation Table
	public int countDeletedUserByUserId(ProjectVO projectVO) throws SQLException;

	public void insertProjectUserRelation(ProjectVO projectVO) throws SQLException;

	public void updateProjectUserRelationToRejoin(ProjectVO projectVO) throws SQLException;

	public void deleteProjectUserRelation(ProjectVO projectVO) throws SQLException;

	public String selectProjectNameByProjNo(String projNo) throws SQLException;

	public void updateUserRole(ProjectVO projectVO) throws SQLException;

	public String selectUserRole(ProjectVO projectVO) throws SQLException;
}
