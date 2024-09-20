package com.probada.user.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.probada.collabo.vo.CollaboVO;
import com.probada.project.vo.ProjectVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserTotalCountVO;
import com.probada.user.vo.UserVO;

public class UserDAOImpl implements UserDAO{
// 	현재 파타미터의 email하고 userId는 동일합니다.

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public UserVO selectUserById(String userId) throws SQLException {
		UserVO user = null;
		user = sqlSession.selectOne("User-Mapper.selectUserById",userId);
		return user;
	}

	@Override
	public void insertUser(UserVO user) throws SQLException {
		sqlSession.update("User-Mapper.insertUser",user);
	}

	@Override
	public void updateUser(UserVO user) throws SQLException {
		sqlSession.update("User-Mapper.updateUser",user);
	}

	@Override
	public UserVO login(UserVO user) throws SQLException {
		UserVO userVO = sqlSession.selectOne("User-Mapper.login", user);
		return userVO;
	}

	@Override
	public int createAuthkey(Map<String, String> paramMap) throws SQLException {
		int ret = 0;
		ret = sqlSession.update("User-Mapper.createAuthkey", paramMap);
		return ret;
	}

	@Override
	public void updateAuthstatus(String userId) throws SQLException {
		sqlSession.update("User-Mapper.updateAuthstatus", userId);
	}


	@Override
	public String idCheck(String email) throws SQLException {
		return sqlSession.selectOne("User-Mapper.idCheck", email);
	}

	@Override
	public String nicknameCheck(String username) throws SQLException {
		return sqlSession.selectOne("User-Mapper.nicknameCheck", username);
	}

	@Override
	public int isAccount(String email) throws SQLException {
		return sqlSession.selectOne("User-Mapper.isAccount", email);
	}

	@Override
	public int pwdCheck(UserVO user) throws SQLException {
		
		return sqlSession.selectOne("User-Mapper.pwdCheck", user);
	}


	@Override
	public void setUserUploadUsage(UserVO userVO) throws SQLException {
		sqlSession.update("User-Mapper.setUserUploadUsage", userVO);
	}

	@Override
	public List<UserVO> selectUserByProjNo(String projNo) throws SQLException {

		List<UserVO> userListForProjDetail = sqlSession.selectList("User-Mapper.selectUserByProjNo", projNo);

		return userListForProjDetail;
	}

	@Override
	public String pwdPiker(String userId) throws SQLException {
		
		return sqlSession.selectOne("User-Mapper.pwdPiker", userId);
	}

	@Override
	public void deleteUser(String userId) throws SQLException {
		sqlSession.update("User-Mapper.deleteUser", userId);
		
	}

	@Override
	public List<String> getUserProjNoList(String userId) throws SQLException {
		return sqlSession.selectList("User-Mapper.getUserProjNoList", userId);
	}

	@Override
	public String pwdPicker(String userId) throws SQLException {
		return sqlSession.selectOne("User-Mapper.pwdPicker", userId);
	}
	
	@Override
	public String selectAuthkey(String userId) throws SQLException {
		return sqlSession.selectOne("User-Mapper.selectAuthkey", userId);
	}

	@Override
	public void setUserPwd(UserVO userVO) throws SQLException {
		sqlSession.update("User-Mapper.setUserPwd", userVO);
	}

	@Override
	public void registExternalLogin(UserVO userVO) throws SQLException {
		sqlSession.update("User-Mapper.insertUser", userVO);
		sqlSession.update("User-Mapper.updateAuthstatus", userVO.getUserId());
	}
	
//	유저의 총량을 반환하는 메서드
	@Override
	public UserTotalCountVO setUserTotalCount(UserTotalCountVO userVO) {
		int taskTotalCount = (Integer) sqlSession.selectOne("User-Mapper.taskTotalCount", userVO.getUserId());
		int issueTotalCount = (Integer) sqlSession.selectOne("User-Mapper.issueTotalCount", userVO.getUserId());
		int mailTotalCount = (Integer) sqlSession.selectOne("User-Mapper.mailTotalCount", userVO.getUserId());
		int requestTotalCount = (Integer) sqlSession.selectOne("User-Mapper.requestTotalCount", userVO.getUserId());
		int projectTotalCount = (Integer) sqlSession.selectOne("User-Mapper.projectTotalCount", userVO.getUserId());
		int collaboTotalCount = (Integer) sqlSession.selectOne("User-Mapper.collaboTotalCount", userVO.getUserId());
		
		userVO.setTaskCount(taskTotalCount);
		userVO.setIssueCount(issueTotalCount);
		userVO.setMailCount(mailTotalCount);
		userVO.setRequestCount(requestTotalCount);
		userVO.setProjectCount(projectTotalCount);
		userVO.setCollaboCount(collaboTotalCount);
		
		return userVO;
	}

	@Override
	public List<TaskVO> getUserTaskList(String userId) throws SQLException {
		return sqlSession.selectList("Task-Mapper.selectTaskListByUserId", userId);
	}

	@Override
	public List<ProjectVO> getUserProjectList(String userId) throws SQLException {
		return sqlSession.selectList("Project-Mapper.selectProjectListByUserId", userId);
	}

	
	@Override
	public List<UserVO> selectTotalUserByPrivacy() throws SQLException {
		
		return sqlSession.selectList("User-Mapper.selectTotalUserByPrivacy");
	}

	@Override
	public List<CollaboVO> selectCollaboListByUserId(String userId) throws SQLException {
		return sqlSession.selectList("Collabo-Mapper.selectCollaboListByUserId", userId);
	}

	@Override
	public List<UserVO> getMemberAchievementList(String projNo) throws SQLException {
		return sqlSession.selectList("User-Mapper.getMemberAchievementList", projNo);
	}

	@Override
	public UserVO getUserByNickname(String nickname) {
		return sqlSession.selectOne("User-Mapper.getUserByNickname", nickname);
	}

	@Override
	public List<UserVO> selectUserByCprojNo(String cprojNo) throws SQLException {
		
		List<UserVO> userListForCprojDetail = sqlSession.selectList("User-Mapper.selectUserByCprojNo", cprojNo);

		return userListForCprojDetail;
	}

	@Override
	public List<UserVO> getCMemberAchievementList(String cprojNo) throws SQLException {
		
		List<UserVO> userListForCprojDetail = sqlSession.selectList("User-Mapper.getCporjMemberAchievementList", cprojNo);
		
		return userListForCprojDetail;
	}

}
