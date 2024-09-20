package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboMilestoneVO;
import com.probada.issue.vo.IssueVO;

public class CollaboMilestoneDAOImpl implements CollaboMilestoneDAO {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboMilestoneDAOImpl.class);
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<CollaboIssueVO> selectWholeIssueByCprojNo(String cprojNo) throws SQLException {
		
		List<CollaboIssueVO> wholeIssueList = sqlSession.selectList("CollaboMilestone-Mapper.selectWholeIssueByCprojNo", cprojNo);
		
		return wholeIssueList;
	}

	@Override
	public void insertMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException {
		sqlSession.update("CollaboMilestone-Mapper.insertMilestoneDetail", collaboMilestoneVO);
		
	}

	@Override
	public void updateMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException {
		sqlSession.update("CollaboMilestone-Mapper.updateMilestoneDetail", collaboMilestoneVO);
	}

	@Override
	public int selectMileSeqNext() throws SQLException {
		int seq = sqlSession.selectOne("CollaboMilestone-Mapper.selectMileSeqNext");

		return seq;
	}

	@Override
	public void insertMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException {
		sqlSession.update("CollaboMilestone-Mapper.insertMileIssueRelation", collaboIssueVO);
	}

	@Override
	public void deleteMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException {
		sqlSession.delete("CollaboMilestone-Mapper.deleteMileIssueRelation", collaboIssueVO);
		
	}

}
