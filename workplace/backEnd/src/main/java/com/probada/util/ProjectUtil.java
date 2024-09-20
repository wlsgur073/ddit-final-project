package com.probada.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.project.service.ProjectService;
import com.probada.project.service.ProjectTagService;
import com.probada.project.vo.ProjectTagVO;
import com.probada.project.vo.ProjectVO;
import com.probada.project.web.ProjectController;
import com.probada.user.service.UserService;
import com.probada.user.vo.UserVO;

public class ProjectUtil {

	@Resource(name="projectService")
	ProjectService projectService;
	@Resource(name="projectTagService")
	ProjectTagService projectTagService;
	@Resource(name="userService")
	UserService userService;
	@Resource(name="taskUtil")
	TaskUtil taskUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectUtil.class);

	/**
	 * 프로젝트 개수 조회
	 * @param userList
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getProjectCountUtil(List<UserVO> userList) throws SQLException {

		try {

			for (UserVO userVO : userList) {
				int projectCount = projectService.getProjectCountInProjByUserId(userVO.getUserId());
				userVO.setProjectCount(projectCount);
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userList;
	}

	/**
	 * 프로젝트들의 태그리스트를 조회
	 * @param projectList
	 * @return
	 * @throws SQLException
	 */
	public List<ProjectVO> getProjectTagList(List<ProjectVO> projectList) throws SQLException{

		List<String> tagList = new ArrayList<String>();
		List<String> tagNoList = new ArrayList<String>();

		try {
			for (ProjectVO projectVO : projectList) {
				List<ProjectTagVO> projectTagList = projectTagService.getTagNameList(projectVO.getProjNo());

				for (ProjectTagVO projectTagVO : projectTagList) {
					if(projectTagVO.getProjNo().equals(projectVO.getProjNo())) {
						tagList.add(projectTagVO.getTagName());
						tagNoList.add(projectTagVO.getTagNo());
					}
				}
				projectVO.setTagNames(tagList);
				projectVO.setTagNo(tagNoList);
				tagList = new ArrayList<String>();
				tagNoList = new ArrayList<String>();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return projectList;
	}

	/**
	 * 프로젝트 단일의 태그리스트를 조회
	 * @param projNo
	 * @param projectVO
	 * @return
	 * @throws SQLException
	 */
	public ProjectVO getProjectTagListByProjNo(String projNo, ProjectVO projectVO) throws SQLException{

		List<ProjectTagVO> projectTagList = new ArrayList<ProjectTagVO>();
		List<String> tagList = new ArrayList<String>();
		try {
			projectTagList = projectTagService.getTagNameList(projNo);
			if(projectTagList != null) {
				for (ProjectTagVO projectTagVO : projectTagList) {
					tagList.add(projectTagVO.getTagName());
				}

				projectVO.setTagNames(tagList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return projectVO;
	}

	/**
	 * 프로젝트들의 프로젝트 멤버 조회
	 * @param projectList
	 * @return
	 * @throws SQLException
	 */
	public List<ProjectVO> getProjectMemberList(List<ProjectVO> projectList) throws SQLException{

		List<UserVO> userList = new ArrayList<UserVO>();
		try {
			
			for (ProjectVO projectVO : projectList) {
				userList = userService.getUserByProjNo(projectVO.getProjNo());
				if(userList != null) {
					userList = getProjectCountUtil(userList);
					userList = taskUtil.getTaskCountUtil(userList);
					projectVO.setMember(userList);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return projectList;
	}

	/**
	 * 프로젝트 번호로 프로젝트 멤버 조회
	 * @param projNo
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getProjectMemberByProjNo(String projNo) throws SQLException{

		List<UserVO> userList = new ArrayList<UserVO>();
		try {

				userList = userService.getUserByProjNo(projNo);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userList;
	}

	/**
	 * 프로젝트와 유저(세션접속중)의 관계성 추가
	 * @param session
	 * @param projectVO
	 * @throws SQLException
	 */
	public void setProjectLeaderRelation(HttpSession session, ProjectVO projectVO) throws SQLException{

		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String userId = userVO.getUserId();

		projectVO.setUserId(userId);
		projectVO.setRole("A303");

		projectService.registProjectUserRelation(projectVO);

	}

	/**
	 * 프로젝트 번호로 프로젝트 이름 조회
	 * @param projNo
	 * @return
	 * @throws SQLException
	 */
	public String getProjectNameByProjNo(String projNo) throws SQLException{

		String title = "";

		try {
			title = projectService.getProjectNameByProjNo(projNo);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return title;
	}

	public List<ProjectTagVO> getProjectListByTagNo(String tagNo) {
		List<ProjectTagVO> projectList = new ArrayList<>();
		try {
			projectList = projectTagService.getProjectListByTagNo(tagNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}

	public void registProjectTagResolver(ProjectVO projectVO) throws SQLException {
		ProjectTagVO tagVO = new ProjectTagVO();

		try {
			if(projectVO.getTagNames() != null) {

				String[] tagNameArr = String.valueOf(projectVO.getTagNames()).split(",");

				for (int i = 0; i < tagNameArr.length; i++) {

					tagVO.setTagName("#"+tagNameArr[i]);
					tagVO.setProjNo(projectVO.getProjNo());

					int count = projectTagService.getCountProjectTagByTagName(tagVO);
					if(count == 0) {
						projectTagService.registProjectTagByProjNo(tagVO);
					}
					String tagNo = projectTagService.getTagNoByTagName(tagVO);
					tagVO.setTagNo(tagNo);
					projectTagService.registProjectTagRelation(tagVO);
				}

			} else {

				return;

			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
