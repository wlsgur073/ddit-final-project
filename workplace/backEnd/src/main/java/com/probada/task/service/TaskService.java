package com.probada.task.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public interface TaskService {

	public Integer getTaskCountByProjectNo(String projNo) throws SQLException;

	public List<TaskVO> getTaskListByProjectNo(String projNo) throws SQLException;

	public TaskVO getTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;

	public int getTaskCountInProjByUserId(UserVO userVO) throws SQLException;

	public void modifyTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;

	public void modifyTaskStatus(TaskVO taskVO) throws SQLException;

	public void removeTaskByTaskNo(TaskVO taskVO) throws SQLException;

	public String registTask(TaskVO taskVO) throws SQLException;
	
	public List<TaskVO> getFormatTaskListByUserId(String userId) throws SQLException;
}