package com.probada.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.project.dao.ProjectDAO;
import com.probada.project.vo.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	public List<ProjectVO> getProjectList() throws SQLException {

		List<ProjectVO> projectList = projectDAO.selectProjectList();

		return projectList;
	}

	@Override
	public ProjectVO getProjectByProjNo(String projNo) throws SQLException {

		ProjectVO projectVO = projectDAO.selectProjectByProjNo(projNo);

		return projectVO;
	}

	@Override
	public String registProject(ProjectVO projectVO) throws SQLException {

		int seqNo = projectDAO.selectProjectSeqNext();

		String projNo = Integer.toString(seqNo);

		projectVO.setProjNo(projNo);

		projectDAO.insertProject(projectVO);

		return projNo;

	}

	@Override
	public void modifyProjectDetail(ProjectVO projectVO) throws SQLException {

		projectDAO.updateProjectDetail(projectVO);

	}

	@Override
	public void modifyProjectNotice(ProjectVO projectVO) throws SQLException {

		projectDAO.updateProjectNotice(projectVO);

	}

	@Override
	public int getProjectCountInProjByUserId(String userId) throws SQLException {

		int count = projectDAO.selectProjectCountInProjByUserId(userId);

		return count;
	}

	@Override
	public void registProjectUserRelation(ProjectVO projectVO) throws SQLException {

		projectDAO.insertProjectUserRelation(projectVO);

	}

	@Override
	public List<ProjectVO> getProjectListByUserId(String userId) throws SQLException {

		List<ProjectVO> projectList = projectDAO.selectProjectListByUserId(userId);

		return projectList;
	}

	@Override
	public String getProjectNameByProjNo(String projNo) throws SQLException {
		String title = projectDAO.selectProjectNameByProjNo(projNo);
		return title;
	}

	@Override
	public void modifyUserRole(ProjectVO projectVO) throws SQLException {

		projectDAO.updateUserRole(projectVO);

	}

	@Override
	public String getUserRole(ProjectVO projectVO) throws SQLException {

		String role = projectDAO.selectUserRole(projectVO);

		return role;
	}

	@Override
	public void removeProjectUserRelation(ProjectVO projectVO) throws SQLException {

		projectDAO.deleteProjectUserRelation(projectVO);

	}

	@Override
	public void modifyProjectUserRelationToRejoin(ProjectVO projectVO) throws SQLException {

		projectDAO.updateProjectUserRelationToRejoin(projectVO);

	}

	@Override
	public int getCountDeletedUserByUserId(ProjectVO projectVO) throws SQLException {

		int count = projectDAO.countDeletedUserByUserId(projectVO);

		return count;
	}




}
