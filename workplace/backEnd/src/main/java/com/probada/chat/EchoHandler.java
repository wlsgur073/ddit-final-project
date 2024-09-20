package com.probada.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.probada.chat.service.ChatService;
import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatUserVO;
import com.probada.chat.vo.ChatVO;
import com.probada.user.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class EchoHandler extends TextWebSocketHandler {
	
	@Autowired
	ChatService chatService;

	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);
	//로그인 한 전체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

	private Map<String, ArrayList<WebSocketSession>> RoomList = new HashMap<String, ArrayList<WebSocketSession>>();
	    
	private Map<WebSocketSession, String> sessionList = new HashMap<WebSocketSession, String>();
	
	private Map<String, List<String>> onoffCheckUser = new HashMap<String, List<String>>();
	private Set<String> joinUser = new HashSet<>();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		
		joinUser.add(getId(session));
		
		String jsonData = new Gson().toJson(joinUser);
		
		LOGGER.debug("jsonData =>{}",jsonData);
		
		
		
		
		// Map<String,Object> map = session.getAttributes();
		// UserVO loginUser = (UserVO)map.get("userVO");

		// LOGGER.debug("loginUser =>{}",loginUser);
		
		//sessions.add(session);
		
		//Map<String, String> data = new HashMap<>();
		//data.put("dataType", "login");
		//세션이 없으면 에려
		//data.put("data", loginUser.getNickname()+"님이 접속했습니다.<br/>현재 총"+sessions.size()+"명 접속중.");
		
		//String jsonData = new Gson().toJson(data);
	    //	for(WebSocketSession user :sessions) {
		//	user.sendMessage(new TextMessage(jsonData));
		//}
		
		//LOGGER.debug("dataMap =>{}",data);
	/*	String senderId = getId(session);
		userSessionsMap.put(senderId , session);*/
	}
	
	//소켓에 메세지를 보냈을때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		
		
		
		List<String> temp = new ArrayList<>();
	
		String checkRealRoom ="";
		String checkNickName="";
		String msg = message.getPayload();
		LOGGER.debug(" msg =>{}",msg);
		
		ChatMessageVO chatMessage = objectMapper.readValue(msg,ChatMessageVO.class);
		LOGGER.debug("chatMessage =>{}",chatMessage);
		
		checkRealRoom = chatMessage.getRealRoom();
		checkNickName = chatMessage.getNickname();
		temp.add(checkNickName);
		onoffCheckUser.put(checkRealRoom,temp);
		
		ChatVO commandChat = new ChatVO();
		commandChat.setUserId(chatMessage.getUserId());
		commandChat.setRealRoom(chatMessage.getRealRoom());
		
		LOGGER.debug("commandChat =>{}",commandChat);
		
		ChatVO chatRoom = chatService.getRoomByRealRoom(commandChat);
		
		LOGGER.debug("chatRoom =>{}",chatRoom);
		
		String jsonData = new Gson().toJson(joinUser);
		
		
		if(RoomList.get(chatRoom.getRealRoom()) == null && chatMessage.getContent().equals("ENTER-CHAT") && chatRoom != null) {
            //test
			TextMessage textMessage3 = new TextMessage(chatMessage.getUserId() + ","+chatMessage.getNickname()+","+ chatMessage.getContent()+","+chatMessage.getRegdate()+","+chatMessage.getPicture());
			TextMessage joinUser = new TextMessage(jsonData);
            // 채팅방에 들어갈 session들
            ArrayList<WebSocketSession> sessionTwo = new ArrayList<>();
            // session 추가
            sessionTwo.add(session);
            // sessionList에 추가
            sessionList.put(session, chatRoom.getRealRoom());
            // RoomList에 추가
            RoomList.put(chatRoom.getRealRoom(), sessionTwo);
            //test
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(joinUser);
            }
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(textMessage3);
            }
            //test
            LOGGER.debug("채팅방 생성");
        }
        
        // 채팅방이 존재 할 때
        else if(RoomList.get(chatRoom.getRealRoom()) != null && chatMessage.getContent().equals("ENTER-CHAT") && chatRoom != null) {
        	TextMessage joinUser = new TextMessage(jsonData);
        	
        	
        	
        	
        	TextMessage textMessage1 = new TextMessage(chatMessage.getUserId() + ","+chatMessage.getNickname()+",님이 참가하였습니다.,"+chatMessage.getRegdate()+","+chatMessage.getPicture());
            // RoomList에서 해당 방번호를 가진 방이 있는지 확인.
            RoomList.get(chatRoom.getRealRoom()).add(session);
            // sessionList에 추가
            sessionList.put(session, chatRoom.getRealRoom());
            
            LOGGER.debug("입장 메시지 전송");
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(joinUser);
            }
            
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(textMessage1);
            }
            LOGGER.debug("채팅방 입장");
        }
        
        // 채팅 메세지 입력 시
        else if(RoomList.get(chatRoom.getRealRoom()) != null && !chatMessage.getContent().equals("ENTER-CHAT") && chatRoom != null && !chatMessage.getContent().equals("CLOSE-CHAT")) {
        	TextMessage joinUser = new TextMessage(jsonData);
        	
        	LOGGER.debug("채팅 매시지 입력 메서드 실행");
        	
            // 메세지에 이름, 내용을 담는다.
            TextMessage textMessage = new TextMessage(chatMessage.getUserId() + ","+chatMessage.getNickname()+","+ chatMessage.getContent()+","+chatMessage.getRegdate()+","+chatMessage.getPicture());
            
            
            // 현재 session 수
            int sessionCount = 0;
 
            // 해당 채팅방의 session에 뿌려준다.
            
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(joinUser);
            }
            
            
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(textMessage);
                sessionCount++;
            }
            
 
            chatMessage.setChatroomMsgNo(chatService.seqMessage());
            
            LOGGER.debug("chatMessage=>{}",chatMessage);
            chatService.createMessage(chatMessage);
            
        } else if(RoomList.get(chatRoom.getRealRoom()) != null && chatRoom != null && chatMessage.getContent().equals("CLOSE-CHAT")  ) {
        	
        	
        		LOGGER.debug("CLOSE 메서드 실행");
        	
            // 메세지에 이름, 내용을 담는다.
            TextMessage textMessage4 =new TextMessage(chatMessage.getUserId() + ","+chatMessage.getNickname()+","+ chatMessage.getContent()+","+chatMessage.getRegdate()+","+chatMessage.getPicture());
        	
            for(WebSocketSession sess : RoomList.get(chatRoom.getRealRoom())) {
                sess.sendMessage(textMessage4);
            }
        	
        }
		
		
		
		
		
		/*	
		for (WebSocketSession sess : sessions) {
			sess.sendMessage(new TextMessage( getId(session) +" : " +  message.getPayload()));
			
		}*/
		
		

		
	}
	
	//연결 해제될때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		
		
		joinUser.remove(getId(session));
		
		LOGGER.debug("지워진느지 확인 =>{}",joinUser);
		
		  if(sessionList.get(session) != null) {
			  
	            RoomList.get(sessionList.get(session)).remove(session);
	            sessionList.remove(session);
	        }
		
		
	}
	
	
	
	
	
	//웹소켓 id 가져오기
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO)httpSession.get("userVO");
		
		
		LOGGER.debug("loginUser =>{}",loginUser);
		
		if(loginUser == null) {
			return session.getId();
		} else {
			return loginUser.getNickname();
		}
	}
}








