package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.command.CollaboCommand;
import com.probada.collabo.vo.CollaboVO;
import com.probada.collabo.web.CollaboController;
import com.probada.mail.vo.MailVO;
import com.probada.user.vo.UserVO;

public class CollaboDAOImpl implements CollaboDAO {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CollaboDAOImpl.class);
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<CollaboVO> selectCollaboList() throws SQLException {
		
		List<CollaboVO> collaboList = sqlSession.selectList("Collabo-Mapper.selectCollaboList");
		
		return collaboList;
	}

	@Override
	public CollaboVO selectCollaboByCprojNo(String cprojNo) throws SQLException {
		
		LOGGER.debug(cprojNo);
		
		CollaboVO collaboVO = sqlSession.selectOne("Collabo-Mapper.selectCollaboByCprojNo", cprojNo);
		
		return collaboVO;
	}

	@Override
	public void updateCollaboDetail(CollaboVO collaboVO) throws SQLException {
		LOGGER.debug("DAO modify" + collaboVO);
		
		sqlSession.update("Collabo-Mapper.updateCollaboDetail", collaboVO);
	}

	@Override
	public void updateCollaboNotice(CollaboVO collaboVO) throws SQLException {

		sqlSession.update("Collabo-Mapper.updateCollaboNotice", collaboVO);
	}

	@Override
	public List<CollaboVO> selectProjectTitleCollabo(String userId) throws SQLException {
		
		List<CollaboVO> projTitle = sqlSession.selectList("Collabo-Mapper.selectProjectTitleCollabo", userId);
		
		return projTitle;
	}

	@Override
	public void registInviteCollaboMail(CollaboCommand cmd) throws SQLException {
		
		sqlSession.update("Collabo-Mapper.registInviteCollaboMail", cmd);
	}

	@Override
	public int selectCollaboMailSequenceNextValue() throws SQLException {
		
		int mailNo = sqlSession.selectOne("Collabo-Mapper.selectCollaboMailSequenceNextValue");
		return mailNo;
	}

	@Override
	public int collaboCount(String userId) throws SQLException {
		int result =Integer.parseInt(sqlSession.selectOne("Collabo-Mapper.collaboCount", userId));
		System.out.println("다오 임플 result =>" + result );
		return result;
	}

	@Override
	public List<CollaboVO> selectCollaboListByUserId(String userId) throws SQLException {
		
		List<CollaboVO> collaboList = sqlSession.selectList("Collabo-Mapper.selectCollaboListByUserId", userId);
		return collaboList;
	}

	@Override
	public List<CollaboVO> selectCollaboSubProj(String cprojNo) throws SQLException {
		
		List<CollaboVO> collaboList = sqlSession.selectList("Collabo-Mapper.selectCollaboSubProj",cprojNo);
		
		return collaboList;
	}

	@Override
	public int selectCollaboSeqNext() throws SQLException {
		
		int result = sqlSession.selectOne("Collabo-Mapper.selectCollaboSeqNext");
		
		return result;
	}

	@Override
	public void insertCollabo(CollaboVO collaboVO) throws SQLException {
		
		sqlSession.update("Collabo-Mapper.insertCollabo", collaboVO);
		
	}

	@Override
	public void insertCollaboUserRelation(CollaboVO collaboVO) throws SQLException {

		sqlSession.update("Collabo-Mapper.insertCollaboUserRelation", collaboVO);
	}

	@Override
	public String selectCprojectNameByCprojNo(String cprojNo) throws SQLException {

		String title = sqlSession.selectOne("Collabo-Mapper.selectCprojectNameByCprojNo",cprojNo);
		
		return title;
	}

	@Override
	public List<UserVO> selectUserByCprojNo(String cprojNo) throws SQLException {
		
		List<UserVO> collaboList = sqlSession.selectList("Collabo-Mapper.selectUserByCprojNo", cprojNo);
		
		return collaboList;
	}

	@Override
	public List<CollaboVO> selectCprojectNameByProjNo(String projNo) throws SQLException {
		
		List<CollaboVO> collaboList = sqlSession.selectList("Collabo-Mapper.selectCprojectNameByProjNo", projNo);
		
		return collaboList;
	}

	@Override
	public int selectCprojectCountInCprojByUserId(String userId) throws SQLException {
		int result = sqlSession.selectOne("Collabo-Mapper.selectProjectCountInCprojByUserId",userId);

		return result;
	}

	@Override
	public String selectUserRole(CollaboVO collaboVO) throws SQLException {
		String role = sqlSession.selectOne("Collabo-Mapper.selectUserRole",collaboVO);
		return role;
	}

	@Override
	public void updateUserRole(CollaboVO collaboVO) throws SQLException {

		sqlSession.update("Collabo-Mapper.updateUserRole",collaboVO);
	}


}
