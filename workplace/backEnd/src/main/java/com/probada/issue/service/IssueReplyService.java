package com.probada.issue.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueReplyVO;

public interface IssueReplyService {
	
	//이슈 댓글 출력
	public List<IssueReplyVO> getIssueReplyListByIssueNo(String issueNo) throws SQLException;

	//이슈 댓글 등록
	public void registIssueReply(IssueReplyVO issueReplyVO) throws SQLException;
	
	//이슈 댓글 수정
	public void modifyIssueReply(IssueReplyVO issueReplyVO) throws SQLException;
	
	//이슈 댓글 삭제
	public void removeIssueReply(String issueResNo) throws SQLException;
}
