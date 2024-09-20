package com.probada.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.probada.task.service.TaskService;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserVO;

public class TaskUtil {


	@Resource(name="taskService")
	private TaskService taskService;

	/**
	 * 업무 개수 조회
	 * @param userList
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getTaskCountUtil(List<UserVO> userList) throws SQLException {

		try {
			
			for (UserVO userVO : userList) {
				int taskCount = taskService.getTaskCountInProjByUserId(userVO);
				userVO.setTaskCount(taskCount);
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userList;
	}



	/**
	 * PROJ_NO와 IMPORTANT, STATUS를 이름 형식으로 format하여 List<TaskVO>를 반환한다.
	 * @param String userId
	 * @return List<TaskVO>
	 * @throws SQLException
	 */
	public List<TaskVO> getFormatTaskListByUserId(String userId) throws SQLException {

		List<TaskVO> formatTaskList = new ArrayList<TaskVO>();

		formatTaskList = taskService.getFormatTaskListByUserId(userId);

		return formatTaskList;
	}

	public TaskVO getSessionId(HttpServletRequest request, TaskVO taskVO) throws SQLException {
		
		try {

			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String sessionId = userVO.getNickname();
			taskVO.setSessionId(sessionId);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return taskVO;
	}

}
