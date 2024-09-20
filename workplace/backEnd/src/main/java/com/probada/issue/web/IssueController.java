package com.probada.issue.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.probada.issue.service.IssueService;
import com.probada.issue.vo.IssueVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;
import com.probada.util.DocumentUtil;
import com.probada.util.IssueUtil;
import com.probada.util.ProjectUtil;

@Controller
@RequestMapping("/app/issue")
public class IssueController {

	@Resource(name="issueService")
	private IssueService issueService;
	@Resource(name="issueUtil")
	IssueUtil issueUtil;
	@Resource(name="projectUtil")
	ProjectUtil projectUtil;
	@Resource(name = "documentUtil")
	DocumentUtil documentUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IssueController.class);

	@RequestMapping("/getIssueSortByUserId")
	@ResponseBody
	public ResponseEntity<List<IssueVO>> getIssueSortByUserId(@RequestParam(defaultValue="") String userId) throws SQLException {
		ResponseEntity<List<IssueVO>> entity = null;
		List<IssueVO> issueSort = null;

		try {
			issueSort = issueService.getIssueSortByUserId(userId);

			entity = new ResponseEntity<List<IssueVO>>(issueSort,HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<IssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getIssueListByUserId")
	@ResponseBody
	public ResponseEntity<List<IssueVO>> getIssueListByUserId(@RequestParam(defaultValue="") String userId) throws SQLException {
		ResponseEntity<List<IssueVO>> entity = null;
		List<IssueVO> issueList = null;

		try {
			issueList = issueService.getIssueListByUserId(userId);
			issueList = issueUtil.getMileListByIssueList(issueList);
			entity = new ResponseEntity<List<IssueVO>>(issueList,HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<IssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getIssueListByProjNoAndUserId")
	@ResponseBody
	public ResponseEntity<List<IssueVO>> getIssueListByProjNoAndUserId(@RequestParam(defaultValue="") String userId, String projNo) throws SQLException {
		ResponseEntity<List<IssueVO>> entity = null;
		List<IssueVO> issueList = null;
		IssueVO issueVO = new IssueVO();

		try {
			issueVO.setUserId(userId);
			issueVO.setProjNo(projNo);

			issueList = issueService.getIssueListByProjNoAndUserId(issueVO);
			issueList = issueUtil.getMileListByIssueList(issueList);

			entity = new ResponseEntity<List<IssueVO>>(issueList,HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<IssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getIssueListByProjNo")
	@ResponseBody
	public ResponseEntity<List<IssueVO>> getIssueListByProjNo(@RequestParam(defaultValue="") String projNo) throws SQLException {
		ResponseEntity<List<IssueVO>> entity = null;

		LOGGER.debug("[요청받음] => /getIssueListByProjNo");

		List<IssueVO> issueList = new ArrayList<IssueVO>();

		try {
			issueList = issueService.getIssueListByProjNo(projNo);
			issueList = issueUtil.getMileListByIssueList(issueList);

			entity = new ResponseEntity<List<IssueVO>>(issueList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<IssueVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return entity;
	}

	@RequestMapping("/getIssueByIssueNo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> getIssueByIssueNo(HttpServletRequest request, IssueVO issueVO) throws SQLException {
		ResponseEntity<HashMap<String, Object>> entity = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {
			String projTitle = projectUtil.getProjectNameByProjNo(issueVO.getProjNo());
			issueVO = issueService.getIssueByIssueNo(issueVO);
			issueVO = issueUtil.getMileListByIssueNo(issueVO);
			issueVO = documentUtil.readIssueDocByIssueTitleAndProjNo(issueVO);
					
			hashMap.put("issueVO", issueVO);
			hashMap.put("projTitle", projTitle);
			
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/modifyIssueByIssueNo")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modifyIssueByIssueNo(HttpServletRequest request
																	  , HttpServletResponse response
																	  , @RequestPart(value="files", required=false) List<MultipartFile> files
																	  , IssueVO issueVO) throws SQLException {
		ResponseEntity<HashMap<String, Object>> entity = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {
			issueService.modifyIssueByIssueNo(issueVO);
			documentUtil.issueUpload(files, request, response, issueVO);
			
			hashMap.put("issueNo", issueVO.getIssueNo());
			
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/removeIssue")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> removeIssue(IssueVO issueVO) throws SQLException {
		ResponseEntity<HashMap<String, Object>> entity = null;

		LOGGER.debug("[요청받음] => /removeIssue");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {


			issueService.removeIssue(issueVO);

			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();

		}

		return entity;
	}


	@RequestMapping("/registIssue")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> registIssue(HttpServletRequest request
															 , HttpServletResponse response
															 , @RequestPart(value="files", required=false) List<MultipartFile> files
															 , IssueVO issueVO) throws SQLException {
		ResponseEntity<HashMap<String, Object>> entity = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		try {
			issueService.registIssue(issueVO);
			documentUtil.issueUpload(files, request, response, issueVO);
			
			hashMap.put("issueNo", issueVO.getIssueNo());

			entity = new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getIssueRegistInfoByProjNo")
	@ResponseBody
	public ResponseEntity<IssueVO> getIssueRegistInfoByProjNo(HttpServletRequest request,String projNo) throws Exception {
		ResponseEntity<IssueVO> entity = null;

		HttpSession session = request.getSession();
		IssueVO issueVO = new IssueVO();
		try {

			UserVO userVO = (UserVO) session.getAttribute("userVO");

			String projTitle = projectUtil.getProjectNameByProjNo(projNo);
			issueVO.setProjTitle(projTitle);
			issueVO.setMember(userVO);

			entity = new ResponseEntity<IssueVO>(issueVO, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<IssueVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
