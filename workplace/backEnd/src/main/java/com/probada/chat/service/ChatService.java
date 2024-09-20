package com.probada.chat.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatVO;
import com.probada.user.vo.UserVO;

public interface ChatService {

	
	public List<String> getMyProjTitleList(String userId) throws SQLException;
	public List<UserVO> getMyProjUserList(String projTitle) throws SQLException;
	
	public void createChatRoom(ChatVO chatVO) throws SQLException;
	
	public String seqChat() throws SQLException;

	public String getProjNoByTitle(String title) throws SQLException;
	
	
	public List<ChatVO> getRoomList(String userId) throws SQLException;
	
	public List<String> getRoomUserList(String chatTitle) throws SQLException;
	
	
	public String seqRealChat() throws SQLException;
	
	public List<String> getUserInRoom(String roomId) throws SQLException;	
	
	
	public ChatVO getRoomByRealRoom(ChatVO chat) throws SQLException;
	
	
	public String seqMessage() throws SQLException;
	
	public List<ChatMessageVO> getMessage(ChatMessageVO message) throws SQLException;
	
	public void createMessage(ChatMessageVO message) throws SQLException;

	public void modifyChatRoom(ChatVO chat) throws SQLException;
	
	public void removeChatRoom(String chatRoomNo) throws SQLException;
	
	
	public ChatVO getChatByChatNo(String chatRoomNo) throws SQLException;
		
	
	
	public List<UserVO> getUserVOByrealRoomNo(String realRoom) throws SQLException;
	
	
	
}