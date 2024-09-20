package com.probada.alert.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.probada.alert.dao.AlertDAO;
import com.probada.alert.vo.AlertVO;

public class AlertServiceImpl implements AlertService{

	private AlertDAO alertDAO;
	
	public void setAlertDAO(AlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	@Override
	public void insertAlert(Map<String, String> alertData) throws SQLException {
		alertDAO.insertAlert(alertData);
	}

	@Override
	public List<AlertVO> getUserAlertList(String userId) throws SQLException {
		return alertDAO.getUserAlertList(userId);
	}

	@Override
	public int getAlertCount(String userId) throws SQLException {
		return alertDAO.getAlertCount(userId);
	}

}
