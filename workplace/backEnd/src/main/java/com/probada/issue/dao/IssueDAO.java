package com.probada.issue.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueVO;
import com.probada.issue.vo.MileIssueVO;

public interface IssueDAO {
	public List<IssueVO> selectIssueSortByUserId(String userId) throws SQLException;

	public List<IssueVO> selectIssueListByUserId(String userId) throws SQLException;

	public List<IssueVO> selectIssueListByProjNoAndUserId(IssueVO issueVO) throws SQLException;

	public List<IssueVO> selectIssueListByProjNo(String projNo) throws SQLException;

	public List<MileIssueVO> selectMileIssueListByIssueNo(String issueNo) throws SQLException;

	public IssueVO selectIssueByIssueNo(IssueVO issueVO) throws SQLException;

	public int selectIssueSeqNext() throws SQLException;

	public void updateIssueByIssueNo(IssueVO issueVO) throws SQLException;

	public void insertIssue(IssueVO issueVO) throws SQLException;

	public void deleteIssue(IssueVO issueVO) throws SQLException;
}
