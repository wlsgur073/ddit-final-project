package com.probada.collabo.dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.user.vo.UserVO;

public class CollaboTaskDAOImpl implements CollaboTaskDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	public List<CollaboTaskVO> selectTaskListByCprojNo(String cprojNo) throws SQLException {
		
		List<CollaboTaskVO> taskVOList = sqlSession.selectList("CollaboTask-Mapper.selectTaskListByCprojNo", cprojNo);
		
		return taskVOList;
	}


	@Override
	public void updateTaskStatus(CollaboTaskVO collaboTaskVO) throws SQLException {

		sqlSession.update("CollaboTask-Mapper.updateTaskStatus", collaboTaskVO);
	}


	@Override
	public void insertTask(CollaboTaskVO collaboTaskVO) throws SQLException {
		sqlSession.update("CollaboTask-Mapper.insertTask", collaboTaskVO);
	}


	@Override
	public int selectTaskSeqNext() throws SQLException {
		int seq = sqlSession.selectOne("CollaboTask-Mapper.selectTaskSeqNext");

		return seq;
	}


	@Override
	public CollaboTaskVO selectTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {
		
		CollaboTaskVO result = sqlSession.selectOne("CollaboTask-Mapper.selectTaskDetailByTaskNo", collaboTaskVO);
		
		return result;
	}


	@Override
	public void updateTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {

		sqlSession.update("CollaboTask-Mapper.updateTaskDetailByTaskNo", collaboTaskVO);
	}


	@Override
	public void deleteTaskByTaskNo(CollaboTaskVO collaboTaskVO) throws SQLException {

		sqlSession.delete("CollaboTask-Mapper.deleteTaskByTaskNo",collaboTaskVO);
	}


	@Override
	public int selectTaskCountInCprojByUserId(UserVO userVO) throws SQLException {
		
		int result = sqlSession.selectOne("CollaboTask-Mapper.selectTaskCountInCprojByUserId", userVO);
		
		return result;
	}

}
