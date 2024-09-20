package com.probada.collabo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.collabo.dao.CollaboMilestoneDAOImpl;
import com.probada.collabo.service.CollaboMilestoneService;
import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboMilestoneVO;
import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;

@Controller
@RequestMapping("/app/collabo")
public class CollaboMilestoneController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboMilestoneController.class);

	@Resource(name="collaboMilestoneService")
	CollaboMilestoneService collaboMilestoneService;
	@Resource(name="collaboUtil")
	CollaboUtil collaboUtil;
	
	
	@RequestMapping("/getMilestoneInfo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getMilestoneInfo(HttpServletRequest request, String cprojNo) throws Exception{
		ResponseEntity<HashMap<String, Object>> entity = null;
		
		LOGGER.debug("[요청받음] => /getMilestoneInfo ");
		
		List<CollaboIssueVO> wholeIssueList = new ArrayList<CollaboIssueVO>();
		HttpSession session = request.getSession();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		try {
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String cprojTitle = collaboUtil.getCollaboNameByCprojNo(cprojNo);
			
			wholeIssueList = collaboMilestoneService.getWholeIssueByCprojNo(cprojNo);
			hashMap.put("userVO", userVO);
			hashMap.put("cprojTitle", cprojTitle);
			hashMap.put("cprojNo", cprojNo);
			hashMap.put("wholeIssueList", wholeIssueList);
			
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getMilestoneInfo 시 에러가 발생했습니다.",e); 
		}
		
		return entity;
	}
	
	@RequestMapping("/registMilestoneDetail")
	@ResponseBody
	public ResponseEntity<CollaboMilestoneVO> registMilestoneDetail(@RequestBody CollaboMilestoneVO collaboMilestoneVO) throws Exception{
		ResponseEntity<CollaboMilestoneVO> entity = null;
		
		LOGGER.debug("[요청받음] => /registMilestoneDetail ");
		
		List<String> afterIssueNoList = new ArrayList<String>();
		CollaboIssueVO collaboIssueVO = new CollaboIssueVO();
		
		try {
			collaboMilestoneService.registMilestoneDetail(collaboMilestoneVO);
			
			afterIssueNoList = collaboMilestoneVO.getIssueNoList();
			if(afterIssueNoList != null) {
				for (String insertNo : afterIssueNoList) {
					collaboIssueVO.setMileNo(collaboMilestoneVO.getMileNo());
					collaboIssueVO.setIssueNo(insertNo);
					collaboMilestoneService.registMileIssueRelation(collaboIssueVO);
				}
			}
			entity = new ResponseEntity<CollaboMilestoneVO>(collaboMilestoneVO,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboMilestoneVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/registMilestoneDetail 시 에러가 발생했습니다.",e);
		}
		
		return entity;
		
	}
	
	
	
}














