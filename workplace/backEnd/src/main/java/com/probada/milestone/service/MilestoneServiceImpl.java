package com.probada.milestone.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.issue.vo.IssueVO;
import com.probada.milestone.dao.MilestoneDAO;
import com.probada.milestone.vo.MilestoneVO;

public class MilestoneServiceImpl implements MilestoneService {

	private MilestoneDAO milestoneDAO;
	public void setMilestoneDAO(MilestoneDAO milestoneDAO) {
		this.milestoneDAO = milestoneDAO;
	}

	@Override
	public List<MilestoneVO> getMilestoneListByUserId(String userId) throws SQLException {
		List<MilestoneVO> milestoneList = milestoneDAO.selectMilestoneListByUserId(userId);
		return milestoneList;
	}

	@Override
	public List<MilestoneVO> getMilestoneListByProjNo(String projNo) throws SQLException {

		List<MilestoneVO> milestoneList = milestoneDAO.selectMilestoneListByProjNo(projNo);

		return milestoneList;
	}


	@Override
	public MilestoneVO getMilestoneByMileNo(String mileNo) throws SQLException {

		MilestoneVO mileVO = milestoneDAO.selectMilestoneByMileNo(mileNo);

		return mileVO;
	}


	@Override
	public void modifyMilestoneDetail(MilestoneVO milestoneVO) throws SQLException {

		milestoneDAO.updateMilestoneDetail(milestoneVO);

	}


	@Override
	public List<IssueVO> getIssueListByMileNo(String mileNo) throws SQLException {

		List<IssueVO> issueList = milestoneDAO.selectIssueListByMileNo(mileNo);

		return issueList;
	}


	@Override
	public List<IssueVO> getWholeIssueByProjNo(String projNo) throws SQLException {

		List<IssueVO> wholeIssueList = milestoneDAO.selectWholeIssueByProjNo(projNo);

		return wholeIssueList;
	}


	@Override
	public void registMileIssueRelation(IssueVO issueVO) throws SQLException {

		milestoneDAO.insertMileIssueRelation(issueVO);

	}


	@Override
	public void removeMileIssueRelation(IssueVO issueVO) throws SQLException {

		milestoneDAO.deleteMileIssueRelation(issueVO);

	}


	@Override
	public void registMilestoneDetail(MilestoneVO milestoneVO) throws SQLException {

		int seq = milestoneDAO.selectMileSeqNext();
		String mileNo = Integer.toString(seq);
		milestoneVO.setMileNo(mileNo);

		milestoneDAO.insertMilestoneDetail(milestoneVO);

	}

	@Override
	public void removeMilestone(String mileNo) throws SQLException {
		milestoneDAO.removeMilestone(mileNo);
	}
}
