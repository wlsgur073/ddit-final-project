package com.probada.myWork.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.myWork.vo.HistoryVO;

public interface HistoryDAO {
	
	//프로젝트 히스토리 리스트 출력
	public List<HistoryVO> selectProjHistoryList(String projNo) throws SQLException;
	
	//프로젝트 히스토리 리스트 구분별 정렬
	public List<HistoryVO> selectProjHistoryDistSort(String projNo) throws SQLException;
	
	//히스토리 리스트 출력
	public List<HistoryVO> selectHistoryList(String userId) throws SQLException;
	
	//히스토리 리스트 프로젝트별 정렬
	public List<HistoryVO> selectMyHistoryProjectSort(String userId) throws SQLException;
	
	//히스토리 리스트 구분별 정렬
	public List<HistoryVO> selectMyHistoryDistSort(String userId) throws SQLException;
}
