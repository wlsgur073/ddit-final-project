package com.probada.task.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.task.dao.TaskDAO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskDAO taskDAO;
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}


	@Override
	public Integer getTaskCountByProjectNo(String projNo) throws SQLException {

		int result = taskDAO.selectTaskCountByProjectNo(projNo);

		return result;
	}


	@Override
	public List<TaskVO> getTaskListByProjectNo(String projNo) throws SQLException {

		List<TaskVO> taskVOList = taskDAO.selectTaskListByProjectNo(projNo);

		return taskVOList;
	}


	@Override
	public TaskVO getTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {

		TaskVO resultVO = taskDAO.selectTaskDetailByTaskNo(taskVO);

		return resultVO;
	}


	@Override
	public int getTaskCountInProjByUserId(UserVO userVO) throws SQLException {

		int result = taskDAO.selectTaskCountInProjByUserId(userVO);

		return result;
	}


	@Override
	public void modifyTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {

		taskDAO.updateTaskDetailByTaskNo(taskVO);

	}


	@Override
	public String registTask(TaskVO taskVO) throws SQLException {

		int seq = taskDAO.selectTaskSeqNext();

		String taskNo = Integer.toString(seq);

		taskVO.setTaskNo(taskNo);

		taskDAO.insertTask(taskVO);

		return taskNo;
	}


	@Override
	public void modifyTaskStatus(TaskVO taskVO) throws SQLException {

		taskDAO.updateTaskStatus(taskVO);

	}


	@Override
	public void removeTaskByTaskNo(TaskVO taskVO) throws SQLException {

		taskDAO.deleteTaskByTaskNo(taskVO);

	}


	@Override
	public List<TaskVO> getFormatTaskListByUserId(String userId) throws SQLException {
		return taskDAO.selectFormatTaskListByUserId(userId);
	}

}
