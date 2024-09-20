package com.probada.spoon.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.dao.CollaboTaskDAO;
import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.spoon.dao.SpoonDAO;
import com.probada.spoon.vo.SpoonVO;
import com.probada.task.vo.TaskVO;

public class SpoonServiceImpl implements SpoonService {
	
	public SpoonDAO spoonDAO;
	public void setSpoonDAO(SpoonDAO spoonDAO) {
		this.spoonDAO = spoonDAO;
	}


	@Override
	public TaskVO getTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {
		TaskVO resultVO = spoonDAO.selectTaskDetailByTaskNo(taskVO);
		return resultVO;
	}


	@Override
	public List<TaskVO> getTaskListByProjectNo(String projNo) throws SQLException {
		List<TaskVO> taskVOList = spoonDAO.selectTaskListByProjectNo(projNo);
		return taskVOList;
	}


	@Override
	public void setTaskToCollabo(TaskVO taskVO) throws SQLException {
		
		int seqNo = spoonDAO.selectTaskSeqNext();
		
		String taskNo = Integer.toString(seqNo);
		
		taskVO.setTaskNo(taskNo);
		
		spoonDAO.insertTaskToCollabo(taskVO);
	}

}
