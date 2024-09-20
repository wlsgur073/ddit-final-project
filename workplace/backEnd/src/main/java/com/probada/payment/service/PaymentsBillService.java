package com.probada.payment.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.probada.payment.vo.PaySuccessVO;
import com.probada.payment.vo.PaymentsBillVO;
import com.probada.payment.vo.PaymentsPlanVO;

public interface PaymentsBillService {
	
	/**
	 * @param paymentsBillVO
	 * @throws SQLException
	 */
	public void init_free(PaymentsBillVO paymentsBillVO) throws SQLException;
	
	/**
	 * ENDDATE를 비교하여 사용 기한이 만료되었는지 확인하는 function
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Date getEndDate(String userId) throws SQLException;
	
	/**
	 * 유저 아이디를 통해 PAYMENTS_BILL의 PLAN_NO를 리턴한다.
	 * @param userId
	 * @return PAYMENTS_BILL테이블에 속한 해당 유저의 PLAN_NO
	 * @throws SQLException
	 */
	public String getPlanNo(String userId) throws SQLException;
	
	/**
	 * getPlanNo에서 가져온 PLAN_NO에 해당하는 MAX_UPLOAD_CAPACITY를 리턴한다.
	 * @param planNo
	 * @return PLAN_NO에 해당하는 MAX_UPLOAD_CAPACITY를 리턴한다.
	 * @throws SQLException
	 */
	public int getMaxUploadCapacity(String planNo)throws SQLException;
	
	/**
	 * 유저 이메일 주소를 파라미터로 받아서 해당 유저의 PaymentsBill 갯수를 확인한다.
	 * 1이면 가입중인 Payment가 있으며, 0이면 가입한 Payment가 없다.
	 * @param String userId
	 * @return int
	 * @throws SQLException
	 */
	public int countUserPaymentsBill(String userId) throws SQLException;
	
	/**
	 * getPlanNo에서 가져온 PLAN_NO에 해당하는 MEMORY_CAPACITY를 리턴한다.
	 * @param planNo
	 * @return
	 * @throws SQLException
	 */
	public int getMemoryCapacity(String planNo) throws SQLException;
	
	/**
	 * planNo를 받아서 해당 유료 플랜 vo를 리턴한다.
	 * @param String planNo
	 * @return PaymentsPlanVO
	 * @throws SQLException
	 */
	public PaymentsPlanVO getPaymentsPlanVO(String planNo) throws SQLException;
	
	/**
	 * 플랜 결제가 성공적으로 완료가 되면 유저의 유료 플랜을 변경시켜주는 메서드
	 * @param PaymentsBillVO pbvo
	 * @throws SQLException
	 */
	public void updateUserPlan(PaymentsBillVO pbvo) throws SQLException;
	
	/**
	 * PAYMENTS_HISTORY 테이블에 데이터를 저장한다.
	 * @param PaySuccessVO psvo
	 * @throws SQLException
	 */
	public void addPaymentHistory(PaySuccessVO psvo) throws SQLException;
	
	/**
	 * 유저 아이디를 참조하여 PAYMENTS_HISTORY 테이블에서 해당 데이터 리스트를 리턴한다.
	 * @param String userId
	 * @return List<PaySuccessVO>
	 * @throws SQLException
	 */
	public List<PaySuccessVO> getPaymentHistoryList(String userId) throws SQLException;
}
