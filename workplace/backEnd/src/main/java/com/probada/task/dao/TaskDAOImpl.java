package com.probada.task.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public class TaskDAOImpl implements TaskDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Integer selectTaskCountByProjectNo(String projNo) throws SQLException {

		int result = sqlSession.selectOne("Task-Mapper.selectTaskCountByProjectNo", projNo);

		return result;
	}

	@Override
	public List<TaskVO> selectTaskListByProjectNo(String projNo) throws SQLException {

		List<TaskVO> taskVOList = sqlSession.selectList("Task-Mapper.selectTaskListByProjectNo", projNo);

		return taskVOList;
	}

	@Override
	public TaskVO selectTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {

		TaskVO resultVO = sqlSession.selectOne("Task-Mapper.selectTaskDetailByTaskNo", taskVO);

		return resultVO;
	}

	@Override
	public int selectTaskCountInProjByUserId(UserVO userVO) throws SQLException {

		int result = sqlSession.selectOne("Task-Mapper.selectTaskCountInProjByUserId", userVO);

		return result;
	}

	@Override
	public void updateTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {

		sqlSession.update("Task-Mapper.updateTaskDetailByTaskNo",taskVO);

	}

	@Override
	public void insertTask(TaskVO taskVO) throws SQLException {

		sqlSession.update("Task-Mapper.insertTask",taskVO);

	}

	@Override
	public int selectTaskSeqNext() throws SQLException {

		int seq = sqlSession.selectOne("Task-Mapper.selectTaskSeqNext");

		return seq;
	}

	@Override
	public void updateTaskStatus(TaskVO taskVO) throws SQLException {

		sqlSession.update("Task-Mapper.updateTaskStatus",taskVO);

	}

	@Override
	public void deleteTaskByTaskNo(TaskVO taskVO) throws SQLException {

		sqlSession.delete("Task-Mapper.deleteTaskByTaskNo", taskVO);

	}

	@Override
	public List<TaskVO> selectFormatTaskListByUserId(String userId) throws SQLException {
		return sqlSession.selectList("Task-Mapper.selectFormatTaskListByUserId", userId);
	}

}
