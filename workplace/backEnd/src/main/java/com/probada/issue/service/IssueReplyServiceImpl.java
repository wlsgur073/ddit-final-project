package com.probada.issue.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.issue.dao.IssueReplyDAO;
import com.probada.issue.vo.IssueReplyVO;

public class IssueReplyServiceImpl implements IssueReplyService {

	private IssueReplyDAO issueReplyDAO;
	public void setIssueReplyDAO(IssueReplyDAO issueReplyDAO) {
		this.issueReplyDAO = issueReplyDAO;
	}
	
	//이슈 댓글 출력
	@Override
	public List<IssueReplyVO> getIssueReplyListByIssueNo(String issueNo) throws SQLException {
		List<IssueReplyVO> issueReplyList = issueReplyDAO.selectIssueReplyListByIssueNo(issueNo);
		return issueReplyList;
	}
	
	//이슈 댓글 등록
	@Override
	public void registIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		issueReplyDAO.insertIssueReply(issueReplyVO);
	}

	//이슈 댓글 수정
	@Override
	public void modifyIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		issueReplyDAO.updateIssueReply(issueReplyVO);
	}
	
	//이슈 댓글 삭제
	@Override
	public void removeIssueReply(String issueResNo) throws SQLException {
		issueReplyDAO.deleteIssueReply(issueResNo);
	}
}
