package com.probada.myWork.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.probada.myWork.vo.HistoryVO;

public class HistoryDAOImpl implements HistoryDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//프로젝트 히스토리 리스트 출력
	@Override
	public List<HistoryVO> selectProjHistoryList(String projNo) throws SQLException {
		List<HistoryVO> historyList = sqlSession.selectList("History-Mapper.selectProjHistoryList", projNo);
		return historyList;
	}
	
	//프로젝트 히스토리 리스트 구분별 정렬
	@Override
	public List<HistoryVO> selectProjHistoryDistSort(String projNo) throws SQLException {
		List<HistoryVO> historyDistSort = sqlSession.selectList("History-Mapper.selectProjHistoryDistSort", projNo);
		return historyDistSort;
	}
	
	//히스토리 리스트 출력
	@Override
	public List<HistoryVO> selectHistoryList(String userId) throws SQLException {
		List<HistoryVO> historyList = sqlSession.selectList("History-Mapper.selectHistoryList", userId);
		return historyList;
	}
	
	//히스토리 리스트 프로젝트별 정렬
	@Override
	public List<HistoryVO> selectMyHistoryProjectSort(String userId) throws SQLException {
		List<HistoryVO> historyProjectSort = sqlSession.selectList("History-Mapper.selectMyHistoryProjectSort", userId);
		return historyProjectSort;
	}
	
	//히스토리 리스트 구분별 정렬
	@Override
	public List<HistoryVO> selectMyHistoryDistSort(String userId) throws SQLException {
		List<HistoryVO> historyDistSort = sqlSession.selectList("History-Mapper.selectMyHistoryDistSort", userId);
		return historyDistSort;
	}
}
