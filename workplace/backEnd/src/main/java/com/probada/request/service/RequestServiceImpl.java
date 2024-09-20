package com.probada.request.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.request.dao.RequestDAO;
import com.probada.request.vo.RequestVO;

public class RequestServiceImpl implements RequestService {
	
	private RequestDAO requestDAO;
	public void setRequestDAO(RequestDAO requestDAO) {
		this.requestDAO = requestDAO;
	}
	
	//Your리퀘스트 리스트 출력
	@Override
	public List<RequestVO> getYourRequestList(String userTo) throws SQLException {
		List<RequestVO> yourRequestList = requestDAO.selectYourRequestList(userTo);
		return yourRequestList;
	}
	
	//Your리퀘스트 범주
	@Override
	public List<RequestVO> getYourRequestSort(String userTo) throws SQLException {
		List<RequestVO> yourRequestSort = requestDAO.selectYourRequestSort(userTo);
		return yourRequestSort;
	}
	
	//My리퀘스트 리스트 출력
	@Override
	public List<RequestVO> getMyRequestList(String userFrom) throws SQLException {
		List<RequestVO> myRequestList = requestDAO.selectMyRequestList(userFrom);
		return myRequestList;
	}
	
	//My리퀘스트 범주
	@Override
	public List<RequestVO> getMyRequestSort(String userFrom) throws SQLException {
		List<RequestVO> myRequestSort = requestDAO.selectMyRequestSort(userFrom);
		return myRequestSort;
	}
	
	//Your리퀘스트 상태 변경(승낙/거절)
	@Override
	public void changeRequestStatus(String status, String reqNo, String reqRes) throws SQLException {
		requestDAO.changeRequestStatus(status, reqNo, reqRes);
	}
}
