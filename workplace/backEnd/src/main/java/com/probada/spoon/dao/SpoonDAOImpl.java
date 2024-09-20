package com.probada.spoon.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.spoon.vo.SpoonVO;
import com.probada.task.vo.TaskVO;

public class SpoonDAOImpl implements SpoonDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public TaskVO selectTaskDetailByTaskNo(TaskVO taskVO) throws SQLException {
		TaskVO result = sqlSession.selectOne("Spoon-Mapper.selectTaskDetailByTaskNo", taskVO);
		
		return result;
	}

	@Override
	public List<TaskVO> selectTaskListByProjectNo(String projNo) throws SQLException {
		List<TaskVO> taskVOList = sqlSession.selectList("Spoon-Mapper.selectTaskListByProjectNo", projNo);
		return taskVOList;
	}


	@Override
	public int selectTaskSeqNext() throws SQLException {
		int seq = sqlSession.selectOne("Spoon-Mapper.selectTaskSeqNext");
		return seq;
	}

	@Override
	public void insertTaskToCollabo(TaskVO taskVO) throws SQLException {
		sqlSession.update("Spoon-Mapper.insertTaskToCollabo", taskVO);
		
	}

}
