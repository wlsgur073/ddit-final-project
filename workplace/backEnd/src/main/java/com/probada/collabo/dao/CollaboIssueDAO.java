package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.issue.vo.IssueVO;
import com.probada.issue.vo.MileIssueVO;

public interface CollaboIssueDAO {
	
	public List<CollaboIssueVO> selectIssueListByCprojNoAndUserId(CollaboIssueVO collaboIssueVO) throws SQLException;

	public List<CollaboIssueVO> selectIssueListByCprojNo(String cprojNo) throws SQLException;
	
	public List<MileIssueVO> selectMileIssueListByIssueNo(String issueNo) throws SQLException;
	
	public CollaboIssueVO selectIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException;

	public int selectIssueSeqNext() throws SQLException;

	public void updateIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException;
}
