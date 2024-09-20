package com.probada.myWork.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.probada.myWork.command.myTaskChartCommand;
import com.probada.myWork.service.MyTaskService;
import com.probada.task.vo.TaskVO;
import com.probada.util.ProjectUtil;

@Controller
@RequestMapping("/app/myWork")
public class MyTaskController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyTaskController.class);
	
	@Autowired
	MyTaskService myTaskService;
	
	@Resource(name="projectUtil")
	ProjectUtil projectUtil;
	
	@PostMapping(value="/getTaskListByUserId")
	@ResponseBody
	public List<TaskVO> getTaskListByUserId(String userId) throws Exception {
		List<TaskVO> myTaskList = null;
		
		try {
			myTaskList = myTaskService.getTaskListByUserId(userId);
			
			for(TaskVO taskVO : myTaskList) {
				String projTitle = projectUtil.getProjectNameByProjNo(taskVO.getProjNo());
				taskVO.setProjTitle(projTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myTaskList;
	}
	
	@PostMapping(value="/updateMyTaskListByUserId")
	@ResponseBody
	public TaskVO updateMyTaskListByUserId(TaskVO taskVO, String userId) throws Exception {
		taskVO.setUserId(userId);
		myTaskService.modifyMyTaskStatus(taskVO);
		return taskVO;
	}

	@RequestMapping("/getProjectNameForSort")
	@ResponseBody
	public ResponseEntity<List<TaskVO>> getProjectNameForSort(String userId) throws Exception {
		ResponseEntity<List<TaskVO>> entity = null;
		List<TaskVO> myProjectNameList = null;
		
		try {
			myProjectNameList = myTaskService.getProjectNameForSort(userId);
			entity = new ResponseEntity<List<TaskVO>>(myProjectNameList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<TaskVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/deleteMyTaskByTaskNo")
	public String deleteMyTaskByTaskNo(String taskNo, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/app/myWork";
		
		myTaskService.deleteMyTaskByTaskNo(taskNo);

		rttr.addFlashAttribute("from", "deleteMyTaskByTaskNo");
		
		return url;
	}
	
	@RequestMapping("getMyTaskChartByUserId")
	@ResponseBody
	public ResponseEntity<myTaskChartCommand> getMyTaskChartByUserId(String userId) throws Exception {
		ResponseEntity<myTaskChartCommand> entity = null;
		myTaskChartCommand myTaskChartData = null;
		
		try {
			myTaskChartData = myTaskService.getMyTaskChartByUserId(userId);
			entity = new ResponseEntity<myTaskChartCommand>(myTaskChartData, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<myTaskChartCommand>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
