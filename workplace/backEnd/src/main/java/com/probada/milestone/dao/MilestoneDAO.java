package com.probada.milestone.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;

public interface MilestoneDAO {

	public List<MilestoneVO> selectMilestoneListByUserId(String userId) throws SQLException;
	
	public List<MilestoneVO> selectMilestoneListByProjNo(String projNo) throws SQLException;

	public MilestoneVO selectMilestoneByMileNo(String mileNo) throws SQLException;

	public List<IssueVO> selectIssueListByMileNo(String mileNo) throws SQLException;

	public List<IssueVO> selectWholeIssueByProjNo(String projNo) throws SQLException;

	public int selectMileSeqNext() throws SQLException;

	public void insertMilestoneDetail(MilestoneVO milestoneVO) throws SQLException;

	public void updateMilestoneDetail(MilestoneVO milestoneVO) throws SQLException;

	public void insertMileIssueRelation(IssueVO issueVO) throws SQLException;

	public void deleteMileIssueRelation(IssueVO issueVO) throws SQLException;

	public void removeMilestone(String mileNo) throws SQLException;
}
