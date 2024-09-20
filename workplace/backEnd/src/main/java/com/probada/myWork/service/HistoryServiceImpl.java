package com.probada.myWork.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.myWork.dao.HistoryDAO;
import com.probada.myWork.vo.HistoryVO;

public class HistoryServiceImpl implements HistoryService {
	
	private HistoryDAO historyDAO;
	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}
	
	//프로젝트 히스토리 리스트 출력
	@Override
	public List<HistoryVO> getProjHistoryList(String projNo) throws SQLException {
		List<HistoryVO> historyList = historyDAO.selectProjHistoryList(projNo);
		return historyList;
	}
	
	//프로젝트 히스토리 리스트 구분별 정렬
	@Override
	public List<HistoryVO> getProjHistoryDistSort(String projNo) throws SQLException {
		List<HistoryVO> historyDistSort = historyDAO.selectProjHistoryDistSort(projNo);
		return historyDistSort;
	}
	
	//히스토리 리스트 출력
	@Override
	public List<HistoryVO> getHistoryList(String userId) throws SQLException {
		List<HistoryVO> historyList = historyDAO.selectHistoryList(userId);
		return historyList;
	}
	
	//히스토리 리스트 프로젝트별 정렬
	@Override
	public List<HistoryVO> getMyHistoryProjectSort(String userId) throws SQLException {
		List<HistoryVO> historyProjectSort = historyDAO.selectMyHistoryProjectSort(userId);
		return historyProjectSort;
	}
	
	//히스토리 리스트 구분별 정렬
	@Override
	public List<HistoryVO> getMyHistoryDistSort(String userId) throws SQLException {
		List<HistoryVO> historyDistSort = historyDAO.selectMyHistoryDistSort(userId);
		return historyDistSort;
	}
}
