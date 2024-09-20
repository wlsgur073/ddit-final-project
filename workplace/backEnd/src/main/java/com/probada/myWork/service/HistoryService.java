package com.probada.myWork.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.myWork.vo.HistoryVO;

public interface HistoryService {
	
	//프로젝트 히스토리 리스트 출력
	public List<HistoryVO> getProjHistoryList(String projNo) throws SQLException;
	
	//프로젝트 히스토리 리스트 구분별 정렬
	public List<HistoryVO> getProjHistoryDistSort(String projNo) throws SQLException;
	
	//히스토리 리스트 출력
	public List<HistoryVO> getHistoryList(String userId) throws SQLException;
	
	//히스토리 리스트 프로젝트별 정렬
	public List<HistoryVO> getMyHistoryProjectSort(String userId) throws SQLException;
	
	//히스토리 리스트 구분별 정렬
	public List<HistoryVO> getMyHistoryDistSort(String userId) throws SQLException;
}
