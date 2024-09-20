package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboMilestoneVO;
import com.probada.issue.vo.IssueVO;
import com.probada.milestone.vo.MilestoneVO;

public interface CollaboMilestoneDAO {
	
	public List<CollaboIssueVO> selectWholeIssueByCprojNo(String cprojNo) throws SQLException;
	
	public void insertMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException;

	public void updateMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException;
	
	public int selectMileSeqNext() throws SQLException;
	
	public void insertMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException;

	public void deleteMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException;
}
