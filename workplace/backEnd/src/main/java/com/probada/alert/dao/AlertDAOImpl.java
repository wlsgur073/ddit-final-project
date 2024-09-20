package com.probada.alert.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.probada.alert.vo.AlertVO;

public class AlertDAOImpl implements AlertDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAlert(Map<String, String> alertData) throws SQLException {
		sqlSession.insert("Alert-Mapper.insertAlert", alertData);
	}

	@Override
	public List<AlertVO> getUserAlertList(String userId) throws SQLException {
		return sqlSession.selectList("Alert-Mapper.getUserAlertList", userId);
	}

	@Override
	public int getAlertCount(String userId) throws SQLException {
		return sqlSession.selectOne("Alert-Mapper.getAlertCount", userId);
	}

}
