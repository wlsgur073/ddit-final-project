package com.probada.spoon.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.spoon.vo.SpoonVO;
import com.probada.task.vo.TaskVO;

public interface SpoonDAO {
	
	//task 상세에서 taskNo로 정보 조회
	public TaskVO selectTaskDetailByTaskNo(TaskVO taskVO) throws SQLException;
	
	//해당 프로젝트 no에 해당하는 업무 조회
	public List<TaskVO> selectTaskListByProjectNo(String projNo) throws SQLException;
	
	public int selectTaskSeqNext() throws SQLException;
	
	public void insertTaskToCollabo(TaskVO taskVO)throws SQLException;
	
	
}
