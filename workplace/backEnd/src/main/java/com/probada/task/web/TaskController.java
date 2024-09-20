package com.probada.task.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.probada.document.vo.FileVO;
import com.probada.task.service.TaskService;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;
import com.probada.util.DocumentUtil;
import com.probada.util.ProjectUtil;
import com.probada.util.TaskUtil;

@Controller
@RequestMapping("/app/task")
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Resource(name = "taskService")
	TaskService taskService;
	@Resource(name = "projectUtil")
	ProjectUtil projectUtil;
	@Resource(name = "documentUtil")
	DocumentUtil documentUtil;
	@Resource(name = "taskUtil")
	TaskUtil taskUtil;

	@RequestMapping("/getTaskListByProjNo")
	@ResponseBody
	public ResponseEntity<List<TaskVO>> getTaskListByProjNo(String projNo)
			throws Exception {
		ResponseEntity<List<TaskVO>> entity = null;

		LOGGER.debug("[요청받음] => /getTaskListByProjNo" + projNo);

		List<TaskVO> taskVOList = new ArrayList<TaskVO>();

		try {

			taskVOList = taskService.getTaskListByProjectNo(projNo);

			for (TaskVO taskVO : taskVOList) {
				String projTitle = projectUtil.getProjectNameByProjNo(projNo);
				taskVO.setProjTitle(projTitle);
			}

			entity = new ResponseEntity<List<TaskVO>>(taskVOList, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<List<TaskVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getTaskListForGanttByProjNo")
	@ResponseBody
	public ResponseEntity<List<TaskVO>> getTaskListForGanttByProjNo(@RequestParam(defaultValue = "") String projNo)
			throws Exception {
		ResponseEntity<List<TaskVO>> entity = null;

		LOGGER.debug("[요청받음] => /getTaskListForGanttByProjNo");

		List<TaskVO> taskVOList = new ArrayList<TaskVO>();

		try {
			taskVOList = taskService.getTaskListByProjectNo(projNo);

			entity = new ResponseEntity<List<TaskVO>>(taskVOList, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<List<TaskVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}



	@RequestMapping("/getTaskRegistInfoByProjNo")
	@ResponseBody
	public ResponseEntity<TaskVO> getTaskRegistInfo(String projNo) throws Exception {
		ResponseEntity<TaskVO> entity = null;

		TaskVO taskRegistVO = new TaskVO();
		try {

			String projTitle = projectUtil.getProjectNameByProjNo(projNo);
			List<UserVO> userList = projectUtil.getProjectMemberByProjNo(projNo);
			taskRegistVO.setProjTitle(projTitle);
			taskRegistVO.setUserList(userList);

			entity = new ResponseEntity<TaskVO>(taskRegistVO, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<TaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@RequestMapping("/getTaskDetailByTaskNo")
	@ResponseBody
	public ResponseEntity<TaskVO> getTaskDetailByTaskNo(TaskVO taskVO) throws Exception {
		ResponseEntity<TaskVO> entity = null;
		TaskVO detailVO = new TaskVO();

		try {
			detailVO = taskService.getTaskDetailByTaskNo(taskVO);
			String projTitle = projectUtil.getProjectNameByProjNo(taskVO.getProjNo());
			List<UserVO> userList = projectUtil.getProjectMemberByProjNo(taskVO.getProjNo());
			detailVO.setProjTitle(projTitle);
			detailVO.setUserList(userList);
			detailVO.setProjNo(taskVO.getProjNo());

			detailVO = documentUtil.readTaskDocByTaskTitleANDprojNo(detailVO);

			entity = new ResponseEntity<TaskVO>(detailVO, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<TaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/modifyTaskDetailByTaskNo")
	@ResponseBody
	public ResponseEntity<TaskVO> modifyTaskDetailByTaskNo(@RequestPart(value="files",required = false) List<MultipartFile> files,
			HttpServletRequest request, HttpServletResponse response, TaskVO taskVO)
			throws Exception {
		ResponseEntity<TaskVO> entity = null;
		LOGGER.debug("[요청받음] => /modifyTaskDetailByTaskNo");

		try {

			taskUtil.getSessionId(request, taskVO);
			taskService.modifyTaskDetailByTaskNo(taskVO);
			documentUtil.taskUpload(files, request, response, taskVO);

			entity = new ResponseEntity<TaskVO>(taskVO,HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<TaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/registTask")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> registTask(@RequestPart(value="files",required = false) List<MultipartFile> files,
			HttpServletResponse response, HttpServletRequest request, TaskVO taskVO) throws Exception {
		ResponseEntity<HashMap<String, Object>> entity = null;

		LOGGER.debug("taskVO==============>"+taskVO);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		try {

			taskUtil.getSessionId(request, taskVO);
			String taskNo = taskService.registTask(taskVO);
			documentUtil.taskUpload(files, request, response, taskVO);
			hashMap.put("taskNo", taskNo);

			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}


	@PostMapping(value="/read")
	@ResponseBody
	public List<TaskVO> read(String projNo) throws Exception {

		LOGGER.debug("요청받음 read" + projNo);

		List<TaskVO> taskVOList = new ArrayList<TaskVO>();

		try {

			taskVOList = taskService.getTaskListByProjectNo(projNo);

			for (TaskVO taskVO : taskVOList) {
				String projTitle = projectUtil.getProjectNameByProjNo(projNo);
				taskVO.setProjTitle(projTitle);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return taskVOList;
	}

	@PostMapping(value="/update")
	@ResponseBody
	private TaskVO update (TaskVO taskVO, String projNo) throws Exception{

		LOGGER.debug("[요청받음] => /registTask VO =>"+taskVO.toString());

		taskVO.setProjNo(projNo);

		taskService.modifyTaskStatus(taskVO);

		return taskVO;
	}

	@RequestMapping("/regist")
	@ResponseBody
	public void regist(TaskVO taskVO) throws Exception {

		LOGGER.debug("[요청받음] => /registTask VO =>"+taskVO.toString());


		try {

			taskService.registTask(taskVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value="/updateTaskStatus")
	@ResponseBody
	public void updateTaskStatus(TaskVO taskVO) throws Exception {

		LOGGER.debug("요청받음 updateTaskStatus" + taskVO.getStatus());

		try {

			taskService.modifyTaskStatus(taskVO);

		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PostMapping(value="/delete")
	@ResponseBody
	private void delete (TaskVO taskVO) throws Exception{

		taskService.removeTaskByTaskNo(taskVO);

	}

	@RequestMapping("/taskDownload")
	public String taskDownload(String path, Model model) throws Exception {

		LOGGER.debug("[요청받음] => /taskDownload => " + path);

		String url = "downloadFile";

		//디렉토리는 다운이 되지 않으니 무조건 패스가 있는 파일만 /유무 판단 안해도 가능
		String FileName = path.substring(path.lastIndexOf("/")+1);

		model.addAttribute("savedPath", path);
		model.addAttribute("fileName", FileName);
		// path = 3조 PMS probada/업무/circle-dot-o.png

		return url;
	}


	@RequestMapping("/taskDocumentRead")
	@ResponseBody
	public ResponseEntity<TaskVO> taskDocumentRead(TaskVO taskVO) throws Exception{

		ResponseEntity<TaskVO> entity = null;
		TaskVO resultVO = new TaskVO();
	try {

		resultVO = documentUtil.readTaskDocByTaskTitleANDprojNo(taskVO);

		entity = new ResponseEntity<TaskVO>(resultVO,HttpStatus.OK);

	} catch (Exception e) {
		entity = new ResponseEntity<TaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
	}
	return entity;
	}

	@RequestMapping("/taskDocumentRemove")
	@ResponseBody
	public ResponseEntity<FileVO> taskDocumentRemove(FileVO fileVO) throws Exception{

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
}
