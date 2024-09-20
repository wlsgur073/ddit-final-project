package com.probada.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.probada.collabo.service.CollaboIssueService;
import com.probada.collabo.service.CollaboService;
import com.probada.collabo.service.CollaboTaskService;
import com.probada.collabo.vo.CollaboIssueVO;
import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.collabo.vo.CollaboVO;
import com.probada.issue.vo.MileIssueVO;
import com.probada.project.service.ProjectTagService;
import com.probada.project.vo.ProjectTagVO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.service.UserService;
import com.probada.user.vo.UserVO;

public class CollaboUtil {
	@Resource(name="collaboService")
	CollaboService collaboService;
	
	@Resource(name="collaboTaskService")
	CollaboTaskService collaboTaskService;
	
	@Resource(name="userService")
	UserService userService;
	
	@Resource(name="collaboIssueService")
	CollaboIssueService collaboIssueService;
	
	@Resource(name="collaboUtil")
	CollaboUtil collaboUtil;
	
	@Resource(name="projectTagService")
	ProjectTagService projectTagService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CollaboUtil.class);
	
	/**
	 * 콜라보 프로젝트 관계성 추가 메서드
	 * @param session
	 * @param collaboVO
	 * @throws Exception
	 */
	public void setCollaboUserRelation(CollaboVO collaboVO, String cprojNo, HttpServletRequest request)throws Exception{
		
		LOGGER.debug("[요청받음] /setCollaboUserRelation => " + collaboVO);

		CollaboVO userTo = new CollaboVO();
		CollaboVO userFrom = new CollaboVO();
		
		userTo.setUserId(collaboVO.getUserTo());
		userTo.setProjNo(collaboVO.getUserFromProjNo());
		userTo.setCprojNo(cprojNo);
		LOGGER.debug("userTo ================> " + userTo.toString());
		collaboService.registProjectUserRelation(userTo);
		
		userFrom.setUserId(collaboVO.getUserFrom());
		userFrom.setProjNo(collaboVO.getUserToProjNo());
		userFrom.setCprojNo(cprojNo);
		LOGGER.debug("userTo ================> " + userFrom);
		collaboService.registProjectUserRelation(userFrom);
		
	}
	
	
	/**
	 * 콜라보 번호로 콜라보 이름 조회
	 * @param cprojNo
	 * @return
	 * @throws SQLException
	 */
	public String getCollaboNameByCprojNo(String cprojNo)throws SQLException{
		String title = "";
		
		try {
			title = collaboService.getCprojectNameByCprojNo(cprojNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return title;
	}
	
	/**
	 * 프로젝트 번호로 상위 콜라보프로젝트 이름 출력
	 * @param projNo
	 * @return
	 * @throws SQLException
	 */
	public List<CollaboVO> getCprojectNameByProjNo(String projNo)throws SQLException{
		
		List<CollaboVO> cProjTitle = new ArrayList<CollaboVO>();
		try {
			cProjTitle = collaboService.getCprojectNameByProjNo(projNo);
			LOGGER.debug("[요청받음] /getCprojectNameByProjNo => " + cProjTitle);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return cProjTitle;
	}
	
	/**
	 * 콜라보 프로젝트 번호로 해당 멤버 조회
	 * @param projNo
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getProjectMemberByCrojNo(String cprojNo) throws SQLException{
		
		List<UserVO> memberList = new ArrayList<UserVO>();
		
		try {
			memberList = collaboService.getUserByCProjNo(cprojNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return memberList;
	}
	
	public List<CollaboVO> getCollaboMemberList(List<CollaboVO> collaboList)throws SQLException{
		
		List<UserVO> userList = new ArrayList<UserVO>();
		
		try {
			for (CollaboVO collaboVO : collaboList) {
				userList = userService.getUserByCprojNo(collaboVO.getCprojNo());
				if (userList != null) {
					userList = getCprojectCountUtil(userList);
					userList = collaboUtil.getTaskCountUtil(userList);
					collaboVO.setMember(userList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return collaboList;
		
	}
	
	
	
	public CollaboTaskVO getSessionId(HttpServletRequest request,CollaboTaskVO collaboTaskVO) throws SQLException {
		
		try {

			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("userVO");
			String sessionId = userVO.getNickname();
			collaboTaskVO.setSessionId(sessionId);
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return collaboTaskVO;
	}
	
	/**
	 * 콜라보 업무 개수 조회
	 * @param userList
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getTaskCountUtil(List<UserVO> userList) throws SQLException {
		
		try {
			
			for (UserVO userVO : userList) {
				int taskCount = collaboTaskService.getTaskCountInCprojByUserId(userVO);
				userVO.setTaskCount(taskCount);
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userList;
	}
	
	
	/**
	 * 유저에 해당하는 콜라보 개수 조회
	 * @param userList
	 * @return
	 * @throws SQLException
	 */
	public List<UserVO> getCprojectCountUtil(List<UserVO> userList) throws SQLException {

		try {
			
			for (UserVO userVO : userList) {
				int collaboCount = collaboService.getCprojectCountInCprojByUserId(userVO.getUserId());
				userVO.setCollaboCount(collaboCount);
			}

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}

		return userList;
	}
	
	public List<CollaboIssueVO> getMileListByIssueList(List<CollaboIssueVO> issueList) throws SQLException{
		
		List<MileIssueVO> mileIssueList = new ArrayList<MileIssueVO>();
		List<String> mileNoList = new ArrayList<String>();
		
		try {
			for (CollaboIssueVO collaboIssueVO: issueList) {
				mileIssueList = collaboIssueService.selectMileIssueListByIssueNo(collaboIssueVO.getIssueNo());
				
				for (MileIssueVO mileIssueVO : mileIssueList) {
					if (mileIssueVO.getIssueNo().equals(collaboIssueVO.getIssueNo())) {
						mileNoList.add(mileIssueVO.getMileNo());
					}
				}
				collaboIssueVO.setMileNo(mileNoList);
				mileNoList = new ArrayList<String>();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return issueList;
		
	}
	
	public CollaboIssueVO getMileListByIssueNo(CollaboIssueVO collaboIssueVO) throws SQLException{
		List<MileIssueVO> mileIssueList = new ArrayList<MileIssueVO>();
		List<String> mileNoList = new ArrayList<String>();
		
		try {
			mileIssueList = collaboIssueService.selectMileIssueListByIssueNo(collaboIssueVO.getIssueNo());
			if (mileIssueList != null) {
				for (MileIssueVO mileIssueVO : mileIssueList) {
					if(mileIssueVO.getIssueNo().equals(collaboIssueVO.getIssueNo())) {
						mileNoList.add(mileIssueVO.getMileNo());
					}
				}
				collaboIssueVO.setMileNo(mileNoList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return collaboIssueVO;
	}
	

}
