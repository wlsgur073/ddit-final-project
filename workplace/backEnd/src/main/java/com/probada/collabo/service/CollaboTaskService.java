package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;


public interface CollaboTaskService {
	
	public List<CollaboTaskVO> getTaskListByCprojNo(String cprojNo) throws SQLException;
	
	public void modifyTaskStatus(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	public CollaboTaskVO getTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	public int getTaskCountInCprojByUserId(UserVO userVO) throws SQLException;
	
	public String registTask(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	public void modifyTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	public void removeTaskByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
}
