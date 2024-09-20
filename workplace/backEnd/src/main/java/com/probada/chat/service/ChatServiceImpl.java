package com.probada.chat.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.chat.dao.ChatDAO;
import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatVO;
import com.probada.project.dao.ProjectDAO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;

@Service
public class ChatServiceImpl implements ChatService {

	private ChatDAO chatDAO;
	public void setChatDAO(ChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}
	

	
	
	@Override
	public List<String> getMyProjTitleList(String userId) throws SQLException {
		
		List<String> list = new ArrayList<>();
		
		list = chatDAO.selectMyProjTitleList(userId);
		
		
		return list;
	}
	
	@Override
	public List<UserVO> getMyProjUserList(String projTitle) throws SQLException {
		
		List<UserVO> list = new ArrayList<>();
		
		list = chatDAO.selectMyProjUserList(projTitle);
		
		
		return list;
				
				
	}




	@Override
	public void createChatRoom(ChatVO chatVO) throws SQLException {

			
		chatDAO.createChatRoom(chatVO);
		
	}




	@Override
	public String seqChat() throws SQLException {

		String seq = chatDAO.selectChatSeqNext();

		return seq;
	}




	@Override
	public String getProjNoByTitle(String title) throws SQLException {
		
			
			
			String projNo = chatDAO.selectProjNoByTitle(title);
			
			
			return projNo;
		
	}




	@Override
	public List<ChatVO> getRoomList(String userId) throws SQLException {
		
		
		
		List<ChatVO> chatVO = new ArrayList<>();
		chatVO = chatDAO.selectRoomById(userId);
		return chatVO;
		
	}




	@Override
	public List<String> getRoomUserList(String chatTitle) throws SQLException {
		
		
		List<String> list = new ArrayList<>();
		list = chatDAO.selectUserByRoomId(chatTitle);
		return list;
	}




	@Override
	public String seqRealChat() throws SQLException {
		
		String seq = chatDAO.selectRealChatSeqNext();
		return seq;
	}




	@Override
	public List<String> getUserInRoom(String roomId) throws SQLException {
		
		
		return chatDAO.selectUserInRoom(roomId);
	}




	@Override
	public ChatVO getRoomByRealRoom(ChatVO chat) throws SQLException {
		
		return chatDAO.selectChatRoom(chat);
	}




	@Override
	public String seqMessage() throws SQLException {
		
		String seq = chatDAO.selectMessageSeqNext();
		return seq;
	}




	@Override
	public List<ChatMessageVO> getMessage(ChatMessageVO message) throws SQLException {
		
		
		List<ChatMessageVO> messageVO = new ArrayList<>();
		messageVO = chatDAO.selectMessage(message);
		return messageVO;
	}




	@Override
	public void createMessage(ChatMessageVO message) throws SQLException {
		
		
		chatDAO.insertMessage(message);
	}




	@Override
	public void modifyChatRoom(ChatVO chat) throws SQLException {
		chatDAO.updateChatRoom(chat);
		
	}




	@Override
	public void removeChatRoom(String chatRoomNo) throws SQLException {
		chatDAO.deleteChatRoom(chatRoomNo);
	}




	@Override
	public ChatVO getChatByChatNo(String chatRoomNo) throws SQLException {
		
		ChatVO chatVO = new ChatVO();
		chatVO = chatDAO.selectChatByChatRoomNo(chatRoomNo);
		
		return chatVO;
	}




	@Override
	public List<UserVO> getUserVOByrealRoomNo(String realRoom) throws SQLException {
		
		List<UserVO> user = new ArrayList<>();
		
		user = chatDAO.selectUserVOByRoomId(realRoom);
		
		return user;
	}






	

	
	
	
	
}
