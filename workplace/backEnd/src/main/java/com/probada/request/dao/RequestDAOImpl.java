package com.probada.request.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.probada.request.vo.RequestVO;

public class RequestDAOImpl implements RequestDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//Your리퀘스트 리스트 출력
	@Override
	public List<RequestVO> selectYourRequestList(String userTo) throws SQLException {
		List<RequestVO> yourRequestList = sqlSession.selectList("Request-Mapper.selectYourRequestList", userTo);
		return yourRequestList;
	}
	
	//Your리퀘스트 범주
	@Override
	public List<RequestVO> selectYourRequestSort(String userTo) throws SQLException {
		List<RequestVO> yourRequestSort = sqlSession.selectList("Request-Mapper.selectYourRequestSort", userTo);
		return yourRequestSort;
	}
	
	//My리퀘스트 리스트 출력
	@Override
	public List<RequestVO> selectMyRequestList(String userFrom) throws SQLException {
		List<RequestVO> myRequestList = sqlSession.selectList("Request-Mapper.selectMyRequestList", userFrom);
		return myRequestList;
	}
	
	//My리퀘스트 범주
	@Override
	public List<RequestVO> selectMyRequestSort(String userFrom) throws SQLException {
		List<RequestVO> myRequestSort = sqlSession.selectList("Request-Mapper.selectMyRequestSort", userFrom);
		return myRequestSort;
	}
	
	//Your리퀘스트 상태 변경(승낙/거절)
	@Override
	public void changeRequestStatus(String status, String reqNo, String reqRes) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("status", status);
		dataMap.put("reqNo", reqNo);
		dataMap.put("reqRes", reqRes);
		
		sqlSession.update("Request-Mapper.changeRequestStatus", dataMap);
	}
}
