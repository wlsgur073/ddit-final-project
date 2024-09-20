package com.probada.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.project.vo.ProjectTagVO;

public interface ProjectTagDAO {

	public List<ProjectTagVO> selectTagNameList(String projNo) throws SQLException;

	public List<ProjectTagVO> selectProjectListByTagNo(String tagNo) throws SQLException;

	public int selectProjTagSeqNext() throws SQLException;

	public String selectTagNoByTagName(ProjectTagVO projectTagVO) throws SQLException;

	public void insertProjectTagByProjNo(ProjectTagVO projectTagVO) throws SQLException;

	public void insertProjectTagRelation(ProjectTagVO projectTagVO) throws SQLException;

	public int countProjectTagByTagName(ProjectTagVO projectTagVO) throws SQLException;


}
