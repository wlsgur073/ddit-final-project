package com.probada.payment.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.payment.vo.PaySuccessVO;
import com.probada.payment.vo.PaymentsBillVO;
import com.probada.payment.vo.PaymentsPlanVO;

public class PaymentsBillDAOImpl implements PaymentsBillDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void init_free(PaymentsBillVO paymentsBillVO) throws SQLException {
		sqlSession.insert("PaymentsBill-Mapper.init_free", paymentsBillVO);
	}

	@Override
	public Date getEndDate(String userId) throws SQLException {
		return sqlSession.selectOne("PaymentsBill-Mapper.getEndDate", userId);
	}

	@Override
	public String getPlanNo(String userId) throws SQLException {
		return sqlSession.selectOne("PaymentsBill-Mapper.getPlanNo", userId);
	}

	@Override
	public int getMaxUploadCapacity(String planNo) throws SQLException {
		return sqlSession.selectOne("PaymentsBill-Mapper.getMaxUploadCapacity", planNo);
	}

	@Override
	public int countUserPaymentsBill(String userId) throws SQLException {
		return (Integer)sqlSession.selectOne("PaymentsBill-Mapper.countUserPaymentsBill", userId);
	}

	@Override
	public int getMemoryCapacity(String planNo) throws SQLException {
		return sqlSession.selectOne("PaymentsBill-Mapper.getMemoryCapacity", planNo);
	}

	@Override
	public PaymentsPlanVO getPaymentsPlanVO(String planNo) throws SQLException {
		return sqlSession.selectOne("PaymentsBill-Mapper.getPaymentsPlanVO", planNo);
	}

	@Override
	public void updateUserPlan(PaymentsBillVO pbvo) throws SQLException {
		sqlSession.update("PaymentsBill-Mapper.updateUserPlan", pbvo);
	}

	@Override
	public void insertPaymentHistory(PaySuccessVO psvo) throws SQLException {
		sqlSession.insert("PaymentsBill-Mapper.insertPaymentHistory", psvo);
	}

	@Override
	public List<PaySuccessVO> getPaymentHistoryList(String userId) throws SQLException {
		return sqlSession.selectList("PaymentsBill-Mapper.selectPaymentHistoryListByUserId", userId);
	}
}
