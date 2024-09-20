package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.dao.CollaboMilestoneDAO;
import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboMilestoneVO;
import com.probada.issue.vo.IssueVO;
import com.probada.util.CollaboUtil;

public class CollaboMilestoneServiceImpl implements CollaboMilestoneService {
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboMilestoneServiceImpl.class);
	
	private CollaboMilestoneDAO collaboMilestoneDAO;
	public void setCollaboMilestoneDAO(CollaboMilestoneDAO collaboMilestoneDAO) {
		this.collaboMilestoneDAO = collaboMilestoneDAO;
	}
	
	
	@Override
	public List<CollaboIssueVO> getWholeIssueByCprojNo(String cprojNo) throws SQLException {
		
		List<CollaboIssueVO> wholeIssueList = collaboMilestoneDAO.selectWholeIssueByCprojNo(cprojNo);
		
		return wholeIssueList;
	}


	@Override
	public void registMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException {

		int seq = collaboMilestoneDAO.selectMileSeqNext();
		String mileNo = Integer.toString(seq);
		collaboMilestoneVO.setMileNo(mileNo);
		
		collaboMilestoneDAO.insertMilestoneDetail(collaboMilestoneVO);
	}


	@Override
	public void modifyMilestoneDetail(CollaboMilestoneVO collaboMilestoneVO) throws SQLException {
		
		collaboMilestoneDAO.updateMilestoneDetail(collaboMilestoneVO);
	}


	@Override
	public void registMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException {
		
		collaboMilestoneDAO.insertMileIssueRelation(collaboIssueVO);
	}


	@Override
	public void removeMileIssueRelation(CollaboIssueVO collaboIssueVO) throws SQLException {
		
		collaboMilestoneDAO.deleteMileIssueRelation(collaboIssueVO);
	}
	
}
