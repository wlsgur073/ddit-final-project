package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboMilestoneVO;
import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;

public interface CollaboMilestoneService {
	
	public List<CollaboIssueVO> getWholeIssueByCprojNo(String cprojNo) throws SQLException;
	
	public void registMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException;

	public void modifyMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException;
	
	public void registMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException;

	public void removeMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException;
	
	
}
