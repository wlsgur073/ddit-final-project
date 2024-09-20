package com.probada.myWork.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.myWork.command.myTaskChartCommand;
import com.probada.task.vo.TaskVO;

public interface MyTaskService {

	//업무 리스트 출력
	public List<TaskVO> getTaskListByUserId(String userId) throws SQLException;
	
	//업무 리스트 정렬 출력
	public List<TaskVO> getProjectNameForSort(String userId) throws SQLException;
	
	//업무 삭제
	public void deleteMyTaskByTaskNo(String taskNo) throws SQLException;
	
	//업무 상태 변경
	public void modifyMyTaskStatus(TaskVO taskVO) throws SQLException;
	
	//대시보드 업무 차트 출력
	public myTaskChartCommand getMyTaskChartByUserId(String userId) throws SQLException;
}