/*chatNo="3";
//방 이름하고, 닉네임이 넘어워야하는데?
for (WebSocketSession s : sessions) {
	 Map<String, List<WebSocketSession>> temp = null;
	 List<WebSocketSession> ListTemp = null;
	 
	 Map<String,Object> map = session.getAttributes();
	 UserVO loginUser = (UserVO)map.get("userVO");
	//sql문 실행 룸 넘버
	//getChatNo(loginUser.getUserId());
	 String roomNo = "3";
	 
	 if(chatNo.equals(roomNo)) {
		 
		 ListTemp.add(s);
		 temp.put(roomNo, ListTemp);
		 
	 }
	 chat.add(temp);
}
	
for (WebSocketSession s : sessions) {
	
	 Map<String, List<WebSocketSession>> temp = null;
	 List<WebSocketSession> ListTemp = null;
	 Map<String,Object> map = session.getAttributes();
	 UserVO loginUser = (UserVO)map.get("userVO");
	
	//sql문 실행 룸 넘버
	//getChatNo(loginUser.getUserId());
	 
	 String roomNo = "3";
	 
	 if(chatNo.equals(roomNo)) {
		 ListTemp.add(s);
		 temp.put(roomNo, ListTemp);
	 }
	 chat.add(temp);
	}
//if(chat.get(index))
String msg = message.getPayload();

for (WebSocketSession sess : sessions) {
	sess.sendMessage(new TextMessage( getId(session) +" : " +  message.getPayload()));
}
*/


