package com.probada.payment.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.payment.service.PaymentsBillService;
import com.probada.payment.vo.PaySuccessVO;
import com.probada.payment.vo.PaymentsBillVO;
import com.probada.payment.vo.PaymentsPlanVO;
import com.probada.user.vo.UserVO;

@Controller
public class PaymentController {
	
	@Autowired
	private PaymentsBillService paymentsBillService;
	

	@ResponseBody
	@RequestMapping(value="/app/getPaymentsPlanVO.do", method = RequestMethod.POST)
	public PaymentsPlanVO getPaymentsPlanVO (@RequestParam("planNo") String planNo) {
		PaymentsPlanVO ppvo = new PaymentsPlanVO();
		
		try {
			ppvo = paymentsBillService.getPaymentsPlanVO(planNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ppvo;
	}
	
	@ResponseBody
	@RequestMapping("/app/paySuccess.do")
	public String paySuccess (@RequestBody PaySuccessVO psvo, HttpServletRequest req) {
		String ret = "success";
		
		HttpSession session = req.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String userId = userVO.getUserId();
		
		psvo.setUserId(userId);
		
		try {
			updateUserPlan(psvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	@ResponseBody
	@RequestMapping("/getPaymentHistory.do")
	public List<PaySuccessVO> getPaymentHistory(HttpSession session) {
		List<PaySuccessVO> psvoList = new ArrayList<PaySuccessVO>();
		
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		try {
			psvoList = paymentsBillService.getPaymentHistoryList(userVO.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return psvoList;
	}
	

	private void updateUserPlan(PaySuccessVO psvo) throws Exception {
		PaymentsBillVO pbvo = new PaymentsBillVO();
		
//		결제가 완료됬다면
		if(psvo.getStatus().equals("paid")) {
//			결제한 플랜이 다음과 동일하다면
			if(psvo.getPlanName().equals("BASIC")) {
//				결제한 금액이 해당 플랜의 가격과 동일하다면
				if(psvo.getPrice() == 5900) {
					pbvo.setPlanNo("2");
					pbvo.setUserId(psvo.getUserId());
					paymentsBillService.updateUserPlan(pbvo);
					paymentsBillService.addPaymentHistory(psvo);
				} else {
					throw new Exception("결제 금액이 잘못되었습니다.");
				}
			} else {
				if(psvo.getPrice() == 9900) {
					pbvo.setPlanNo("3");
					pbvo.setUserId(psvo.getUserId());
					paymentsBillService.updateUserPlan(pbvo);
					paymentsBillService.addPaymentHistory(psvo);
				} else {
					throw new Exception("결제 금액이 잘못되었습니다.");
				}
			}
			
		} else {
			throw new Exception("결제가 완료되지 않았습니다.");
		}
	}
	
}
