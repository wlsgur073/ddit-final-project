package com.probada.chat.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.probada.chat.service.ChatService;
import com.probada.chat.vo.ChatMessageVO;
import com.probada.chat.vo.ChatVO;
import com.probada.chat.vo.models;
import com.probada.project.service.ProjectService;
import com.probada.project.vo.ProjectVO;
import com.probada.project.web.ProjectController;
import com.probada.user.service.UserService;
import com.probada.user.vo.UserVO;

@Controller
@RequestMapping("/chat")
public class ChatController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	ChatService chatService;
	

	
	
	@RequestMapping("/test")
	   public String chat() {
	    	
	    	String url = "/web-app/chat/chatTest";
	    	
	        return url;
	    }
	
	


	
	@RequestMapping("/")
	public ModelAndView chatTest(ModelAndView mnv,HttpSession session) throws Exception {
	 	
	 	String url = "/web-app/chat/chat";
	 	UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	
	 	List<ChatVO> chat = new ArrayList<>();
	 	
	 	String userId = userVO.getUserId();
	 	
	 	chat = chatService.getRoomList(userId);
	 	
		mnv.addObject("chat", chat);
		mnv.setViewName(url);
	 	
	     return mnv;
	 }
	

	

	
	@RequestMapping("/getProjectTitle")
	@ResponseBody
	public ResponseEntity<List<String>> getProjectTitle(HttpSession session) throws Exception {
		ResponseEntity<List<String>> entity = null;

		UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	
		String userId = userVO.getUserId();
		
		List<String> projectList= null;

		try {

			projectList = chatService.getMyProjTitleList(userId);
			LOGGER.debug("프로젝트 리스트  {}",projectList);

			entity = new ResponseEntity<List<String>>(projectList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	@RequestMapping("/getProjectUser")
	@ResponseBody
	public ResponseEntity<List<UserVO>> getProjectUser(String selectProj,HttpSession session) throws Exception {
		ResponseEntity<List<UserVO>> entity = null;

		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		LOGGER.debug("[요청받음] => /getProjectUser");
	
		//하드코딩 나는 제외
		String userId = userVO.getUserId();
		
		List<UserVO> userList = new ArrayList<UserVO>();
		
		
		try {
			userList = chatService.getMyProjUserList(selectProj);
			
			
			for (int i = 0; i < userList.size(); i++) {
				
				if(userList.get(i).getUserId().equals(userVO.getUserId())) {
					
					userList.remove(i);
					
				}
				
			}
			
			/*
			 * 오브젝트가 같은 걸 어떻게 비교해 시발
			 */
				
			
			
			/*LOGGER.debug("userList  {}",userList);
			LOGGER.debug("index of text  {}",userList.indexOf(userVO));
			
			
			userList.remove(userList.indexOf(userVO));
		*/
			LOGGER.debug("유저 리스트  {}",userList);

			entity = new ResponseEntity<List<UserVO>>(userList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	
	@RequestMapping(value="/createRoom.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity<String> createRoom(String ptitle, String title,String userId,RedirectAttributes rttr,HttpSession session) throws Exception {
		
		
		ResponseEntity<String> entity = null;
		
		String[] realUserId = userId.split(",");
		
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");				
		String realRoom = chatService.seqRealChat();
		
		
		LOGGER.debug("[요청받음] => /createRoom");
		String seq=null;

		String myId=userVO.getUserId();
		String myNickName = userVO.getNickname();
		
		
		//프로젝트가 중복될 경우 배제한 코드
		String projNo = chatService.getProjNoByTitle(ptitle);
		
		seq = chatService.seqChat();
		
		ChatVO chatVO1 = new ChatVO();
		
		seq = chatService.seqChat();
		chatVO1.setProjNo(projNo);
		chatVO1.setChatroomNo(seq);
		chatVO1.setTitle(title);
		chatVO1.setUserId(myId);
		chatVO1.setRealRoom(realRoom);
		chatService.createChatRoom(chatVO1);
		
		LOGGER.debug("chatvo1 {}",chatVO1);
		
		for(String user : realUserId) {
			ChatVO chatVO = new ChatVO();
			seq = chatService.seqChat();
			chatVO.setProjNo(projNo);
			chatVO.setChatroomNo(seq);
			chatVO.setTitle(title);
			chatVO.setUserId(user);
			chatVO.setRealRoom(realRoom);
			chatService.createChatRoom(chatVO);
			LOGGER.debug(" for문 안에 {}"+chatVO);
		}


		
	/*	test나중에 룸 넘버 빼기 용
	 * ChatVO testChat = new ChatVO();
		
		testChat = chatService.getChatByChatNo(firstSeq);
		
		LOGGER.debug(" firstSeq {}",firstSeq);
		LOGGER.debug(" testChat {}",testChat);*/
	
		
		
		
		try {
			entity = new ResponseEntity<String>(myNickName,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
	
	
	@RequestMapping("/getRoomUser")
	@ResponseBody
	public ResponseEntity<List<String>> getRoomUser(String pTitle,HttpSession session) throws Exception {
		
		ResponseEntity<List<String>> entity = null;
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		
		LOGGER.debug("[요청받음] => /getRoomUser");
	
		//하드코딩 나는 제외
		
		String userId = userVO.getUserId();
		List<String> userList = new ArrayList<String>();
	
		try {

			userList = chatService.getRoomUserList(pTitle);
		
			LOGGER.debug("getRoomUser  {}",userList);

			entity = new ResponseEntity<List<String>>(userList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	@RequestMapping("/chatRoom")
	public ModelAndView chatRoom(ModelAndView mnv,HttpSession session,String roomNo) throws Exception {
	 	
	 	String url = "/web-app/chattest/chatRoom";
	 	LOGGER.debug("roomNo  {}",roomNo);
	 	
	 	List<String> userList = new ArrayList<String>();
	 	List<UserVO> chatUser = new ArrayList<>();
	 	UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	ChatVO chat = new ChatVO();
	 	
	 	
	 	chat.setRealRoom(roomNo);
	 	chat.setUserId(userVO.getUserId());
	 	
	 	//String userId = userVO.getUserId();
	 	
	 	ChatVO realChat = chatService.getRoomByRealRoom(chat);
	 	chatUser = chatService.getUserVOByrealRoomNo(roomNo);	 	


		userList = chatService.getRoomUserList(realChat.getTitle());
	 	//채팅방에 포함하는 유저들도 .addobject해주자
	 	
		
		
		
	 	LOGGER.debug("chatUser  {}",chatUser);
	 	
		mnv.addObject("roomNo", roomNo);
		mnv.addObject("chatUser", chatUser);
		mnv.addObject("userList", userList);
		mnv.addObject("userVO", userVO);
		mnv.addObject("realChat", realChat);
		mnv.setViewName(url);
	 	
	     return mnv;
	 }
	
	
	@RequestMapping("/getMessageByDb")
	@ResponseBody
	public ResponseEntity<List<ChatMessageVO>> getMessage(HttpSession session,String roomNo) throws Exception {
		ResponseEntity<List<ChatMessageVO>> entity = null;
		
		LOGGER.debug("[요청받음] => /getMessageByDb");
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		
		List<ChatMessageVO> message = new ArrayList<>();
		ChatMessageVO messageVO = new ChatMessageVO();
		
		String userId = userVO.getUserId();
		
		messageVO.setRealRoom(roomNo);
		
		message = chatService.getMessage(messageVO);
		LOGGER.debug("db 리스트, {}",message);
		try {
			
			entity = new ResponseEntity<List<ChatMessageVO>>(message,HttpStatus.OK);
			LOGGER.debug("db, {}",entity);
		} catch(Exception e) {
			entity = new ResponseEntity<List<ChatMessageVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	@RequestMapping("/Read")
	@ResponseBody
	public ResponseEntity<List<ChatVO>> getProject(HttpSession session) throws Exception {
		ResponseEntity<List<ChatVO>> entity = null;

		LOGGER.debug("[요청받음] => /getMyChatProject");
		UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	
	
	 	List<ChatVO> chat = new ArrayList<>();
	 	
	 	String userId = userVO.getUserId();
	 	
	 	chat = chatService.getRoomList(userId);

		try {

			LOGGER.debug("프로젝트 리스트, {}",chat);

			entity = new ResponseEntity<List<ChatVO>>(chat,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<ChatVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	@RequestMapping("/Update")
	@ResponseBody
	public Map<String, Object> modifyChatTitle(HttpSession session, 
			@RequestBody Map<String, Object> payload	
			) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		
		String orgin = payload.get("models").toString();
		String realOrigin = orgin.replace("=", ":");
		
		JSONArray ja = new JSONArray(realOrigin);

		JSONObject jo = ja.getJSONObject(0);


		LOGGER.debug("list {}",jo.get("chatroomNo"));
		
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");

		LOGGER.debug("list {}",(String)jo.get("title"));
		LOGGER.debug("list {}",(String)jo.get("realRoom"));
	
	 	ChatVO chat = new ChatVO();
	 	chat.setTitle((String)jo.get("title"));
	 	chat.setRealRoom((String)jo.get("realRoom"));
	 	

	 	chatService.modifyChatRoom(chat);
		
	 	
	 	
	 	
	 	
	 	
	 	return result;
	}
	
	
	@RequestMapping("/Destroy")
	@ResponseBody
	public Map<String, Object> deleteChatRoom(HttpSession session, 
			@RequestBody Map<String, Object> payload	
			) throws Exception {
		Map<String, Object> result = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		LOGGER.debug("[요청받음] => /getMyChatProject");

		
		String orgin = payload.get("models").toString();
		LOGGER.debug("orgin {}",orgin);
		String realOrigin = orgin.replace("=", ":");
		LOGGER.debug("realOrigin {}",realOrigin);
		JSONArray ja = new JSONArray(realOrigin);

		JSONObject jo = ja.getJSONObject(0);

	 	chatService.removeChatRoom((String)jo.get("chatroomNo"));
	 	
	 	return result;
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	
