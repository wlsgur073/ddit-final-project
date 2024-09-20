package com.probada.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.probada.chat.vo.ChatVO;
import com.probada.user.service.UserService;
import com.probada.user.vo.UserVO;
import com.probada.util.UserUtil;

import org.apache.commons.io.IOUtils;


@RestController
@RequestMapping("/user")
public class UserModifyController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	
	
	
	
	@RequestMapping("/my-page")
	public ModelAndView my_page(ModelAndView mnv, HttpSession session) throws Exception{
		
		String url = "/web-app/my-page/my-page";
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");

		//String userId = userVO.getUserId();
		//하드코딩 세션
	 	UserVO user = new UserVO();
	 	//
	 	//String userVO = "test8@example.com";
	 	//user = userService.getUser(userId);
	 	
		mnv.addObject("user", userVO);
		mnv.setViewName(url);
	 	
	    return mnv;
	}
	
	
	
	@RequestMapping(value="/pictureUpload", method= RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> picture(@RequestParam("picture") MultipartFile multi 
																) throws IOException{
		
		ResponseEntity<String> entity = null;
		String result = savePicture(multi);
		entity = new ResponseEntity<String>(result,HttpStatus.OK);
		
		return entity;
	}
	
	
	
	
	private String savePicture(MultipartFile multi) throws IOException{
		String fileName = null;
		
		/*파일유무 확인*/
		if (!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 500)) {
			/*파일 저장폴더 설정*/
			
			fileName = multi.getOriginalFilename();
			String uploadPath = "c:/member/picture";
			File storeFile = new File(uploadPath, fileName);
			
		
			
			if(storeFile.exists()) { 
				
			}else{
				storeFile.mkdirs();
				multi.transferTo(storeFile);
			}


			
		
			
			
		}
		
		return fileName;
	}
	
	
	
	
	

	@RequestMapping(value="/getPicture", produces="text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPicture(String picture) throws IOException{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String imgPath = "c:/member/picture";
		try {
			in = new FileInputStream(new File(imgPath, picture));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),HttpStatus.CREATED);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping(value="/getPictureById", produces="text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPictureById(String userId) throws IOException{
		String picture = null;
		
		try {
			picture = userService.getUser(userId).getPicture();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getPicture(picture);
	}
	
	@RequestMapping(value="/getPictureByNickname", produces="text/plain;charset=utf-8")
	public ResponseEntity<byte[]> getPictureByNickname(String nickname) throws IOException{
		String picture = null;
		
		try {
			picture = userService.getUserByNickname(nickname).getPicture();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getPicture(picture);
	}
	
	
	@RequestMapping(value="/modify", method= RequestMethod.POST)
	public ResponseEntity<String> modify(UserVO user, HttpSession session) throws Exception{
		
	/*	A201	전체공개
		A202	비공개*/
		
		
		ResponseEntity<String> entity = null;
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");

		user.setUserId(userVO.getUserId());
		user.setAuthStatus(1);
		user.setPwd(null);
		LOGGER.debug("user=>{}",user);

		int pri;

		pri = userService.nicknameCheck(user.getNickname());
		
		LOGGER.debug("pri=>{}",pri);
		
		if(pri==0) {
			entity = new ResponseEntity<String>("no",HttpStatus.BAD_GATEWAY);
			return entity;
			
		}
		

		LOGGER.debug("pri=>{}",pri);
		
		
		 
			userService.modifyUser(user);
			entity =  new ResponseEntity<String>("yes",HttpStatus.OK);
			
			//세션 적용 수정한 회원 세션 적용
			UserVO loginUser = (UserVO) session.getAttribute("userVO");
			if(loginUser != null && loginUser.getUserId().equals(loginUser.getUserId())) {
				
				loginUser = userService.getUser(user.getUserId());
				session.setAttribute("userVO", loginUser);
		    }
		
		return entity;
	}
	
	
		
	
	@RequestMapping(value="/nickNameCheck", method= RequestMethod.POST)
	public ResponseEntity<String> nickNameCheck(UserVO user, HttpSession session) throws Exception{
		
		ResponseEntity<String> entity = null;
		int pri;
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		//하드코딩
		user.setUserId(userVO.getUserId());
		user.setAuthStatus(1);
		user.setPicture(null);
		user.setIntro(null);
		user.setPwd(null);

		LOGGER.debug("user=>{}",user);
	/*	A201	전체공개
		A202	비공개*/
		userService.modifyUser(user);
		
		pri = userService.nicknameCheck(user.getNickname());
	
		
		
		LOGGER.debug("pri=>{}",pri);
		
		if(pri==0) {
			entity = new ResponseEntity<String>("no",HttpStatus.OK);
		}else {
			userService.modifyUser(user);
			entity =  new ResponseEntity<String>("yes",HttpStatus.OK);
	
		}
		
		return entity;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	

	
	@RequestMapping(value="/passwordCheck", method= RequestMethod.POST)
	public ResponseEntity<String> passwordCheck(String passwords,HttpSession session) throws Exception{
		

		//암호화되어 있는 to를 복호화 시킨 후, 같은 String타입의 from과 문자열이 같은지 비교한다.
		//userUtil.comparePwd(passwords, passwords);
		UserVO userVO= (UserVO)session.getAttribute("userVO");
	
		ResponseEntity<String> entity = null;
		String pwd;
		boolean chk;
		//pwd = userService.getPwdByUserId(userVO.getUserId());
		chk = userUtil.comparePwd(passwords, userVO.getUserId());


		LOGGER.debug("chk=>{}",chk);
		
		if(chk) {
			entity =  new ResponseEntity<String>("",HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>("비밀번호 불일치 없음",HttpStatus.BAD_GATEWAY);
		}
		
		
		return entity;
	}
	
	
	
	
	@RequestMapping(value="/modifyPwd", method= RequestMethod.POST)
	public ResponseEntity<String> modifyPwd(String password,HttpSession session) throws Exception{
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		
		ResponseEntity<String> entity = null;
		String newPassWord;
		UserVO user = new UserVO();
		
//		encodePassword = userUtil.encodePwd(password);
		user.setUserId(userVO.getUserId());
//		user.setPwd(encodePassword);
		
		
		newPassWord= userUtil.encodePwd(password, userVO.getUserId());
		
		user.setPwd(newPassWord);
		
		userService.modifyUser(user);
		

		LOGGER.debug("user=>{}",user);

		entity =  new ResponseEntity<String>("",HttpStatus.OK);
	
		return entity;
	}
	
	
	
	@RequestMapping(value="/fire", method= RequestMethod.POST)
	public ResponseEntity<String> fire(String passwords,HttpSession session) throws Exception{
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		ResponseEntity<String> entity = null;
		String pwd;
		boolean chk;

		chk = userUtil.comparePwd(passwords, userVO.getUserId());
		LOGGER.debug("chk=>{}",chk);
		
		UserVO user = new UserVO();
		user.setAuthStatus(2);
		user.setUserId(userVO.getUserId());
		user.setPwd(null);
		
		
		if(chk) {
			entity =  new ResponseEntity<String>("yes",HttpStatus.OK);
			LOGGER.debug("chk11=>{}",chk);
			userService.modifyUser(user);
		}else {
			entity = new ResponseEntity<String>("no",HttpStatus.BAD_GATEWAY);
			LOGGER.debug("entity=>{}",entity);
		}

	
		return entity;
	}
	
	
	
	
	
	
}
