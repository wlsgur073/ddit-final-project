package com.probada.user.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.probada.collabo.vo.CollaboVO;
import com.probada.project.vo.ProjectVO;
import com.probada.task.vo.TaskVO;
import com.probada.user.vo.UserTotalCountVO;
import com.probada.user.vo.UserVO;

public interface UserDAO {

	// 로그인
	public UserVO login(UserVO user) throws SQLException;

	/* 해당 멤버 조회 */
	public UserVO selectUserById(String userId) throws SQLException;

	/* 유저 등록 */
	public void insertUser(UserVO user) throws SQLException;

	// 유저 업데이트 동적 커리문으로 각각 다 바꿀 수 있음
	public void updateUser(UserVO user) throws SQLException;

	// 이메일 인증 후 authstatus 1로 변경
	public void updateAuthstatus(String email) throws SQLException;

	// 디비에 authkey저장
	public int createAuthkey(Map<String, String> paramMap) throws SQLException;

	//	DB 아이디 체크
	public String idCheck(String email) throws SQLException;

	//	DB 닉네임 체크
	public String nicknameCheck(String username) throws SQLException;

	//	 리스트에서 해당 계정 체크
	public int isAccount(String email) throws SQLException;
	
	// 권한이 있는 사람 
	public List<UserVO> selectTotalUserByPrivacy() throws SQLException;

	
	
	
	//seok 비밀번호 체크
	public int pwdCheck(UserVO user)throws SQLException;
	
	public String pwdPiker(String userId) throws SQLException;
	

	public void deleteUser(String userId) throws SQLException;
	
	//	유저 데이터 사용량 업데이트
	public void setUserUploadUsage(UserVO userVO) throws SQLException;

	//  프로젝트 상세에서 사용되는 유저리스트
	public List<UserVO> selectUserByProjNo(String projNo) throws SQLException;

	// USER_PROJECT 테이블에서 유저 아이디를 조회하여 PROJ_NO를 조회한다.
	public List<String> getUserProjNoList(String userId) throws SQLException;
	
	// 콜라보 프로젝트 멤버리스트
	public List<UserVO> selectUserByCprojNo(String cprojNo) throws SQLException;
	
	
	// userId를 파라미터로 받아서 해당 유저의 pwd를 리턴한다.
	public String pwdPicker(String userId) throws SQLException;
	
	// 해당 유저 아이디를 참조하여 유저의 authkey를 조회한다.
	public String selectAuthkey(String userId) throws SQLException;
	
	// 유저의 비밀번호를 재설정한다.
	public void setUserPwd(UserVO userVO) throws SQLException;
	
	// 외부 로그인을 회원가입을 승인하는 메서드
	public void registExternalLogin(UserVO userVO) throws SQLException;
	
	//	유저의 총량을 반환하는 메서드
	public UserTotalCountVO setUserTotalCount(UserTotalCountVO userVO) throws SQLException;
	
	// 유저의 task리스트를 조회하는 메서드
	public List<TaskVO> getUserTaskList(String userId) throws SQLException;
	
	// 유저의 project리스트를 조회하는 메서드
	public List<ProjectVO> getUserProjectList(String userId) throws SQLException;
	
	// 유저 아이디를 참조하여 콜라보 리스트를 리턴한다.
	public List<CollaboVO> selectCollaboListByUserId(String userId) throws SQLException;
	
	// 프로젝트 넘버를 참조하여 프로젝트 대시보드에 사용할 진척도 그래프 리스트를 리턴한다.
	public List<UserVO> getMemberAchievementList(String projNo) throws SQLException;
	
	// 콜라보 프로젝트 넘버를 참조하여 프로젝트 대시보드에 사용할 진척도 그래프 리스트를 리턴한다.
	public List<UserVO> getCMemberAchievementList(String cprojNo) throws SQLException;

	// 닉네임을 참조하여 UserVO를 리턴한다.
	public UserVO getUserByNickname(String nickname);
	
}
