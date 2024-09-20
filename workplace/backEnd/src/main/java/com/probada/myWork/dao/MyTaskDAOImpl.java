package com.probada.myWork.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.myWork.command.myTaskChartCommand;
import com.probada.task.vo.TaskVO;

public class MyTaskDAOImpl implements MyTaskDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//업무 리스트 출력
	@Override
	public List<TaskVO> selectTaskListByUserId(String userId) throws SQLException {
		List<TaskVO> myTaskList = sqlSession.selectList("Task-Mapper.selectTaskListByUserId", userId);
		return myTaskList;
	}
	
	//업무 리스트 정렬 출력
	@Override
	public List<TaskVO> selectProjectNameForSort(String userId) throws SQLException {
		List<TaskVO> myProjectNameList = sqlSession.selectList("Task-Mapper.selectProjectNameForSort", userId);
		return myProjectNameList;
	}
	
	//업무 삭제
	@Override
	public void deleteMyTaskByTaskNo(String taskNo) throws SQLException {
		sqlSession.update("Task-Mapper.deleteMyTaskByTaskNo", taskNo);
	}
	
	//업무 상태 변경
	@Override
	public void updateMyTaskStatus(TaskVO taskVO) throws SQLException {
		sqlSession.update("Task-Mapper.updateTaskStatus",taskVO);
	}
	
	//대시보드 업무 차트 출력
	@Override
	public myTaskChartCommand selectMyTaskChartByUserId(String userId) throws SQLException {
		myTaskChartCommand myTaskChartData = sqlSession.selectOne("Task-Mapper.selectMyTaskChartByUserId", userId);
		return myTaskChartData;
	}
}
