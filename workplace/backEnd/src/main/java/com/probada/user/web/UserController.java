package com.probada.user.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.probada.collabo.vo.CollaboVO;
import com.probada.project.vo.ProjectTagVO;
import com.probada.project.vo.ProjectVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.service.UserService;
import com.probada.user.vo.EmailVO;
import com.probada.user.vo.UserTotalCountVO;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.ProjectUtil;
import com.probada.util.TaskUtil;
import com.probada.util.UserUtil;


@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserUtil userUtil;
	@Resource(name="taskUtil")
	private TaskUtil taskUtil;
	@Resource(name="projectUtil")
	private ProjectUtil projectUtil;
	@Resource(name="collaboUtil")
	private CollaboUtil collaboUtil;
	
	public UserController() {
		LOGGER.debug("userController 생성됨!");
	}

	/**
	 * @return 로그인 화면 이동
	 */
	@RequestMapping(value = "home/login", method = RequestMethod.GET)
	public String getLogin() {
		return "/web-index/common/login";
	}

	/**
	 * 로그인 시도시,
	 * 1. DB에서 계정 존재 여부와 비밀번호 일치 검사를 한다.
	 * 2. 해당 계정의 이메일 인증 확인을 검사한다.
	 * 3. 해당 계정의 플랜 이용 만료일과 현재 날짜를 비교 검사한다.
	 * 로그인 접속에 문제가 없다면, UserVO와 데이터 사용량을 Map으로 리턴한다.
	 * @param req
	 * @return 로그인 결과를 Map타입으로 login_registration.js의 ajax에 리턴
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping(value = "home/login.do", method = RequestMethod.POST)
	public Map<String, String> postLogin(HttpServletRequest req) throws SQLException{

		HttpSession session = req.getSession();			// 세션 등록
		Map<String, String> retMap = new HashMap<>();	// ajax로 보내는 리턴 값
		UserVO user = new UserVO();

//		id와 password 저장
		String inputId = req.getParameter("input_email");
		String inputPwd = req.getParameter("input_pwd");
		user.setUserId(inputId);

		user = userService.login(user);	// 계정 리스트에서 입력한 계정 확인 후 user에 저장
		boolean flag_pwd = userUtil.comparePwd(inputPwd, user.getUserId());	// 복호화 후 비교

// 		비밀번호가 틀리거나, 계정이 없는 경우
		if(!flag_pwd || user.getUserId().equals("")) {
			retMap.put("login_fail", "login_fail");
			return retMap;
		}
// 		인증이 완료되면 무료 이용권 자동 시작
		else if (user.getAuthStatus() == 0) {
			retMap.put("authStatus", "fail");
			return retMap;
		}
//		사용기한이 만료되었을 경우
		else if(!userUtil.compareExpireDate(user.getUserId())){
			retMap.put("expired", "expired");
			return retMap;
		}
		else {
			retMap.put("success", "success");
		}
		
		session.setAttribute("userVO", user);
		return retMap;
	}

	/**
	 * 회원가입 페이지 이동
	 * @return 회원가입 페이지 url 리턴
	 * @throws Exception
	 */
	@RequestMapping(value = "home/register", method = RequestMethod.GET)
	public String getRegister() throws Exception {
		return "/web-index/common/register";
	}

	/**
	 * 1. 아이디 중복 검사를 한다.
	 * 2. 닉네임 중복 검사를 한다.
	 * 3. 입력한 비밀번호를 암호화한다.
	 * 문제가 없다면, 해당 이메일로 인증폼을 보낸다.
	 * @param UserVO
	 * @return 회원가입 처리한 값(Map)을 login_registration.js의 ajax로 리턴
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "home/register.do", method = RequestMethod.POST)
	public Map<String, String> postRegister(UserVO user) throws Exception {

		// 이메일 중복여부 확인 (사용가능하면 1)
		int idCheck = userService.idCheck(user.getUserId());
		// 닉네임 중복여부 확인 (사용가능하면 1)
		int nicknameCheck = userService.nicknameCheck(user.getNickname());
		// Map으로 ajax에 데이터 다시 보내기
		Map<String, String> jsonMap = new HashMap<>();

		// 닉네임 또는 이메일 중복일 때
		if (idCheck == 0) { jsonMap.put("idCheck", "false"); return jsonMap; }
		if (nicknameCheck == 0) { jsonMap.put("nicknameCheck", "false"); return jsonMap; }

		// 암호 복호화
		String storeToDB = userUtil.encodePwd(user.getPwd(), user.getUserId());
		user.setPwd(storeToDB);

		// register 회원가입 dao
		userService.registUser(user);

		return jsonMap;
	}


	/**
	 * 1. updateAuthstatus()에서 회원 인증 상태를 0에서 1로 변경한다.
	 * 2. 인증일 기준으로 무료 플랜에 30일간 가입시킨다.
	 * 문제가 없다면 로그인 페이지로 이동한다.
	 * @param String userId
	 * @param model
	 * @return 로그인 페이지 리턴
	 * @throws Exception
	 */
	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(String userId) throws Exception {
		// authstatus 권한 상태 1로 변경
		userService.updateAuthstatus(userId);

		userUtil.init_free(userId);

		return "redirect:/home/login?welcome=true";
	}


	/**
	 * 로그아웃 버튼 클릭 시, 모든 세션을 삭제하고 로그인 페이지로 이동시킨다.
	 * @param req
	 * @return 로그인 페이지 리턴
	 */
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/home/login";
	}

	@RequestMapping(value = "/getUserByProjNo", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> getUserByProjNo(String projNo) throws Exception {
		ResponseEntity<List<UserVO>> entity = null;


		List<UserVO> userListForProjDetail = new ArrayList<UserVO>();

		try {

			userListForProjDetail = userService.getUserByProjNo(projNo);

			userListForProjDetail = taskUtil.getTaskCountUtil(userListForProjDetail);
			userListForProjDetail = projectUtil.getProjectCountUtil(userListForProjDetail);

			entity = new ResponseEntity<List<UserVO>>(userListForProjDetail,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return entity;
	}
	
	/**
	 * 콜라보 프로젝트 유저 리스트
	 * @param cprojNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserByCprojNo", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> getUserByCprojNo(String cprojNo) throws Exception{
		ResponseEntity<List<UserVO>> entity = null;
		
		LOGGER.debug("[요청받음] => /getUserByCprojNo");
		
		List<UserVO> userListForCprojDetail = new ArrayList<UserVO>();
		
		try {
			userListForCprojDetail = userService.getUserByCprojNo(cprojNo);
			userListForCprojDetail = collaboUtil.getTaskCountUtil(userListForCprojDetail);
			
			entity = new ResponseEntity<List<UserVO>>(userListForCprojDetail,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
	
	@RequestMapping(value="home/send_pwdReset", method = RequestMethod.GET)
	public String password_reset() {
		return "/web-index/common/send_pwdReset";
	}
	
	/**
	 * 이메일로 전송할 폼을 생성한다.
	 * @param String userId
	 * @return /web-index/common/success_send_pwdReset
	 */
	@RequestMapping(value="home/send_pwdReset.do", method = RequestMethod.POST)
	public String success_send_pwdReset(String userId) {
		
		EmailVO emailVO = new EmailVO();
		emailVO.setUserId(userId);
		emailVO.setSubject("비밀번호를 재설정합니다.");
		emailVO.setContent("<h1>회원님의 이메일을 제설정합니다.</h1>");
		emailVO.setHostname("home/password_reset");
		
		userUtil.sendEmail(emailVO);
		
		return "/web-index/common/success_send_pwdReset";
	}
	
	/**
	 * 유저 아이디와 인증키를 받아서 password_reset.jsp로 전달한다.
	 * 인증키가 유효하지 않으면 Exception이 발생한다.
	 * @param String userId
	 * @param String authkey
	 * @return ModelAndView mav
	 * @throws Exception
	 */
	@RequestMapping(value = "home/password_reset", method = RequestMethod.GET)
	public ModelAndView verifyPasswordReset(String userId, String authkey) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		String userAutkeyFromDB = userUtil.selectAuthkey(userId);
		mav.addObject("userId", userId);
		
		if(userAutkeyFromDB.equals(authkey)) {
			mav.setViewName("/web-index/common/password_reset");
		} else {
			LOGGER.debug("DB에서 가져온 유저의 인증키와 URL파라미터로 전달된 인증키의 값이 일치하지 않습니다.");
			throw new Exception("에러가 발생했습니다.");
			// 나중에 404 처리해야 됨.
		}
		
		return mav;
	}

	/**
	 * 비밀번호 암호화하여 재설정한다.
	 * @param String pwd
	 * @param String userId
	 * @return ModelAndView mav
	 */
	@RequestMapping(value = "home/password_reset.do", method = RequestMethod.POST)
	public ModelAndView passwordReset(String pwd, String userId){

		ModelAndView mav = new ModelAndView();
		
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);
		
		String encriptPwd = userUtil.encodePwd(pwd, userId);
		userVO.setPwd(encriptPwd);

		try {
			userService.setUserPwd(userVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("successReset", "true");
		mav.setViewName("/web-index/common/login");

		return mav;
	}

	@RequestMapping(value="externalLogin.do", method = RequestMethod.POST)
	public void externalLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		UserVO userVO = new UserVO();
		userVO.setUserId(req.getParameter("memEmail"));
		userVO.setNickname(req.getParameter("memNm"));
		userVO.setPicture(req.getParameter("memImgUrl"));
		
//		해당 아이디로 가입한 계정이 있는지 DB에서 확인
//		있다면 DB에서 해당 계정 불러와서 userVO에 초기화, 없다면 회원가입시킨다.
		try {
			if(userService.isAccount(userVO.getUserId()) <= 0) {
				userUtil.registExternalLogin(userVO);
			} else {
				userVO = userService.getUser(userVO.getUserId());
			}
			
			int userMaxUploadCapacity = userUtil.getUserMaxUploadCapacity(userVO.getUserId());
			
			session.setAttribute("userMaxUploadCapacity", userMaxUploadCapacity);
			session.setAttribute("userVO", userVO);
			
//			세션에 등록 후, 로그인 성공처리
			resp.setContentType("application/json");
			resp.getWriter().print("{\"flag\": \"true\"}");

		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserTotalCount.do", method = RequestMethod.GET)
	public UserTotalCountVO getUserTotalCount(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserTotalCountVO vo = new UserTotalCountVO();
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		
		vo.setUserId(userVO.getUserId());
		vo = userUtil.getUserTotalCount(vo);
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/app/getListToAsideBar.do", method = RequestMethod.POST)
	public Map<String, Object> getListToAsideBar(HttpSession session) {
		
		Map<String, Object> retMap = new HashMap<>();
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		
		List<TaskVO> taskList = new ArrayList<>();
		List<ProjectVO> projectList = new ArrayList<>();
		List<CollaboVO> collaboList = new ArrayList<>();
		
		try {
			taskList = taskUtil.getFormatTaskListByUserId(userVO.getUserId());
			projectList = userUtil.getUserProjectList(userVO.getUserId());
			collaboList = userUtil.getUserCollaboList(userVO.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		retMap.put("taskList", taskList);
		retMap.put("projectList", projectList);
		retMap.put("collaboList", collaboList);
		
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserMaxUploadCapacity.do", method = RequestMethod.POST)
	public Map<String, Object> getUserDataUsage(HttpSession session) {
		Map<String, Object> retMap = new HashMap<>();
		
		int maxDataUsage = 0;
		
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		
		maxDataUsage = userUtil.getUserMaxUploadCapacity(userVO.getUserId());
		
		retMap.put("userVO", userVO);
		retMap.put("userMaxUploadCapacity", maxDataUsage);
		
		return retMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecentTaskList.do", method = RequestMethod.POST)
	public List<TaskVO> getRecentTaskList(HttpSession session){
		List<TaskVO> recentTaskList = new ArrayList<>();
		
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		
		try {	
			recentTaskList = taskUtil.getFormatTaskListByUserId(userVO.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		시간별 내림차순 정렬
		Collections.sort(recentTaskList, new Comparator<TaskVO>() {
			@Override
			public int compare(TaskVO a1, TaskVO a2) {
				return a1.getUpdatedate().compareTo(a2.getUpdatedate());
			}
		});
		
		return recentTaskList;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/toMemberAchievementChart.do", method = RequestMethod.POST)
	public List<UserVO> toMemberAchievementChart(@RequestParam("projNo") String projNo) {
		
		List<UserVO> userList = new ArrayList<>();
		
		try {
			userList = userService.getMemberAchievementList(projNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/toCMemberAchievementChart.do", method = RequestMethod.POST)
	public List<UserVO> toCprojMemberAchievementChart(@RequestParam("cprojNo") String cprojNo) {
		
		LOGGER.debug("============================================>" + cprojNo);
		List<UserVO> userList = new ArrayList<>();
		
		try {
			LOGGER.debug("===>" + cprojNo);
			userList = userService.getCMemberAchievementList(cprojNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getFormatTaskListByUserId.do", method = RequestMethod.POST)
	public List<TaskVO> getFormatTaskListByUserId(HttpSession session, @RequestParam("userId") String userId) {
		
		List<TaskVO> taskList = new ArrayList<>();
		
		try {
			taskList = taskUtil.getFormatTaskListByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return taskList;
	}
	
	@RequestMapping("/user/profile")
	public ModelAndView goToUserProfilePage(@RequestParam("userId") String userId ,ModelAndView mv) {
		
		UserVO userProfileVO = new UserVO();
		
		try {
			userProfileVO = userService.getUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("userProfileVO", userProfileVO);
		mv.setViewName("/web-app/common/profile");
		
		return mv;
	}
	
	@RequestMapping("/app/tag/read")
	@ResponseBody
	public ResponseEntity<List<ProjectTagVO>> getProjectListByTagNo(@RequestParam("tagNo") String tagNo, ModelAndView mv) throws Exception {
		ResponseEntity<List<ProjectTagVO>> entity = null;
		
		LOGGER.debug("[요청받음] => /getProjectListByTagNo");
		
		List<ProjectTagVO> projectTagList = new ArrayList<ProjectTagVO>();
		
		try {
			projectTagList = projectUtil.getProjectListByTagNo(tagNo);
			
			entity = new ResponseEntity<List<ProjectTagVO>>(projectTagList,HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<List<ProjectTagVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
//	@RequestMapping("/user/profile")
//	public String printUserProfile() {
//		String url = "/web-app/common/profile";
//		return url;
//	}
//	
	
}