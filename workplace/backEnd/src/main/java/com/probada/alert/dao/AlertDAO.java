package com.probada.alert.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.probada.alert.vo.AlertVO;

public interface AlertDAO {

	// ALERT테이블에 데이터를 저장한다.
	public void insertAlert(Map<String, String> alertData) throws SQLException;
	
	// 해당 유저이 알림 리스트를 조회한다.
	public List<AlertVO> getUserAlertList(String userId) throws SQLException;
	
	// alert가 몇 개인지 조회하는 메서드
	public int getAlertCount(String userId)throws SQLException;
}
