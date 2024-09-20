package com.probada.chat.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatVO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;

public class ChatDAOImpl implements ChatDAO {


	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	@Override
	public List<String> selectMyProjTitleList(String userId) throws SQLException {
		return  sqlSession.selectList("Chat-Mapper.selectMyProjTitleList",userId);
	}

	@Override
	public List<UserVO> selectMyProjUserList(String projTitle) throws SQLException {
		return  sqlSession.selectList("Chat-Mapper.selectMyProjUserList",projTitle);
	}


	@Override
	public void createChatRoom(ChatVO chatVO) throws SQLException {
		
		sqlSession.insert("Chat-Mapper.createChatRoom",chatVO);
		
	}


	@Override
	public String selectChatSeqNext() throws SQLException {
		
		String seq_num=
				sqlSession.selectOne("Chat-Mapper.selectChatSeqNext");

		return seq_num;
	}


	@Override
	public String selectProjNoByTitle(String title) throws SQLException {
		
		
		return sqlSession.selectOne("Chat-Mapper.selectProjNoByTitle",title);
		
	}


	
	
	@Override
	public List<ChatVO> selectRoomById(String userId) throws SQLException {
		
		
		
		return sqlSession.selectList("Chat-Mapper.selectRoomById",userId);
	}

	@Override
	public List<String> selectUserByRoomId(String roomTitle) throws SQLException {
		
		return sqlSession.selectList("Chat-Mapper.selectUserByRoomId",roomTitle);
		
		
		
	}


	@Override
	public String selectRealChatSeqNext() throws SQLException {
		String seq_num=
				sqlSession.selectOne("Chat-Mapper.selectRealChatSeqNext");
		return seq_num;
	}


	@Override
	public List<String> selectUserInRoom(String roomId) throws SQLException {
		return sqlSession.selectList("Chat-Mapper.selectUserInRoom",roomId);
		
	}


	@Override
	public ChatVO selectChatRoom(ChatVO chat) throws SQLException {
		return sqlSession.selectOne("Chat-Mapper.selectChatRoom",chat);
	}


	@Override
	public List<ChatMessageVO> selectMessage(ChatMessageVO message) throws SQLException {
		return sqlSession.selectList("Chat-Mapper.selectMessage",message);
	}


	@Override
	public String selectMessageSeqNext() throws SQLException {
		String seq_num=
				sqlSession.selectOne("Chat-Mapper.selectMessageSeqNext");
		return seq_num;
	}


	@Override
	public void insertMessage(ChatMessageVO message) throws SQLException {
		sqlSession.insert("Chat-Mapper.insertMessage",message);
		
	}


	@Override
	public void updateChatRoom(ChatVO chat) throws SQLException {
		
		sqlSession.update("Chat-Mapper.updateChatRoom",chat);
		
	}


	@Override
	public void deleteChatRoom(String chatRoomNo) throws SQLException {

		sqlSession.delete("Chat-Mapper.deleteChatRoom",chatRoomNo);
		
	}


	@Override
	public ChatVO selectChatByChatRoomNo(String chatRoomNo) throws SQLException {
		
		return sqlSession.selectOne("Chat-Mapper.selectChatByChatRoomNo",chatRoomNo);
	}


	@Override
	public List<UserVO> selectUserVOByRoomId(String realRoom) throws SQLException {
		return sqlSession.selectList("Chat-Mapper.selectUserVOByRoomId",realRoom);
	}

	

	
	
	
}