package com.probada.project.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.probada.document.vo.FileVO;
import com.probada.mail.command.MailRegistCommand;
import com.probada.mail.service.MailService;
import com.probada.mail.vo.MailVO;
import com.probada.project.service.ProjectService;
import com.probada.project.service.ProjectTagService;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;
import com.probada.util.DocumentUtil;
import com.probada.util.ProjectUtil;

@Controller
@RequestMapping("/app/project")
public class ProjectController {

	@Resource(name="projectService")
	ProjectService projectService;
	@Resource(name="projectUtil")
	ProjectUtil projectUtil;
	@Resource(name="documentUtil")
	DocumentUtil documentUtil;
	@Resource(name="mailService")
	MailService mailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@RequestMapping("/main")
	public ModelAndView main(@RequestParam(defaultValue="") String tab, ModelAndView mnv) {
		String url = "/web-app/project/project";
		mnv.addObject("tab",tab);
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping("/getProjectList")
	@ResponseBody
	public ResponseEntity<List<ProjectVO>> getProjectList() throws Exception {
		ResponseEntity<List<ProjectVO>> entity = null;

		LOGGER.debug("[요청받음] => /getProjectList");

		List<ProjectVO> projectList = new ArrayList<ProjectVO>();

		try {

			projectList = projectService.getProjectList();
			projectList = projectUtil.getProjectTagList(projectList);
			projectList = projectUtil.getProjectMemberList(projectList);

			entity = new ResponseEntity<List<ProjectVO>>(projectList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<ProjectVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getProjectListByUserId")
	@ResponseBody
	public ResponseEntity<List<ProjectVO>> getProjectListByUserId(HttpServletRequest request) throws Exception {
		ResponseEntity<List<ProjectVO>> entity = null;

		LOGGER.debug("[요청받음] => /getProjectListByUserId");

		List<ProjectVO> projectList = new ArrayList<ProjectVO>();
		HttpSession session = request.getSession();

		try {
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String userId = userVO.getUserId();

			projectList = projectService.getProjectListByUserId(userId);
			projectList = projectUtil.getProjectTagList(projectList);
			projectList = projectUtil.getProjectMemberList(projectList);

			entity = new ResponseEntity<List<ProjectVO>>(projectList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<ProjectVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/searchProj")
	public ModelAndView searchProj(ModelAndView mnv) {

		String url = "/web-app/project/project-search-detail";
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping("/getProjectByProjNo")
	public ResponseEntity<ProjectVO> getProjectByProjNo(@RequestParam(defaultValue="") String projNo) throws Exception {
		ResponseEntity<ProjectVO> entity = null;

		ProjectVO projectVO = new ProjectVO();

		try {

			projectVO = projectService.getProjectByProjNo(projNo);
			projectVO = projectUtil.getProjectTagListByProjNo(projNo, projectVO);

			entity = new ResponseEntity<ProjectVO>(projectVO,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<ProjectVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping(value = "/modifyProjectDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modifyProjectDetail(ProjectVO projectVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		try {

			projectService.modifyProjectDetail(projectVO);

			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping(value = "/registProject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> registProject(HttpServletRequest request, ProjectVO projectVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		HttpSession session = request.getSession();

		try {

			String projNo = projectService.registProject(projectVO);

			projectUtil.setProjectLeaderRelation(session, projectVO);

			//태그추가하는 메서드 만들기
			projectUtil.registProjectTagResolver(projectVO);

			hashmap.put("projNo", projNo);

			entity = new ResponseEntity<HashMap<String, Object>>(hashmap,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@RequestMapping(value = "/modifyProjectNotice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modifyProjectNotice(ProjectVO projectVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		try {

			projectService.modifyProjectNotice(projectVO);

			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@RequestMapping(value = "/removeProjectNotice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> removeProjectNotice(ProjectVO projectVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		try {

			projectService.modifyProjectNotice(projectVO);

			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/uploadProjectDocument")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> uploadProjectDocument(@RequestPart(value="files",required = false) List<MultipartFile> files,
			HttpServletResponse response, HttpServletRequest request, ProjectVO projectVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ProjectVO resultVO = new ProjectVO();
		try {

			String title = projectUtil.getProjectNameByProjNo(projectVO.getProjNo());
			resultVO.setProjNo(projectVO.getProjNo());
			resultVO.setTitle(title);

			documentUtil.projectDocumentUpload(files, request, response, resultVO);

			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/deleteProjectDoc")
	@ResponseBody
	public ResponseEntity<FileVO> deleteProjectDoc(FileVO fileVO) throws Exception{

		LOGGER.debug("[요청받음] => /taskDocumentRemove => " + fileVO);

		ResponseEntity<FileVO> entity = null;

		try {

			documentUtil.documentRemoveResolver(fileVO);

			entity = new ResponseEntity<FileVO>(HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<FileVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}


	@RequestMapping("/getProjectMemberList")
	@ResponseBody
	public ResponseEntity<List<UserVO>> getProjectMemberList(String projNo) throws Exception{

		LOGGER.debug("[요청받음] => /getProjectMemberList => " + projNo);
		
		ResponseEntity<List<UserVO>> entity = null;

		try {

			List<UserVO> userList = projectUtil.getProjectMemberByProjNo(projNo);

			entity = new ResponseEntity<List<UserVO>>(userList,HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/modifyUserRole")
	@ResponseBody
	public ResponseEntity<String> modifyUserRole(String projNo, ProjectVO projectVO, HttpServletRequest request) throws Exception{

		LOGGER.debug("[요청받음] => /modifyUserRole => " + projectVO.toString());

		ResponseEntity<String> entity = null;

		try {

			projectVO.setProjNo(projNo);
			projectService.modifyUserRole(projectVO);

			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/getUserRole")
	@ResponseBody
	public ResponseEntity<String> getUserRole(String projNo, HttpServletRequest request) throws Exception{

		LOGGER.debug("[요청받음] => /getUserRole => " + projNo);

		ResponseEntity<String> entity = null;

		try {
			ProjectVO projectVO = new ProjectVO();

			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			projectVO.setUserId(userVO.getUserId());
			projectVO.setProjNo(projNo);

			String role = projectService.getUserRole(projectVO);

			entity = new ResponseEntity<String>(role,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/getProjectListByParamUserId")
	@ResponseBody
	public ResponseEntity<List<ProjectVO>> getProjectListByParamUserId(@RequestParam("userId") String userId) throws Exception {
		ResponseEntity<List<ProjectVO>> entity = null;

		LOGGER.debug("[요청받음] => /getProjectListByParamUserId");

		List<ProjectVO> projectList = new ArrayList<ProjectVO>();

		try {
			projectList = projectService.getProjectListByUserId(userId);
			projectList = projectUtil.getProjectTagList(projectList);
			projectList = projectUtil.getProjectMemberList(projectList);

			entity = new ResponseEntity<List<ProjectVO>>(projectList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<ProjectVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@RequestMapping("/removeProjectUserRelation")
	@ResponseBody
	public ResponseEntity<String> removeProjectUserRelation(ProjectVO projectVO) throws Exception{

		LOGGER.debug("[요청받음] => /removeProjectUserRelation => " + projectVO);

		ResponseEntity<String> entity = null;

		try {

			projectService.removeProjectUserRelation(projectVO);

			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/quitProjectUserRelation")
	@ResponseBody
	public ResponseEntity<String> quitProjectUserRelation(ProjectVO projectVO) throws Exception{

		LOGGER.debug("[요청받음] => /quitProjectUserRelation => " + projectVO);

		ResponseEntity<String> entity = null;

		try {
				projectService.removeProjectUserRelation(projectVO);

			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/registInviteMail")
	@ResponseBody
	public ResponseEntity<String> registInviteMail(MailRegistCommand regData,HttpServletRequest request) throws Exception{

		ResponseEntity<String> entity = null;

		try {

			LOGGER.debug("/registInviteMail ====================>>>" + regData.toString());
			String dist = "B901";
			HttpSession session = request.getSession();
			UserVO sessionVO = (UserVO) session.getAttribute("userVO");

			String[] userToArr = regData.getUserTo().split(",");

			for (int i = 0; i < userToArr.length; i++) {

				String userId = getUserIdByNickname(regData.getUserTo());
				regData.setUserTo(userId);

				MailVO mailVO = new MailVO();
				mailVO.setTitle(sessionVO.getNickname() + " 님의 프로젝트 초대 메일");
				mailVO.setContent(regData.getContent());
				mailVO.setUserTo(regData.getUserTo());
				mailVO.setUserFrom(sessionVO.getUserId());
				mailVO.setDist(dist);

				LOGGER.debug("mailVO============>" + mailVO.toString());
				mailService.registMailAttachFile(mailVO);

			}

			entity = new ResponseEntity<String>("hello",HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage());
		}
		return entity;
	}

	@RequestMapping("/registUserProjectRelation")
	@ResponseBody
	public ResponseEntity<String> registUserProjectRelation(ProjectVO projectVO) throws Exception{
		ResponseEntity<String> entity = null;

	try {
		String userId = getUserIdByNickname(projectVO.getUserId());
		projectVO.setUserId(userId);

		int count = projectService.getCountDeletedUserByUserId(projectVO);

		if(count == 0) {
			projectService.registProjectUserRelation(projectVO);
		} else {
			projectService.modifyProjectUserRelationToRejoin(projectVO);
		}

		entity = new ResponseEntity<String>(projectVO.getProjNo(),HttpStatus.OK);

	} catch (Exception e) {
		entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
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
/*
	@RequestMapping("/getProjectDashCard")
	@ResponseBody
	public ResponseEntity<List<Map<String,Integer>>> getProjectDashCard() throws Exception {
		ResponseEntity<List<Map<String,Integer>>> entity = null;

		return entity;
	}*/
}
