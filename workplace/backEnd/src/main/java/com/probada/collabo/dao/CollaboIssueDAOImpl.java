package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.issue.vo.IssueVO;
import com.probada.issue.vo.MileIssueVO;

public class CollaboIssueDAOImpl implements CollaboIssueDAO {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboDAOImpl.class);
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<CollaboIssueVO> selectIssueListByCprojNoAndUserId(CollaboIssueVO collaboIssueVO) throws SQLException {
		List<CollaboIssueVO> issueList = sqlSession.selectList("CollaboIssue-Mapper.selectIssueListByCprojNoAndUserId", collaboIssueVO);
		return issueList;
	}

	@Override
	public List<CollaboIssueVO> selectIssueListByCprojNo(String cprojNo) throws SQLException {
		
		List<CollaboIssueVO> issueList = sqlSession.selectList("CollaboIssue-Mapper.selectIssueListByCprojNo", cprojNo);
		return issueList;
	}

	@Override
	public List<MileIssueVO> selectMileIssueListByIssueNo(String issueNo) throws SQLException {
		
		List<MileIssueVO> issueList = sqlSession.selectList("CollaboIssue-Mapper.selectMileIssueListByIssueNo", issueNo);
		
		return issueList;
	}

	@Override
	public CollaboIssueVO selectIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException {
		CollaboIssueVO issueVo = sqlSession.selectOne("CollaboIssue-Mapper.selectIssueByIssueNo", collaboIssueVO);
		return issueVo;
	}

	@Override
	public int selectIssueSeqNext() throws SQLException {
		int seq = sqlSession.selectOne("CollaboIssue-Mapper.selectIssueSeqNext");
		return seq;
	}

	@Override
	public void updateIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException {
		sqlSession.update("CollaboIssue-Mapper.updateIssueByIssueNo", collaboIssueVO);
	}
}
