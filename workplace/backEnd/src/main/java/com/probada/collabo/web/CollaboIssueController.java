package com.probada.collabo.web;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.collabo.service.CollaboIssueService;
import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.issue.vo.IssueVO;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.DocumentUtil;
import com.probada.util.IssueUtil;

@Controller
@RequestMapping("/app/collabo")
public class CollaboIssueController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboIssueController.class);
	
	@Resource(name="collaboIssueService")
	private CollaboIssueService collaboIssueService;
	@Resource(name="issueUtil")
	IssueUtil issueUtil;
	@Resource(name="collaboUtil")
	CollaboUtil collaboUtil;
	@Resource(name = "documentUtil")
	DocumentUtil documentUtil;
	
	
	@RequestMapping("/getIssueListByCprojNoAndUserId")
	@ResponseBody
	public ResponseEntity<List<CollaboIssueVO>> getIssueListByCprojNoAndUserId(@RequestParam(defaultValue="") String userId, String cprojNo) throws SQLException{
		
		LOGGER.debug("[요청받음] => /getIssueListByCprojNoAndUserId ");
		
		ResponseEntity<List<CollaboIssueVO>> entity = null;
		List<CollaboIssueVO> issueList = null;
		CollaboIssueVO collaboIssueVO = new CollaboIssueVO();
		
		
		try {
			collaboIssueVO.setUserId(userId);
			collaboIssueVO.setCprojNo(cprojNo);
			
			issueList = collaboIssueService.getIssueListByCprojNoAndUserId(collaboIssueVO);
			issueList = collaboUtil.getMileListByIssueList(issueList);
			
			entity = new ResponseEntity<List<CollaboIssueVO>>(issueList,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboIssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getIssueListByCprojNoAndUserId 시 에러가 발생했습니다.",e); 
		}
		
		return entity;
		
	}
	
	@RequestMapping("/getIssueListByCprojNo")
	@ResponseBody
	public ResponseEntity<List<CollaboIssueVO>> getIssueListByCprojNo(@RequestParam(defaultValue="") String cprojNo) throws SQLException{
		ResponseEntity<List<CollaboIssueVO>> entity = null;
		LOGGER.debug("[요청받음] => /getIssueListByCprojNo");
		
		List<CollaboIssueVO> issueList = new ArrayList<CollaboIssueVO>();
		
		try {
			issueList = collaboIssueService.getIssueListByCprojNo(cprojNo);
			issueList = collaboUtil.getMileListByIssueList(issueList);
			
			entity = new ResponseEntity<List<CollaboIssueVO>>(issueList,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboIssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getIssueListByCprojNo 시 에러가 발생했습니다.",e); 
		}
		return entity;
	}
	
	@RequestMapping("/getIssueRegistInfoByCprojNo")
	@ResponseBody
	public ResponseEntity<CollaboIssueVO> getIssueRegistInfoByCprojNo(HttpServletRequest request,String cprojNo) throws Exception{
		ResponseEntity<CollaboIssueVO> entity = null;
		
		HttpSession session = request.getSession();
		CollaboIssueVO collaboIssueVO = new CollaboIssueVO();
		
		try {
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			
			String cprojTitle = collaboUtil.getCollaboNameByCprojNo(cprojNo);
			collaboIssueVO.setCprojTitle(cprojTitle);
			collaboIssueVO.setMember(userVO);
			
			entity = new ResponseEntity<CollaboIssueVO>(collaboIssueVO, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboIssueVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getIssueRegistInfoByCprojNo 시 에러가 발생했습니다.",e); 
		}
		
		return entity;
		
	}
	
	@RequestMapping("/getIssueByIssueNo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getIssueByIssueNo(HttpServletRequest request, CollaboIssueVO collaboIssueVO) throws SQLException{
		ResponseEntity<HashMap<String, Object>> entity = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		
		try {
			String cprojtitle = collaboUtil.getCollaboNameByCprojNo(collaboIssueVO.getCprojNo());
			collaboIssueVO = collaboIssueService.getIssueByIssueNo(collaboIssueVO);
			collaboIssueVO = collaboUtil.getMileListByIssueNo(collaboIssueVO);
			//collaboIssueVO = documentUtil.
			
			hashMap.put("collaboIssueVO", collaboIssueVO);
			hashMap.put("cprojtitle", cprojtitle);
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			
		}
		
		return entity;
		
	}
	
}








