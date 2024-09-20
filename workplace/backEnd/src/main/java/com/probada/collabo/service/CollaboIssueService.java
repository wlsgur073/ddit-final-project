package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.issue.vo.IssueVO;
import com.probada.issue.vo.MileIssueVO;

public interface CollaboIssueService {
	
	
	public List<CollaboIssueVO> getIssueListByCprojNoAndUserId(CollaboIssueVO collaboIssueVO) throws SQLException;

	public List<CollaboIssueVO> getIssueListByCprojNo(String cprojNo) throws SQLException;
	
	public List<MileIssueVO> selectMileIssueListByIssueNo(String issueNo) throws SQLException;
	
	public CollaboIssueVO getIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException;

	public void modifyIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException;
}
