package com.probada.task.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public interface TaskDAO {

	public Integer selectTaskCountByProjectNo(String projNo) throws SQLException;

	public List<TaskVO> selectTaskListByProjectNo(String projNo) throws SQLException;

	public TaskVO selectTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;

	public int selectTaskCountInProjByUserId(UserVO userVO) throws SQLException;

	public int selectTaskSeqNext() throws SQLException;

	public void updateTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;

	public void updateTaskStatus(TaskVO taskVO) throws SQLException;

	public void deleteTaskByTaskNo(TaskVO taskVO) throws SQLException;

	public void insertTask(TaskVO taskVO) throws SQLException;

	public List<TaskVO> selectFormatTaskListByUserId(String userId) throws SQLException;
}
