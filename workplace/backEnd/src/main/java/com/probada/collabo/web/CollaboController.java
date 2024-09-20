package com.probada.collabo.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.probada.collabo.command.CollaboCommand;
import com.probada.collabo.service.CollaboService;
import com.probada.collabo.vo.CollaboVO;
import com.probada.document.vo.FileVO;
import com.probada.mail.service.MailService;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.DocumentUtil;

@Controller
@RequestMapping("/app/collabo")
public class CollaboController {

	@Resource(name="collaboService")
	CollaboService collaboService;
	@Resource(name="collaboUtil")
	CollaboUtil collaboUtil;
	@Resource(name="documentUtil")
	DocumentUtil documentUtil;
	@Resource(name="mailService")
	MailService mailService;

	private final Logger LOGGER = LoggerFactory.getLogger(CollaboController.class);

	@RequestMapping("/main")
	public String index() {
		String url ="/web-app/collabo/collabo";
		return url;
	}

	/**
	 * 콜라보 프로젝트 리스트 출력
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/getCollaboList")
	@ResponseBody
	public ResponseEntity<List<CollaboVO>> getCollaboList() throws SQLException{
		ResponseEntity<List<CollaboVO>> entity = null;

		LOGGER.debug("[요청받음] => /getCollaboList ");

		List<CollaboVO> collaboList = new ArrayList<CollaboVO>();

		try {
			collaboList = collaboService.getCollaboList();
			collaboList = collaboUtil.getCollaboMemberList(collaboList);
			LOGGER.debug("[collaboList] => /collaboList " + collaboList.get(0).getMember());
			entity = new ResponseEntity<List<CollaboVO>>(collaboList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getCollaboList 시 에러가 발생했습니다.",e);
		}
		return entity;
	}

	/**
	 * 로그인한 user의 콜라보 리스트 출력
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCollaboListByUserId")
	@ResponseBody
	public ResponseEntity<List<CollaboVO>> getCollaboListByUserId(HttpServletRequest request)throws Exception{
		ResponseEntity<List<CollaboVO>> entity = null;

		LOGGER.debug("[요청받음] => /getCollaboListByUserId ");

		List<CollaboVO> collaboList = new ArrayList<CollaboVO>();
		HttpSession session = request.getSession();

		try {
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String userId = userVO.getUserId();

			collaboList = collaboService.getCollaboListByUserId(userId);
			collaboList = collaboUtil.getCollaboMemberList(collaboList);
			LOGGER.debug("[collaboList] => /collaboList " + collaboList.get(0).getMember());
			//태그 추가

			entity = new ResponseEntity<List<CollaboVO>>(collaboList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getCollaboListByUserId 시 에러가 발생했습니다.",e);
		}

		return entity;

	}


	@RequestMapping("/getCollaboByCprojNo")
	@ResponseBody
	public ResponseEntity<CollaboVO> getCollaboByCprojNo(@RequestParam(defaultValue="") String cprojNo)throws Exception{

		ResponseEntity<CollaboVO> entity = null;

		LOGGER.debug("[요청받음] => /getCollaboByCprojNo ");

		CollaboVO collaboVO = new CollaboVO();

		try {
			collaboVO = collaboService.getCollaboByCprojNo(cprojNo);
			//태그 추가 할 것
			entity = new ResponseEntity<CollaboVO>(collaboVO, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getCollaboByCprojNo 시 에러가 발생했습니다.",e);
		}
		return entity;
	}

	/**
	 * 콜라보 상세에서 하위 프로젝트 리스트 출력
	 * @param cprojNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCollaboSubProj")
	@ResponseBody
	public ResponseEntity<List<CollaboVO>> getCollaboSubProj(@RequestParam(defaultValue="")String cprojNo, HttpServletRequest request)throws Exception{
		ResponseEntity<List<CollaboVO>> entity = null;

		LOGGER.debug("[요청받음] => /getCollaboSubProj ");

		List<CollaboVO> collaboList = new ArrayList<CollaboVO>();

		try {
			collaboList = collaboService.getCollaboSubProj(cprojNo);
			LOGGER.debug("[collaboVO] => " + collaboList.toString());
			entity = new ResponseEntity<List<CollaboVO>>(collaboList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getCollaboSubProj 시 에러가 발생했습니다.",e);
		}

		return entity;
	}

	/**
	 * 콜라보 프로젝트 등록
	 * @param request
	 * @param collaboVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registCollabo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registCollabo(HttpServletRequest request, CollaboVO collaboVO)throws Exception{

		ResponseEntity<String> entity = null;
		LOGGER.debug("[요청받음] => /registCollabo ");
		LOGGER.debug("[요청받음] => data=========================== {}",collaboVO);

		try {
			String userFrom = getUserIdByNickname(collaboVO.getUserFrom());
			collaboVO.setUserFrom(userFrom);

			String cprojNo = collaboService.registCollabo(collaboVO);
			LOGGER.debug("[요청받음] => /registCollabo " + cprojNo);
			collaboUtil.setCollaboUserRelation(collaboVO, cprojNo, request);


			LOGGER.debug("[요청받음] => /registCollabo 완료 ===================");
			entity = new ResponseEntity<String>(cprojNo, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error("/registCollabo 시 에러가 발생했습니다.",e);
		}

		return entity;

	}


	/**
	 * 콜라보 프로젝트 개요부분 수정
	 * @param collaboVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyCollaboDetail",method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modifyCollaboDetail(CollaboVO collaboVO) throws Exception{

		ResponseEntity<HashMap<String, Object>> entity = null;
		LOGGER.debug("[요청받음] => /modifyCollaboDetail ");
		LOGGER.debug("Controller modify" + collaboVO);
		try {
			collaboService.modifyCollaboDetail(collaboVO);
			entity = new ResponseEntity<HashMap<String,Object>>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/modifyCollaboDetail 시 에러가 발생했습니다.",e);
		}
		return entity;

	}

	/**
	 * 콜라보 프로젝트 공지 사항 수정
	 * @param collaboVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/modifyCollaboNotice")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modifyCollaboNotice(CollaboVO collaboVO)throws Exception{

		ResponseEntity<HashMap<String, Object>> entity = null;

		try {
			collaboService.modifyCollaboNotice(collaboVO);
			entity = new ResponseEntity<HashMap<String,Object>>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/modifyCollaboNotice 시 에러가 발생했습니다.",e);
		}
		return entity;
	}

	/***
	 * 콜라보 제안시 로그인한 유저의 프로젝트 리스트 출력
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getProjectTitleCollabo")
	public ResponseEntity<List<CollaboVO>> getProjectTitle(HttpSession session, String userName)throws Exception{
		ResponseEntity<List<CollaboVO>> entity = null;

		LOGGER.debug("[요청받음] => /getProjectTitleCollabo");

		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String userId = userVO.getUserId();
		List<CollaboVO> projTitle = new ArrayList<CollaboVO>();

		try {
			projTitle = collaboService.getProjectTitleCollabo(userId);
			LOGGER.debug("[프로젝트 제목] projTitle=> " + projTitle);
			entity = new ResponseEntity<List<CollaboVO>>(projTitle,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getProjectTitleCollabo 시 에러가 발생했습니다.",e);
		}
		return entity;
	}
	/***
	 * 콜라보 제안시 상대 유저의 프로젝트 리스트 출력
	 * @param userVO
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getProjectTitleOther",method = RequestMethod.POST)
	public ResponseEntity<List<CollaboVO>> getProjectTitleOther(UserVO userVO, HttpServletRequest request)throws Exception{

		ResponseEntity<List<CollaboVO>> entity = null;

		LOGGER.debug("[요청받음] => /getProjectTitleOther");

		List<CollaboVO> projTitle = new ArrayList<CollaboVO>();

		try {
			String userId = getUserIdByNickname(userVO.getNickname());
			projTitle = collaboService.getProjectTitleCollabo(userId);
			LOGGER.debug("[프로젝트 제목2] projTitle=> " + projTitle);
			entity = new ResponseEntity<List<CollaboVO>>(projTitle,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getProjectTitleOther 시 에러가 발생했습니다.",e);
		}
		return entity;
	}

	/**
	 * 콜라보 프로젝트 제안 메일보내는 것
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendInviteCollaboMail", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String sendInviteCollaboMail(CollaboCommand cmd)throws Exception{

		String userId = getUserIdByNickname(cmd.getUserTo());
		cmd.setUserTo(userId);

		String url = "redirect:/app/index";

		LOGGER.debug("[요청받음] => /sendInviteCollaboMail");

		System.out.println("콘텐트 : " + cmd.getContent());

		collaboService.sendInviteCollaboMail(cmd);

		return url;

	}

	/**
	 * 콜라보 프로젝트 보유 유무 판단
	 * @param request
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value = "/getCollaboCount", method = RequestMethod.POST)
	@RequestMapping("/getCollaboCount")
	@ResponseBody
	public int getCollaboCount(HttpServletRequest request)throws Exception{

		LOGGER.debug("[요청받음] => /getCollaboCount");

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userVO");

		String userId = userVO.getUserId();

		int result = collaboService.getCollaboCount(userId);

		return result;
	}

	@RequestMapping("/getUserRole")
	@ResponseBody
	public ResponseEntity<String> getUserRole(String cprojNo, HttpServletRequest request) throws Exception{
		ResponseEntity<String> entity = null;

		LOGGER.debug("[요청받음] => /getUserRole + cprojNo => " + cprojNo);

		try {
			CollaboVO collaboVO = new CollaboVO();

			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			collaboVO.setUserId(userVO.getUserId());
			collaboVO.setCprojNo(cprojNo);

			String role = collaboService.getUserRole(collaboVO);

			entity = new ResponseEntity<String>(role,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getUserRole 시 에러가 발생했습니다.",e);
		}

		return entity;
	}

	public ResponseEntity<String> modifyUserRole(String cprojNo, CollaboVO collaboVO, HttpServletRequest request) throws Exception{
		ResponseEntity<String> entity = null;
		LOGGER.debug("[요청받음] => /modifyUserRole => " + collaboVO.toString());

		try {
			collaboVO.setCprojNo(cprojNo);
			collaboService.modifyUserRole(collaboVO);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e);
			LOGGER.error("/getUserRole 시 에러가 발생했습니다.",e);
		}

		return entity;
	}

	/**
	 * 콜라보 문서 업로드
	 * @param files
	 * @param response
	 * @param request
	 * @param collaboVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadCollaboDocument")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> uploadCollaboDocument(@RequestPart(value="files",required = false) List<MultipartFile> files,
			HttpServletResponse response, HttpServletRequest request, CollaboVO collaboVO) throws Exception{
		ResponseEntity<HashMap<String, Object>> entity = null;
		LOGGER.debug("[요청받음] => /uploadCollaboDocument");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		CollaboVO resultVO = new CollaboVO();

		try {

			LOGGER.debug("==> cpno" + collaboVO.getCprojNo());
			String title = collaboUtil.getCollaboNameByCprojNo(collaboVO.getCprojNo());
			LOGGER.debug("==> title" + title);
			resultVO.setCprojNo(collaboVO.getCprojNo());
			resultVO.setTitle(title);
			LOGGER.debug("==> resultVO" + resultVO.toString());
			documentUtil.CollaboDocumentUpload(files, request, response, resultVO);
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return entity;

	}
	/**
	 * 콜라보 문서 삭제
	 * @param fileVO
	 * @return
//	 * @throws Exception
	 */
	@RequestMapping("/deleteCollaboDoc")
	@ResponseBody
	public ResponseEntity<FileVO> deleteCollaboDoc(FileVO fileVO) throws Exception{
		ResponseEntity<FileVO> entity = null;
		LOGGER.debug("[요청받음] => /deleteCollaboDoc => " + fileVO);

		try {

			documentUtil.documentRemoveResolver(fileVO);

			entity = new ResponseEntity<FileVO>(HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<FileVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}


	public String getUserIdByNickname(String userId) throws Exception {
		return mailService.getUserIdByNickname(userId);
	}

	public String getNicknameByUserId(String userId) throws Exception {
		return mailService.getNicknameByUserId(userId);
	}
}













