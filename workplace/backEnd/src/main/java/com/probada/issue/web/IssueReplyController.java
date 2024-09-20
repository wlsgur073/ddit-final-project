package com.probada.issue.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.issue.service.IssueReplyService;
import com.probada.issue.vo.IssueReplyVO;
import com.probada.issue.vo.IssueVO;
import com.probada.util.IssueUtil;

@Controller
@RequestMapping("/app/issueReply")
public class IssueReplyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IssueReplyController.class);

	@Resource(name="issueReplyService")
	private IssueReplyService issueReplyService;
	@Resource(name="issueUtil")
	private IssueUtil issueUtil;

	/**
	 * 이슈 댓글 출력
	 * @param issueVO
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/getIssueReplyByIssueNo")
	@ResponseBody
	public ResponseEntity<List<IssueReplyVO>> getIssueReplyByIssueNo(IssueVO issueVO) throws SQLException {
		ResponseEntity<List<IssueReplyVO>> entity = null;

		try {
			List<IssueReplyVO> issueReplyList = issueReplyService.getIssueReplyListByIssueNo(issueVO.getIssueNo());
			List<IssueReplyVO> issueReplyList2 = issueUtil.getUserDetailByReplyList(issueReplyList);
			entity = new ResponseEntity<List<IssueReplyVO>>(issueReplyList2,HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<IssueReplyVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("이슈 댓글 리스트 출력 에러", e);
		}
		return entity;
	}

	/**
	 * 이슈 댓글 등록
	 * @param issueReplyVO
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/registIssueReply")
	@ResponseBody
	public ResponseEntity<String> registIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		ResponseEntity<String> entity = null;

		try {
			issueReplyService.registIssueReply(issueReplyVO);
			String issueNo = issueReplyVO.getIssueNo();
			entity = new ResponseEntity<String>(issueNo, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("이슈 댓글 등록 에러", e);
		}
		return entity;
	}

	/**
	 * 이슈 댓글 수정
	 * @param issueReplyVO
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/modifyIssueReply")
	@ResponseBody
	public ResponseEntity<String> modifyIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		ResponseEntity<String> entity = null;

		try {
			issueReplyService.modifyIssueReply(issueReplyVO);
			String issueNo = issueReplyVO.getIssueNo();
			entity = new ResponseEntity<String>(issueNo, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("이슈 댓글 수정 에러", e);
		}
		return entity;
	}

	/**
	 * 이슈 댓글 삭제
	 * @param issueResNo
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/removeIssueReply")
	@ResponseBody
	public ResponseEntity<String> removeIssueReply(String issueResNo) throws SQLException {
		ResponseEntity<String> entity = null;

		try {
			issueReplyService.removeIssueReply(issueResNo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("이슈 댓글 삭제 에러", e);
		}
		return entity;
	}
}
