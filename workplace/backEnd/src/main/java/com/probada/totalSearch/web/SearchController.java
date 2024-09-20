package com.probada.totalSearch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.chat.vo.ChatVO;
import com.probada.project.service.ProjectService;
import com.probada.project.service.ProjectTagService;
import com.probada.project.vo.ProjectTagVO;
import com.probada.project.vo.ProjectVO;
import com.probada.totalSearch.service.SearchService;
import com.probada.totalSearch.vo.SearchProjVO;
import com.probada.user.service.UserService;
import com.probada.user.vo.UserVO;



@RestController
@RequestMapping("/search")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	ProjectTagService projectTagService;
	
	
	
	
	
	
	
	
	
	@RequestMapping("/result")
	public ModelAndView result(ModelAndView mnv, HttpSession session,String resultData) throws Exception{
		
		String url = "/web-app/Search/totalSearch";

		UserVO userVO= (UserVO)session.getAttribute("userVO");
		LOGGER.debug("test=> {}",resultData);
		
	 	UserVO user = new UserVO();
	 	List<UserVO> popUser = new ArrayList<>();
	 	List<SearchProjVO> popProj = new ArrayList<>();
	 	List<String> popPL = new ArrayList<>();
	 	UserVO userPl = new UserVO();
	 	
	 	popUser = userService.getPriUser();
	 	popProj = searchService.getResultSearchPopProject();
	 	popPL = searchService.getPopPLSearch();
	 		
	 	LOGGER.debug("popUser1=> {}",popUser);
	 	LOGGER.debug("popProj2=> {}",popProj);
	 	LOGGER.debug("popPL3=> {}",popPL);
		
	 	
	 	for (int i = 0; i < 3; i++) {
	 		 userPl = searchService.getUserByNickName(popPL.get(i));
	 		 
	 		 popProj.get(i).setPlId(popPL.get(i));
	 		 popProj.get(i).setPicture(userPl.getPicture());
		}
	 	
	 	if(popUser.size()>3) {
	 		//사이즈가 3이 될 때 까지 뒤에서 자르기
	 		LOGGER.debug("popUser.size()=> {}",popUser.size());
			
	 		for (int j = popUser.size(); j > 3; j--) {
	 			popUser.remove(j-1);
			}
	 		LOGGER.debug("if문 안에 popUser=> {}",popUser);
	 	}
	 	
	 	if(popProj.size()>3) {
	 		
	 		for (int i = popProj.size(); i > 3; i--) {
	 			popProj.remove(i-1);
			}
	 		LOGGER.debug("if문 안에 popProj=> {}",popProj);
	 	}
	 	
		LOGGER.debug("popProj=> {}",popProj);
	 	
		
		
		
		for (int i = 0; i < popProj.size(); i++) {
			
			String projNo = "";
			projNo = popProj.get(i).getProjNo();
			List<String> tagNameList = new ArrayList<>();
			List<ProjectTagVO> tagList = projectTagService.getTagNameList(projNo);
			LOGGER.debug("tagList  확인=> {}",tagList);
			for (ProjectTagVO projectTagVO : tagList) {
				
				String tagName = projectTagVO.getTagName();
				tagNameList.add(tagName);
				
			}
			
			popProj.get(i).setTagName(tagNameList);
			
			
		}
		
		statusCheck(popProj);
		
		LOGGER.debug("popProj tagNAme 확인=> {}",popProj);
		
		mnv.addObject("popUser", popUser);
		mnv.addObject("popProj", popProj);
	
		
		mnv.addObject("resultData", resultData);
		mnv.setViewName(url);
	 	
	    return mnv;
	}
	
	
	@RequestMapping(value="/data", method= RequestMethod.POST)
	public ResponseEntity <List<String>> data(HttpSession session) throws Exception{
		
		ResponseEntity<List<String>> entity = null;
		List<String> realTotal = new ArrayList<>();
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		//모든 유저
		List<UserVO> user = new ArrayList<>();
		
		//모든 프로젝트 
		List<ProjectVO> project = new ArrayList<>();

		//모든 콜라보 프로젝트 
		List<SearchProjVO> colProject = new ArrayList<>();
		
		
		project = searchService.getPublicProject();
		user = userService.getPriUser();
		colProject = searchService.getCollaboProject();
		
		realTotal = realTotalResult(project,user,colProject);
		entity =  new ResponseEntity <List<String>>(realTotal,HttpStatus.OK);

		return entity;
	}
	
	
	@RequestMapping("/readUser")
	@ResponseBody
	public ResponseEntity<List<UserVO>> getSearchUser(HttpSession session,String result) throws Exception {
		ResponseEntity<List<UserVO>> entity = null;

		LOGGER.debug("resut {}",result);
		LOGGER.debug("[요청받음] => /getSearchUser");
		UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	
	 	List<UserVO> user = new ArrayList<>();
	 	
	 	user = searchService.getResultSearchUser(result);
	 

		try {

			LOGGER.debug("프로젝트 리스트, {}",user);

			entity = new ResponseEntity<List<UserVO>>(user,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	
	
	@RequestMapping("/readProject")
	@ResponseBody
	public ResponseEntity<List<SearchProjVO>> getSearchProject(HttpSession session,String result) throws Exception {
		ResponseEntity<List<SearchProjVO>> entity = null;
		
		LOGGER.debug("resut {}",result);
		
		LOGGER.debug("[요청받음] => /getSearchProject");
		UserVO userVO= (UserVO)session.getAttribute("userVO");
	 	
	 	List<ProjectVO> project = new ArrayList<>();
	 	List<String> PL = new ArrayList<>();
	 	
	 	List<SearchProjVO> colProject = new ArrayList<>();
	 	List<String> colPL = new ArrayList<>();
	 	
	 	List<SearchProjVO> projVO = new ArrayList<>();
	 	
	 	project = searchService.getResultSearchProject(result);
	 	PL = searchService.getResultSearchPL(result);
	 	colProject = searchService.getResultSearchCollaboProject(result);
	 	colPL = searchService.getCollaboPL(result);	 	
	 	
	 	
	 	LOGGER.debug("project {}",project);
	 	LOGGER.debug("PL {}",PL);
	 	LOGGER.debug("colProject=> {}",colProject);
	 	LOGGER.debug("colPL=> {}",colPL);
	 	
	 	
	 	LOGGER.debug("project.size() {}",project.size());
	 	
	 	for (int i = 0; i < project.size(); i++) {
			
	 		SearchProjVO tempVO = new SearchProjVO();
	 		
	 		tempVO.setPlId(PL.get(i));
	 		tempVO.setPrivacy(project.get(i).getPrivacy());
	 		tempVO.setTitle(project.get(i).getTitle());
	 		tempVO.setProjNo(project.get(i).getProjNo());
	 		tempVO.setStatus(project.get(i).getStatus());
	 		
	 		projVO.add(tempVO);
	 		
		}
	 	LOGGER.debug("projVO123123=> {}",projVO);
	 	
	 	int j=0;
	 	
	 	for (int i = 0; i < colProject.size(); i++) {
			
	 		SearchProjVO tempVO = new SearchProjVO();
	 		
	 		tempVO.setPlId(colPL.get(j));
		 	tempVO.setPlIdSec(colPL.get(j+1));
		 	
	 		 j = i+2;

	 		tempVO.setPrivacy(colProject.get(i).getPrivacy());
	 		tempVO.setTitle(colProject.get(i).getTitle());
	 		tempVO.setProjNo(colProject.get(i).getProjNo());
	 		tempVO.setIntro(colProject.get(i).getIntro());
	 		tempVO.setStatus(colProject.get(i).getStatus());
	 		
	 		projVO.add(tempVO);
	 		
		}

	 	statusCheck(projVO);
	 	
	 	
		try {

			LOGGER.debug("2523001 projVO, {}",projVO);
			entity = new ResponseEntity<List<SearchProjVO>>(projVO,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<SearchProjVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	

	
	
	


	/**
	 * 검색 결과가 중복 될 경우 하나만 출력
	 * 
	 */
	public List<String> realTotalResult(List<ProjectVO> project, List<UserVO> user, List<SearchProjVO> colProject){
		
		HashSet<String> temp = new HashSet<String>();
		
		
		List<String> userName = new ArrayList<>();
		List<String> projectName = new ArrayList<>();
		List<String> colProjectName = new ArrayList<>();
		List<String> total = new ArrayList<>();
		List<String> realTotal = new ArrayList<>();
		
		for (SearchProjVO pv : colProject) {
			
			colProjectName.add(pv.getTitle());
			
		}
		
		for (ProjectVO pv : project) {
			projectName.add(pv.getTitle());
		}

		for (UserVO uv : user) {
			userName.add(uv.getNickname());
		}
		
		
		total.addAll(colProjectName);
		total.addAll(userName);
		total.addAll(projectName);
		
		for (String string : total) {
			
			temp.add(string);
			
		}
		
		
		LOGGER.debug("temp=>{}",temp);
		realTotal = new ArrayList<String>(temp);
		
		return realTotal;
		
	}
	
	
	/**
	 * 상태 체크
	 * @param projVO
	 * @return
	 */
	private List<SearchProjVO> statusCheck(List<SearchProjVO> projVO) {
		
		for (SearchProjVO searchProjVO : projVO) {
			
			
			if(searchProjVO.getStatus().equals("A501")) {
				
				searchProjVO.setStatus("진행중");
				
			}else if(searchProjVO.getStatus().equals("A502")) {
				
				searchProjVO.setStatus("지연중");
				
			}else if(searchProjVO.getStatus().equals("A503")) {
				
				searchProjVO.setStatus("파기요청 ");
				
			}else if(searchProjVO.getStatus().equals("A504")) {
				
				searchProjVO.setStatus("완료");
				
			}else if(searchProjVO.getStatus().equals("A601")) {
				
				searchProjVO.setStatus("합의중");
				
			}else if(searchProjVO.getStatus().equals("A602")) {
				
				searchProjVO.setStatus("진행중");
				
			}else if(searchProjVO.getStatus().equals("A603")) {
				
				searchProjVO.setStatus("지연중");
				
			}else if(searchProjVO.getStatus().equals("A604")) {
				
				searchProjVO.setStatus("파기요청");
				
			}else if(searchProjVO.getStatus().equals("A604")) {
				
				searchProjVO.setStatus("완료");
				
			}else {
				
				searchProjVO.setStatus("상태 없움");
			}
			
			
		}
		
		
		
		return projVO;
		
	}
	
	
	@RequestMapping(value="/changeIdByNickName", method= RequestMethod.POST)
	public ResponseEntity <String> changeIdByNickName(HttpSession session, String nickname) throws Exception{
		
		ResponseEntity<String> entity = null;
		String userId = null;
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		
		UserVO user = searchService.getUserByNickName(nickname);
		
		userId = user.getUserId();
		
		
		
		entity =  new ResponseEntity <String>(userId,HttpStatus.OK);

		return entity;
	}
	
	
	
	@RequestMapping(value="/changeTagTitleByTagNo", method= RequestMethod.POST)
	public ResponseEntity <String> changeTagTitleByTagNo(HttpSession session, String tagName) throws Exception{
		
		ResponseEntity<String> entity = null;
		String tagNo = null;
		
		UserVO userVO= (UserVO)session.getAttribute("userVO");
		
		tagNo = searchService.getTagNoByTitle(tagName);
		
		entity =  new ResponseEntity <String>(tagNo,HttpStatus.OK);

		return entity;
	}
	
	

}



	
	
	
	
	
	
	