package com.probada.spoon.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.spoon.vo.SpoonVO;
import com.probada.task.vo.TaskVO;

public interface SpoonService {
	
	//업무 번호로 업무 상세 가져오기
	public TaskVO getTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;
	
	//프로젝트 no로 해당 프로젝트 업무 가져오기
	public List<TaskVO> getTaskListByProjectNo(String projNo) throws SQLException;
		
	public void setTaskToCollabo(TaskVO taskVO)throws SQLException;
}
