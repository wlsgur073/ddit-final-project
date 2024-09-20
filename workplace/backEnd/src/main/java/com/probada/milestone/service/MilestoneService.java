package com.probada.milestone.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;

public interface MilestoneService {

	public List<MilestoneVO> getMilestoneListByUserId(String userId) throws SQLException;

	public List<MilestoneVO> getMilestoneListByProjNo(String projNo) throws SQLException;

	public MilestoneVO getMilestoneByMileNo(String mileNo) throws SQLException;

	public List<IssueVO> getIssueListByMileNo(String mileNo) throws SQLException;

	public List<IssueVO> getWholeIssueByProjNo(String projNo) throws SQLException;

	public void registMilestoneDetail(MilestoneVO milestoneVO) throws SQLException;

	public void modifyMilestoneDetail(MilestoneVO milestoneVO) throws SQLException;

	public void registMileIssueRelation(IssueVO issueVO) throws SQLException;

	public void removeMileIssueRelation(IssueVO issueVO) throws SQLException;

	public void removeMilestone(String mileNo) throws SQLException;
}
