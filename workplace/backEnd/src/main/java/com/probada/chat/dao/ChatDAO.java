package com.probada.chat.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatVO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;

public interface ChatDAO {

	public List<String> selectMyProjTitleList(String userId) throws SQLException;
	
	public List<UserVO> selectMyProjUserList(String projTitle) throws SQLException;
	
	public void createChatRoom(ChatVO chatVO) throws SQLException;
	
	String selectChatSeqNext() throws SQLException;

	
	
	
	public String selectProjNoByTitle(String title) throws SQLException;
	
	public List<ChatVO> selectRoomById(String userId) throws SQLException;
	
	public List<String>selectUserByRoomId(String roomTitle) throws SQLException;
	
	String selectRealChatSeqNext() throws SQLException;
	
	public List<String> selectUserInRoom(String roomId) throws SQLException;
	
	
	public ChatVO selectChatRoom(ChatVO chat) throws SQLException;
	
	public List<ChatMessageVO> selectMessage(ChatMessageVO message) throws SQLException;
	
	String selectMessageSeqNext() throws SQLException;
	
	public void insertMessage(ChatMessageVO message) throws SQLException;
	
	public void updateChatRoom(ChatVO chat) throws SQLException;
	
	public void deleteChatRoom(String chatRoomNo) throws SQLException;
	
	
	public ChatVO selectChatByChatRoomNo(String chatRoomNo) throws SQLException;
	
	public List<UserVO> selectUserVOByRoomId(String realRoom) throws SQLException;
	
	
	
	
}
