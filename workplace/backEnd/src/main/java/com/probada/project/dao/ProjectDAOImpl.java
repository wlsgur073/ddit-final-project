package com.probada.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.project.vo.ProjectVO;

public class ProjectDAOImpl implements ProjectDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ProjectVO> selectProjectList() throws SQLException{

		List<ProjectVO> projectList = sqlSession.selectList("Project-Mapper.selectProjectList");

		return projectList;
	}

	@Override
	public ProjectVO selectProjectByProjNo(String projNo) throws SQLException {

		ProjectVO projectVO = sqlSession.selectOne("Project-Mapper.selectProjectByProjNo",projNo);

		return projectVO;
	}

	@Override
	public int selectProjectSeqNext() throws SQLException {

		int projNo = sqlSession.selectOne("Project-Mapper.selectProjectSeqNext");

		return projNo;
	}

	@Override
	public void insertProject(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.insertProject",projectVO);

	}

	@Override
	public void updateProjectDetail(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.updateProjectDetail",projectVO);

	}

	@Override
	public void updateProjectNotice(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.updateProjectNotice",projectVO);

	}

	@Override
	public int selectProjectCountInProjByUserId(String userId) throws SQLException {

		int result = sqlSession.selectOne("Project-Mapper.selectProjectCountInProjByUserId",userId);

		return result;
	}

	@Override
	public void insertProjectUserRelation(ProjectVO projectVO) throws SQLException {

		sqlSession.insert("Project-Mapper.insertProjectUserRelation",projectVO);
	}

	@Override
	public List<ProjectVO> selectProjectListByUserId(String userId) throws SQLException {

		List<ProjectVO> projectList = sqlSession.selectList("Project-Mapper.selectProjectListByUserId",userId);

		return projectList;
	}

	@Override
	public String selectProjectNameByProjNo(String projNo) throws SQLException {

		String title = sqlSession.selectOne("Project-Mapper.selectProjectNameByProjNo", projNo);

		return title;
	}

	@Override
	public void updateUserRole(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.updateUserRole",projectVO);

	}

	@Override
	public String selectUserRole(ProjectVO projectVO) throws SQLException {

		String role = sqlSession.selectOne("Project-Mapper.selectUserRole",projectVO);

		return role;
	}

	@Override
	public void deleteProjectUserRelation(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.deleteProjectUserRelation",projectVO);

	}

	@Override
	public void updateProjectUserRelationToRejoin(ProjectVO projectVO) throws SQLException {

		sqlSession.update("Project-Mapper.updateProjectUserRelationToRejoin",projectVO);

	}

	@Override
	public int countDeletedUserByUserId(ProjectVO projectVO) throws SQLException {

		int count = sqlSession.selectOne("Project-Mapper.countDeletedUserByUserId",projectVO);

		return count;
	}


}
