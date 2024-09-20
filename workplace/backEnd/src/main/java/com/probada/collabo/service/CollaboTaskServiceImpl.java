package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.dao.CollaboTaskDAO;
import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public class CollaboTaskServiceImpl implements CollaboTaskService {
	
	private CollaboTaskDAO collaboTaskDAO;
	public void setCollaboTaskDAO(CollaboTaskDAO collaboTaskDAO) {
		this.collaboTaskDAO = collaboTaskDAO;
	}


	@Override
	public List<CollaboTaskVO> getTaskListByCprojNo(String cprojNo) throws SQLException {
		
		List<CollaboTaskVO> taskVOList = collaboTaskDAO.selectTaskListByCprojNo(cprojNo);
		
		return taskVOList;
	}


	@Override
	public void modifyTaskStatus(CollaboTaskVO collaboTaskVO) throws SQLException {
		collaboTaskDAO.updateTaskStatus(collaboTaskVO);
	}


	@Override
	public String registTask(CollaboTaskVO collaboTaskVO) throws SQLException {
		
		int seq = collaboTaskDAO.selectTaskSeqNext();
		String taskNo = Integer.toString(seq);
		collaboTaskVO.setTaskNo(taskNo);
		collaboTaskDAO.insertTask(collaboTaskVO);
		
		return taskNo;
	}


	@Override
	public CollaboTaskVO getTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {
		CollaboTaskVO result = collaboTaskDAO.selectTaskDetailByTaskNo(collaboTaskVO);
		return result;
	}


	@Override
	public void modifyTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {

		collaboTaskDAO.updateTaskDetailByTaskNo(collaboTaskVO);
	}


	@Override
	public void removeTaskByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {
		
		collaboTaskDAO.deleteTaskByTaskNo(collaboTaskVO);
	}


	@Override
	public int getTaskCountInCprojByUserId(UserVO userVO) throws SQLException {
		
		int result = collaboTaskDAO.selectTaskCountInCprojByUserId(userVO);

		return result;
	}



}
