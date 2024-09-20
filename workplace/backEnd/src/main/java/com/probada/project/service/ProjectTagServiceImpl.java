package com.probada.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.project.dao.ProjectTagDAO;
import com.probada.project.vo.ProjectTagVO;

@Service
public class ProjectTagServiceImpl implements ProjectTagService {

	private ProjectTagDAO projectTagDAO;
	public void setProjectTagDAO(ProjectTagDAO projectTagDAO) {
		this.projectTagDAO = projectTagDAO;
	}
	@Override
	public List<ProjectTagVO> getTagNameList(String projNo) throws SQLException {

		List<ProjectTagVO> projectTagList = projectTagDAO.selectTagNameList(projNo);

		return projectTagList;
	}

	@Override
	public List<ProjectTagVO> getProjectListByTagNo(String tagNo) throws SQLException {
		return projectTagDAO.selectProjectListByTagNo(tagNo);
	}
	@Override
	public void registProjectTagByProjNo(ProjectTagVO projectTagVO) throws SQLException {

		int seqNo = projectTagDAO.selectProjTagSeqNext();

		String tagNo = Integer.toString(seqNo);

		projectTagVO.setTagNo(tagNo);

		projectTagDAO.insertProjectTagByProjNo(projectTagVO);

	}

	@Override
	public void registProjectTagRelation(ProjectTagVO projectTagVO) throws SQLException {
		projectTagDAO.insertProjectTagRelation(projectTagVO);

	}
	@Override
	public int getCountProjectTagByTagName(ProjectTagVO projectTagVO) throws SQLException {

		int count = projectTagDAO.countProjectTagByTagName(projectTagVO);

		return count;
	}
	@Override
	public String getTagNoByTagName(ProjectTagVO projectTagVO) throws SQLException {

		String tagNo = projectTagDAO.selectTagNoByTagName(projectTagVO);

		return tagNo;
	}


}
