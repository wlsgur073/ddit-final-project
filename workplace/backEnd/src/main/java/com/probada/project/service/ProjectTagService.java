package com.probada.project.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.project.vo.ProjectTagVO;

public interface ProjectTagService {

	public List<ProjectTagVO> getTagNameList(String projNo) throws SQLException;

	public List<ProjectTagVO> getProjectListByTagNo(String tagNo) throws SQLException;

	public String getTagNoByTagName(ProjectTagVO projectTagVO) throws SQLException;

	public void registProjectTagByProjNo(ProjectTagVO projectTagVO) throws SQLException;

	public void registProjectTagRelation(ProjectTagVO projectTagVO) throws SQLException;

	public int getCountProjectTagByTagName(ProjectTagVO projectTagVO) throws SQLException;

}
