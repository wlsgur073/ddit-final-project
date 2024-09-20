package com.probada.totalSearch.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.project.vo.ProjectVO;
import com.probada.totalSearch.vo.SearchProjVO;
import com.probada.user.vo.UserVO;

public class SearchDAOImpl implements SearchDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	
	
	@Override
	public List<UserVO> selectUserBySearch(String searchDate) {
		
		List<UserVO> user = sqlSession.selectList("Search-Mapper.selectUserBySearch",searchDate);
		return user;
	}
	
	
	@Override
	public List<ProjectVO> selectProjectBySearch(String searchDate) {
		
		List<ProjectVO> project = sqlSession.selectList("Search-Mapper.selectProjectBySearch",searchDate);
		return project;
	}


	@Override
	public List<String> selectPLBySearch(String searchDate) {
		List<String> pl = sqlSession.selectList("Search-Mapper.selectPLBySearch",searchDate);
		return pl;
	}




	@Override
	public List<SearchProjVO> selectPopProjBySearch() {
		List<SearchProjVO> project = sqlSession.selectList("Search-Mapper.selectPopProjBySearch");
		return project;
	}




	@Override
	public List<String> selectPopPLBySearch() {
		List<String> pl = sqlSession.selectList("Search-Mapper.selectPopPLBySearch");
		return pl;
	}




	@Override
	public List<SearchProjVO> selectCollaboListByAutoComplate() {
		
		List<SearchProjVO> project = sqlSession.selectList("Search-Mapper.selectCollaboListByAutoComplate");
		return project;
		
	}




	@Override
	public List<SearchProjVO> selectCollaboListBySearch(String searchDate) {
		
		List<SearchProjVO> project = sqlSession.selectList("Search-Mapper.selectCollaboListBySearch",searchDate);
		return project;
		
	}




	@Override
	public List<String> selectCollaboPLBySearch(String searchDate) {
		
		
		List<String> pl = sqlSession.selectList("Search-Mapper.selectCollaboPLBySearch",searchDate);
		return pl;
		
		
	}




	@Override
	public List<ProjectVO> selectProjectListByAutoComplate() {
		List<ProjectVO> project = sqlSession.selectList("Search-Mapper.selectProjectListByAutoComplate");
		return project;
	}




	@Override
	public UserVO selectUserByNickName(String nickName) {
		
		UserVO user = new UserVO();
		
		user = sqlSession.selectOne("Search-Mapper.selectUserByNickName",nickName);
		
		return user;
	}




	@Override
	public String selectTagNoByTagName(String tagName) {
		
		String tagNo=null;
		
		tagNo = sqlSession.selectOne("Search-Mapper.selectTagNoByTagName",tagName);
		
		return tagNo;
	}


	

}
