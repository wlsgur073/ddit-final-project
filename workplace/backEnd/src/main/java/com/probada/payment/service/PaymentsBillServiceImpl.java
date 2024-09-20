package com.probada.payment.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.probada.payment.dao.PaymentsBillDAO;
import com.probada.payment.vo.PaySuccessVO;
import com.probada.payment.vo.PaymentsBillVO;
import com.probada.payment.vo.PaymentsPlanVO;

public class PaymentsBillServiceImpl implements PaymentsBillService{
	
	private PaymentsBillDAO paymentsBillDAO;
	
	public void setPaymentsBillDAO(PaymentsBillDAO paymentsBillDAO) {
		this.paymentsBillDAO = paymentsBillDAO;
	}

	@Override
	public void init_free(PaymentsBillVO paymentsBillVO) throws SQLException {
		paymentsBillDAO.init_free(paymentsBillVO);
	}

	@Override
	public Date getEndDate(String userId) throws SQLException {
		return paymentsBillDAO.getEndDate(userId);
	}

	@Override
	public String getPlanNo(String userId) throws SQLException {
		return paymentsBillDAO.getPlanNo(userId);
	}

	@Override
	public int getMaxUploadCapacity(String planNo) throws SQLException {
		return paymentsBillDAO.getMaxUploadCapacity(planNo);
	}

	@Override
	public int countUserPaymentsBill(String userId) throws SQLException {
		return paymentsBillDAO.countUserPaymentsBill(userId);
	}

	@Override
	public int getMemoryCapacity(String planNo) throws SQLException {
		return paymentsBillDAO.getMemoryCapacity(planNo);
	}

	@Override
	public PaymentsPlanVO getPaymentsPlanVO(String planNo) throws SQLException {
		return paymentsBillDAO.getPaymentsPlanVO(planNo);
	}

	@Override
	public void updateUserPlan(PaymentsBillVO pbvo) throws SQLException {
		paymentsBillDAO.updateUserPlan(pbvo);
	}

	@Override
	public void addPaymentHistory(PaySuccessVO psvo) throws SQLException {
		paymentsBillDAO.insertPaymentHistory(psvo);
	}

	@Override
	public List<PaySuccessVO> getPaymentHistoryList(String userId) throws SQLException {
		return paymentsBillDAO.getPaymentHistoryList(userId);
	}

}
