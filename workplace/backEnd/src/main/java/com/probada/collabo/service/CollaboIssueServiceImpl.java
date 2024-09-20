package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.dao.CollaboIssueDAO;
import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.issue.vo.MileIssueVO;

public class CollaboIssueServiceImpl implements CollaboIssueService {
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboServiceImpl.class);
	
	private CollaboIssueDAO collaboIssueDAO;

	public void setCollaboIssueDAO(CollaboIssueDAO collaboIssueDAO) {
		this.collaboIssueDAO = collaboIssueDAO;
	}

	@Override
	public List<CollaboIssueVO> getIssueListByCprojNoAndUserId(CollaboIssueVO collaboIssueVO) throws SQLException {
		List<CollaboIssueVO> issueList = collaboIssueDAO.selectIssueListByCprojNoAndUserId(collaboIssueVO);
		return issueList;
	}

	@Override
	public List<CollaboIssueVO> getIssueListByCprojNo(String cprojNo) throws SQLException {
		List<CollaboIssueVO> issueList = collaboIssueDAO.selectIssueListByCprojNo(cprojNo);
		return issueList;
	}

	@Override
	public List<MileIssueVO> selectMileIssueListByIssueNo(String issueNo) throws SQLException {
		List<MileIssueVO> mileIssueList = collaboIssueDAO.selectMileIssueListByIssueNo(issueNo);
		return mileIssueList;
	}

	@Override
	public CollaboIssueVO getIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException {
		CollaboIssueVO resultIssueVO = collaboIssueDAO.selectIssueByIssueNo(collaboIssueVO);
		return resultIssueVO;
	}

	@Override
	public void modifyIssueByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException {
		collaboIssueDAO.updateIssueByIssueNo(collaboIssueVO);
		
	}

	
}
