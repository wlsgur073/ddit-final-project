package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public interface CollaboTaskDAO {
	
	public List<CollaboTaskVO> selectTaskListByCprojNo(String cprojNo)throws SQLException;
	
	public CollaboTaskVO selectTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	public int selectTaskSeqNext() throws SQLException;
	
	public void updateTaskStatus(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	//해당 아이디의 업무 수 카운트
	public int selectTaskCountInCprojByUserId(UserVO userVO) throws SQLException;
	
	//업무 등록
	public void insertTask(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	//업무 상세 수정
	public void updateTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	//업무 삭제
	public void deleteTaskByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
}
