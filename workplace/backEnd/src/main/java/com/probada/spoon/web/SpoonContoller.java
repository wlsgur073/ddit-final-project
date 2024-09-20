package com.probada.spoon.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.collabo.vo.CollaboVO;
import com.probada.spoon.service.SpoonService;
import com.probada.spoon.vo.SpoonVO;
import com.probada.task.vo.TaskVO;
import com.probada.task.web.TaskController;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.DocumentUtil;
import com.probada.util.ProjectUtil;
import com.probada.util.TaskUtil;

@Controller
@RequestMapping("/app/spoon")
public class SpoonContoller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	@Resource(name="spoonService")
	SpoonService spoonService;
	
	@Resource(name="projectUtil")
	ProjectUtil projectUtil;
	
	@Resource(name = "documentUtil")
	DocumentUtil documentUtil;
	
	@Resource(name = "taskUtil")
	TaskUtil taskUtil;
	
	@Resource(name = "collaboUtil")
	CollaboUtil collaboUtil;
	
	/**
	 * 스푼 버튼 클릭시 해당 업무에 대한 정보 가져옴
	 * @param taskVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskDetailByTaskNo")
	@ResponseBody
	public ResponseEntity<TaskVO> getTaskDetailByTaskNo(TaskVO taskVO) throws Exception {
		ResponseEntity<TaskVO> entity = null;
		
		LOGGER.debug("[요청받음] Spoon => /getTaskDetailByTaskNo" + taskVO);
		TaskVO detailVO = new TaskVO();
		
		try {
			detailVO = spoonService.getTaskDetailByTaskNo(taskVO);
			String projTitle = projectUtil.getProjectNameByProjNo(taskVO.getProjNo());
			List<UserVO> userList = projectUtil.getProjectMemberByProjNo(taskVO.getProjNo());
			detailVO.setProjTitle(projTitle);
			detailVO.setUserList(userList);
			detailVO.setProjNo(taskVO.getProjNo());
			
			detailVO = documentUtil.readTaskDocByTaskTitleANDprojNo(detailVO);
			
			LOGGER.debug("detailVO => "+ detailVO);
			entity = new ResponseEntity<TaskVO>(detailVO, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<TaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.debug("getTaskDetailByTaskNo에서 에러",e);
		}
		
		return entity;
	}
	
	/**
	 * 프로젝트 번호로 해당 프로젝트의 업무 리스트 출력
	 * @param projNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskListByProjNo")
	@ResponseBody
	public ResponseEntity<SpoonVO> getTaskListByProjNo(String projNo) throws Exception {
		ResponseEntity<SpoonVO> entity = null;
		
		LOGGER.debug("[요청받음] Spoon => /getTaskListByProjNo" + projNo);
		SpoonVO spoonVO = new SpoonVO();
		
		List<TaskVO> taskVOList = new ArrayList<TaskVO>();
		
		try {
			String projTitle = projectUtil.getProjectNameByProjNo(projNo);
			spoonVO.setTitle(projTitle);
			
			List<CollaboVO> cprojTitle = collaboUtil.getCprojectNameByProjNo(projNo);
			spoonVO.setCprojTitleList(cprojTitle);
			LOGGER.debug("[요청받음] Spoon => spoonVO의 cprojTitle" + cprojTitle);
			
			taskVOList = spoonService.getTaskListByProjectNo(projNo);
			spoonVO.setTaskList(taskVOList);
			spoonVO.setProjNo(projNo);
			
			LOGGER.debug("[요청받음] Spoon => spoonVO" + spoonVO);
			entity = new ResponseEntity<SpoonVO>(spoonVO, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<SpoonVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.debug("getTaskListByProjNo에서 에러",e);
		}
		return entity;
	}
	
	@PostMapping(value="/setTaskToCollabo")
	@ResponseBody
	public ResponseEntity<SpoonVO> setTaskToCollabo(@RequestBody SpoonVO spoonVO)throws Exception{
		ResponseEntity<SpoonVO> entity = null;
		
		List<TaskVO> taskList = new ArrayList<TaskVO>();
		List<String> taskNoList = spoonVO.getTaskNoList();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		try {

			for (int i = 0; i < taskNoList.size(); i++) {
				
				TaskVO taskVO = new TaskVO();
				taskVO.setTaskNo(taskNoList.get(i));
				taskVO.setProjNo(spoonVO.getProjNo());
				TaskVO taskResultVO = spoonService.getTaskDetailByTaskNo(taskVO);
				
				taskResultVO.setProjNo("");
				taskResultVO.setCprojNo(spoonVO.getCprojNo());
				taskResultVO.setStatus("B201");
				LOGGER.debug("setTaskToCollabo() 디버그 => "+taskResultVO.toString());
				
				spoonService.setTaskToCollabo(taskResultVO);
				
			}
			
			
			
			entity = new ResponseEntity<SpoonVO>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<SpoonVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.debug("setTaskToCollabo에서 에러",e);
		}
		return entity;
	}
	
	
	
	
	
}
