package com.probada.request.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.request.vo.RequestVO;

public interface RequestDAO {
	
	//Your리퀘스트 리스트 출력
	public List<RequestVO> selectYourRequestList(String userTo) throws SQLException;
	
	//Your리퀘스트 범주
	public List<RequestVO> selectYourRequestSort(String userTo) throws SQLException;

	//My리퀘스트 리스트 출력
	public List<RequestVO> selectMyRequestList(String userFrom) throws SQLException;
	
	//My리퀘스트 범주
	public List<RequestVO> selectMyRequestSort(String userFrom) throws SQLException;
	
	//Your리퀘스트 상태 변경(승낙/거절)
	public void changeRequestStatus(String status, String reqNo, String reqRes) throws SQLException;
}
