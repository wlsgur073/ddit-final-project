package com.probada.collabo.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.probada.collabo.service.CollaboTaskService;
import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.collabo.vo.CollaboVO;
import com.probada.document.vo.FileVO;
import com.probada.task.vo.TaskVO;
import com.probada.task.web.TaskController;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.DocumentUtil;

/**
 * @author PC-24
 *
 */
@Controller
@RequestMapping("/app/collabo")
public class CollaboTaskController {
	
	@Resource(name="collaboTaskService")
	CollaboTaskService collaboTaskService;
	
	@Resource(name="collaboUtil")
	CollaboUtil collaboUtil;
	
	@Resource(name="documentUtil")
	DocumentUtil documentUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
	
	@RequestMapping("/getTaskListByCprojNo")
	@ResponseBody
	public ResponseEntity<List<CollaboTaskVO>> getTaskListByCprojNo(String cprojNo) throws Exception{
		ResponseEntity<List<CollaboTaskVO>> entity = null;
		
		LOGGER.debug("[요청받음] => /getTaskListByCprojNo" + cprojNo);
		
		List<CollaboTaskVO> taskVOList = new ArrayList<CollaboTaskVO>();
		
		try {
			taskVOList = collaboTaskService.getTaskListByCprojNo(cprojNo);
			
			for (CollaboTaskVO collaboTaskVO : taskVOList) {
				String title = collaboUtil.getCollaboNameByCprojNo(cprojNo);
				collaboTaskVO.setCprojTitle(title);
			}
			entity = new ResponseEntity<List<CollaboTaskVO>>(taskVOList, HttpStatus.OK);
		} catch (Exception e) {
			entity =new ResponseEntity<List<CollaboTaskVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/getTaskListByCprojNo에서 에러가 발생했습니다.",e); 
		}
		return entity;
		
	}
	
	
	/**
	 * CollaboTask 읽어오는 메서드들 read/update/regist
	 * @param cprojNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/TaskRead")
	@ResponseBody
	public List<CollaboTaskVO> TaskRead(String cprojNo)throws Exception{
		
		LOGGER.debug("[요청받음] => /TaskRead" + cprojNo);
		
		List<CollaboTaskVO> taskList = new ArrayList<CollaboTaskVO>();
		
		try {
			taskList = collaboTaskService.getTaskListByCprojNo(cprojNo);
			
			for (CollaboTaskVO collaboTaskVO : taskList) {
				String title = collaboUtil.getCollaboNameByCprojNo(cprojNo);
				collaboTaskVO.setCprojTitle(title);
				
				LOGGER.debug("------------->"+collaboTaskVO.getTaskNo());
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/Taskread에서 에러가 발생했습니다.",e); 
		}
		
		return taskList;
	}
	
	@PostMapping(value="/TaskUpdate")
	@ResponseBody
	private CollaboTaskVO TaskUpdate(CollaboTaskVO collaboTaskVO, String cprojNo)throws Exception{
		LOGGER.debug("[요청받음] => /TaskUpdate" + collaboTaskVO);
		
		collaboTaskVO.setCprojNo(cprojNo);
		
		collaboTaskService.modifyTaskStatus(collaboTaskVO);
		
		return collaboTaskVO;
	}
	
	@RequestMapping("/TaskRegist")
	@ResponseBody
	public void TaskRegist(CollaboTaskVO collaboTaskVO)throws Exception{
		LOGGER.debug("[요청받음] => /TaskRegist" + collaboTaskVO);
		
		try {
			
			collaboTaskService.registTask(collaboTaskVO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/TaskRegist에서 에러가 발생했습니다.",e); 
		}
	}
	
	@RequestMapping("/TaskDelete")
	@ResponseBody
	private void TaskDelete(CollaboTaskVO collaboTaskVO)throws Exception{
		collaboTaskService.removeTaskByTaskNo(collaboTaskVO);
	}
	
	
	@RequestMapping("/getTaskDetailByTaskNo")
	@ResponseBody
	public ResponseEntity<CollaboTaskVO> getTaskDetailByTaskNo(CollaboTaskVO collaboTaskVO)throws Exception{
		LOGGER.debug("[요청받음] => /getTaskDetailByTaskNo" + collaboTaskVO);
		ResponseEntity<CollaboTaskVO> entity = null;
		CollaboTaskVO detail = new CollaboTaskVO();
		
		String test = "완료";
		
		try {
			detail = collaboTaskService.getTaskDetailByTaskNo(collaboTaskVO);
			LOGGER.debug("getTaskDetailByTaskNo" + detail);
			String cprojTitle = collaboUtil.getCollaboNameByCprojNo(collaboTaskVO.getCprojNo());
			LOGGER.debug("getCollaboNameByCprojNo" + cprojTitle);
			List<UserVO> userList = collaboUtil.getProjectMemberByCrojNo(collaboTaskVO.getCprojNo());
			LOGGER.debug("getProjectMemberByCrojNo" + userList);
			detail.setCprojTitle(cprojTitle);
			detail.setUserList(userList);
			detail.setCprojNo(collaboTaskVO.getCprojNo());
			
			LOGGER.debug("detail to string =====================>>>" + detail.toString());
			
			detail = documentUtil.readTaskDocByTaskTitleANDcprojNo(detail);
			LOGGER.debug("readTaskDocByTaskTitleANDcprojNo" + test);
			LOGGER.debug("[요청받음]/getTaskDetailByTaskNo의 detail => "+ detail);
			entity = new ResponseEntity<CollaboTaskVO>(detail, HttpStatus.OK);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/getTaskDetailByTaskNo에서 에러가 발생했습니다.",e); 
		}
		return entity;
	}
	@RequestMapping("/modifyTaskDetailByTaskNo")
	@ResponseBody
	public ResponseEntity<CollaboTaskVO> modifyTaskDetailByTaskNo(@RequestPart(value="files",required = false)
			List<MultipartFile> files,HttpServletRequest request ,HttpServletResponse response, CollaboTaskVO collaboTaskVO)throws Exception{
		
		ResponseEntity<CollaboTaskVO> entity = null;
		LOGGER.debug("[요청받음] => /modifyTaskDetailByTaskNo");
		
		try {
			collaboUtil.getSessionId(request, collaboTaskVO);
			collaboTaskService.modifyTaskDetailByTaskNo(collaboTaskVO);
			documentUtil.collaboTaskUpload(files, request, response, collaboTaskVO);
			
			entity = new ResponseEntity<CollaboTaskVO>(collaboTaskVO,HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboTaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/modifyTaskDetailByTaskNo에서 에러가 발생했습니다.",e); 
		}
		
		return entity;
		
	}
	
	/**
	 * 콜라보 업무 등록 메서드
	 * @param cprojNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskRegistInfoByCprojNo")
	@ResponseBody
	public ResponseEntity<CollaboTaskVO> getTaskRegistInfo(String cprojNo)throws Exception{
		ResponseEntity<CollaboTaskVO> entity = null;
		LOGGER.debug("[요청받음] => /getTaskRegistInfoByCprojNo");
		CollaboTaskVO collaboTaskVO = new CollaboTaskVO();
		
		try {
			String cprojTitle = collaboUtil.getCollaboNameByCprojNo(cprojNo);
			List<UserVO> userList = collaboUtil.getProjectMemberByCrojNo(cprojNo);
			collaboTaskVO.setCprojTitle(cprojTitle);
			collaboTaskVO.setUserList(userList);
			
			entity = new ResponseEntity<CollaboTaskVO>(collaboTaskVO,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboTaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/getTaskRegistInfoByCprojNo에서 에러가 발생했습니다.",e); 
		}
		
		return entity;
	}
	
	/**
	 * 콜라보 업무 등록 메서드
	 * @param files
	 * @param request
	 * @param response
	 * @param collaboTaskVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/registTask")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> registTask(@RequestPart(value="files",required = false) List<MultipartFile> files,
			HttpServletRequest request, HttpServletResponse response, CollaboTaskVO collaboTaskVO)throws Exception{
		ResponseEntity<HashMap<String, Object>> entity = null;
		LOGGER.debug("[요청받음] => /registTask");
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();	
		
		try {
			collaboUtil.getSessionId(request, collaboTaskVO);
			String taskNo = collaboTaskService.registTask(collaboTaskVO);
			documentUtil.collaboTaskUpload(files, request, response, collaboTaskVO);
			hashMap.put("taskNo", taskNo);
			
			entity = new ResponseEntity<HashMap<String, Object>>(hashMap,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<HashMap<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/getTaskRegistInfoByCprojNo에서 에러가 발생했습니다.",e); 
		}
		return entity;
	}
	
	@RequestMapping("/taskDownload")
	public String taskDownload(String path, Model model) throws Exception {

		LOGGER.debug("[요청받음] => /taskDownload(콜라보) => " + path);

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
	public ResponseEntity<CollaboTaskVO> taskDocumentRead(CollaboTaskVO collaboTaskVO)throws Exception{
		ResponseEntity<CollaboTaskVO> entity = null;
		CollaboTaskVO result = new CollaboTaskVO();
		LOGGER.debug("[요청받음] => /taskDocumentRead(콜라보)");
		
		try {
			result = documentUtil.readTaskDocByTaskTitleANDcprojNo(collaboTaskVO);
			
			entity = new ResponseEntity<CollaboTaskVO>(collaboTaskVO, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<CollaboTaskVO>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/taskDocumentRead에서 에러가 발생했습니다.",e); 
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
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/taskDocumentRemove에서 에러가 발생했습니다.",e); 
		}
		return entity;
	}
	
	/**
	 * 콜라보 번호로 간트에서 업무 리스트 출력
	 * @param cprojNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTaskListForGanttByCprojNo")
	@ResponseBody
	public ResponseEntity<List<CollaboTaskVO>> getTaskListForGanttByCprojNo(@RequestParam(defaultValue = "") String cprojNo)
			throws Exception{
		ResponseEntity<List<CollaboTaskVO>> entity = null;
		
		LOGGER.debug("[요청받음] => /getTaskListForGanttByProjNo");
		
		List<CollaboTaskVO> collaboVOList = new ArrayList<CollaboTaskVO>(); 
		
		try {
			collaboVOList = collaboTaskService.getTaskListByCprojNo(cprojNo);
			entity = new ResponseEntity<List<CollaboTaskVO>>(collaboVOList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<CollaboTaskVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); 
			LOGGER.error("/getTaskListForGanttByProjNo에서 에러가 발생했습니다.",e); 
		}
		
		return entity;
	}
	
}









