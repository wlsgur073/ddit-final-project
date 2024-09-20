package com.probada.issue.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.issue.vo.IssueReplyVO;

public class IssueReplyDAOImpl implements IssueReplyDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//이슈 댓글 출력
	@Override
	public List<IssueReplyVO> selectIssueReplyListByIssueNo(String issueNo) throws SQLException {
		List<IssueReplyVO> replyVO = sqlSession.selectList("IssueReply-Mapper.selectIssueReplyListByIssueNo", issueNo);
		return replyVO;
	}
	
	//이슈 댓글 등록
	@Override
	public void insertIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		sqlSession.update("IssueReply-Mapper.insertIssueReply", issueReplyVO);
	}
	
	//이슈 댓글 수정
	@Override
	public void updateIssueReply(IssueReplyVO issueReplyVO) throws SQLException {
		sqlSession.update("IssueReply-Mapper.updateIssueReply", issueReplyVO);
	}
	
	//이슈 댓글 삭제
	@Override
	public void deleteIssueReply(String issueResNo) throws SQLException {
		sqlSession.update("IssueReply-Mapper.deleteIssueReply", issueResNo);
	}
}
