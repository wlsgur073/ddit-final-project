package com.probada.myWork.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.myWork.command.myTaskChartCommand;
import com.probada.myWork.dao.MyTaskDAO;
import com.probada.task.vo.TaskVO;

public class MyTaskServiceImpl implements MyTaskService {
	
	private MyTaskDAO myTaskDAO;
	public void setMyTaskDAO(MyTaskDAO myTaskDAO) {
		this.myTaskDAO = myTaskDAO;
	}

	//업무 리스트 출력
	@Override
	public List<TaskVO> getTaskListByUserId(String userId) throws SQLException {
		List<TaskVO> myTaskList = myTaskDAO.selectTaskListByUserId(userId);
		return myTaskList;
	}
	
	//업무 리스트 정렬 출력
	@Override
	public List<TaskVO> getProjectNameForSort(String userId) throws SQLException {
		List<TaskVO> myProjectNameList = myTaskDAO.selectProjectNameForSort(userId);
		return myProjectNameList;
	}
	
	//업무 삭제
	@Override
	public void deleteMyTaskByTaskNo(String taskNo) throws SQLException {
		myTaskDAO.deleteMyTaskByTaskNo(taskNo);
	}
	
	//업무 상태 변경
	@Override
	public void modifyMyTaskStatus(TaskVO taskVO) throws SQLException {
		myTaskDAO.updateMyTaskStatus(taskVO);
	}
	
	//대시보드 업무 차트 출력
	@Override
	public myTaskChartCommand getMyTaskChartByUserId(String userId) throws SQLException {
		myTaskChartCommand myTaskChartData = myTaskDAO.selectMyTaskChartByUserId(userId);
		return myTaskChartData;
	}
}
