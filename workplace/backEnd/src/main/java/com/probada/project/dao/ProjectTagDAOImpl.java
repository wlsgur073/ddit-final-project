package com.probada.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.project.vo.ProjectTagVO;

public class ProjectTagDAOImpl implements ProjectTagDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ProjectTagVO> selectTagNameList(String projNo) throws SQLException {

		List<ProjectTagVO> projectTagList = sqlSession.selectList("ProjectTag-Mapper.selectTagNameList", projNo);

		return projectTagList;
	}

	@Override
	public List<ProjectTagVO> selectProjectListByTagNo(String tagNo) throws SQLException{
		return sqlSession.selectList("ProjectTag-Mapper.selectProjectListByTagNo", tagNo);
	}

	@Override
	public void insertProjectTagByProjNo(ProjectTagVO projectTagVO) throws SQLException {
		sqlSession.update("ProjectTag-Mapper.insertProjectTagByProjNo",projectTagVO);

	}

	@Override
	public void insertProjectTagRelation(ProjectTagVO projectTagVO) throws SQLException {
		sqlSession.update("ProjectTag-Mapper.insertProjectTagRelation",projectTagVO);

	}

	@Override
	public int countProjectTagByTagName(ProjectTagVO projectTagVO) throws SQLException {

		int count = sqlSession.selectOne("ProjectTag-Mapper.countProjectTagByTagName",projectTagVO);

		return count;
	}

	@Override
	public int selectProjTagSeqNext() throws SQLException {

		int seq = sqlSession.selectOne("ProjectTag-Mapper.selectProjTagSeqNext");

		return seq;
	}

	@Override
	public String selectTagNoByTagName(ProjectTagVO projectTagVO) throws SQLException {

		String tagNo = sqlSession.selectOne("ProjectTag-Mapper.selectTagNoByTagName",projectTagVO);

		return tagNo;
	}


}
