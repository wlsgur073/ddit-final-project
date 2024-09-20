package com.probada.issue.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueReplyVO;

public interface IssueReplyDAO {
	
	//이슈 댓글 출력
	public List<IssueReplyVO> selectIssueReplyListByIssueNo(String issueNo) throws SQLException;
	
	//이슈 댓글 등록
	public void insertIssueReply(IssueReplyVO issueReplyVO) throws SQLException;
	
	//이슈 댓글 수정
	public void updateIssueReply(IssueReplyVO issueReplyVO) throws SQLException;
	
	//이슈 댓글 삭제
	public void deleteIssueReply(String issueResNo) throws SQLException;
}
