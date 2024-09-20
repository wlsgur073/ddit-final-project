package com.probada.milestone.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;

public class MilestoneDAOImpl implements MilestoneDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<MilestoneVO> selectMilestoneListByUserId(String userId) throws SQLException {
		List<MilestoneVO> milestoneList = sqlSession.selectList("Milestone-Mapper.selectMilestoneListByUserId", userId);
		return milestoneList;
	}

	@Override
	public List<MilestoneVO> selectMilestoneListByProjNo(String projNo) throws SQLException {

		List<MilestoneVO> milestoneList =
				sqlSession.selectList("Milestone-Mapper.selectMilestoneListByProjNo", projNo);

		return milestoneList;
	}

	@Override
	public MilestoneVO selectMilestoneByMileNo(String mileNo) throws SQLException {

		MilestoneVO mileVO = sqlSession.selectOne("Milestone-Mapper.selectMilestoneByMileNo", mileNo);

		return mileVO;
	}

	@Override
	public void updateMilestoneDetail(MilestoneVO milestoneVO) throws SQLException {

		sqlSession.update("Milestone-Mapper.updateMilestoneDetail", milestoneVO);
	}

	@Override
	public List<IssueVO> selectIssueListByMileNo(String mileNo) throws SQLException {

		List<IssueVO> issueList = sqlSession.selectList("Milestone-Mapper.selectIssueListByMileNo",mileNo);

		return issueList;
	}

	@Override
	public List<IssueVO> selectWholeIssueByProjNo(String projNo) throws SQLException {

		List<IssueVO> wholeIssueList = sqlSession.selectList("Milestone-Mapper.selectWholeIssueByProjNo",projNo);

		return wholeIssueList;
	}

	@Override
	public void insertMileIssueRelation(IssueVO issueVO) throws SQLException {

		sqlSession.update("Milestone-Mapper.insertMileIssueRelation",issueVO);

	}

	@Override
	public void deleteMileIssueRelation(IssueVO issueVO) throws SQLException {

		sqlSession.delete("Milestone-Mapper.deleteMileIssueRelation",issueVO);

	}

	@Override
	public int selectMileSeqNext() throws SQLException {

		int seq = sqlSession.selectOne("Milestone-Mapper.selectMileSeqNext");

		return seq;
	}

	@Override
	public void insertMilestoneDetail(MilestoneVO milestoneVO) throws SQLException {

		sqlSession.update("Milestone-Mapper.insertMilestoneDetail", milestoneVO);

	}

	@Override
	public void removeMilestone(String mileNo) throws SQLException {
		sqlSession.update("Milestone-Mapper.deleteMileIssue", mileNo);
		sqlSession.update("Milestone-Mapper.deleteMilestone", mileNo);
	}
}
