package com.probada.collabo.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.command.CollaboCommand;
import com.probada.collabo.vo.CollaboVO;
import com.probada.mail.vo.MailVO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;

public interface CollaboService {
	
	public List<CollaboVO> getCollaboList() throws SQLException;

	public List<CollaboVO> getCollaboListByUserId(String userId) throws SQLException;
	
	public List<CollaboVO> getCollaboSubProj(String cprojNo) throws SQLException;
	
	public CollaboVO getCollaboByCprojNo(String cprojNo) throws SQLException;

	public int getCollaboCount(String userId)throws SQLException;
	
	//유저 관련 콜라보 개수
	public int getCprojectCountInCprojByUserId(String userId) throws SQLException;
	
	// 프로젝트 상세에서 사용되는 유저리스트
	public List<UserVO> getUserByCProjNo(String cprojNo) throws SQLException;
	
	public String registCollabo(CollaboVO collaboVO)throws SQLException;
	
	public void registProjectUserRelation(CollaboVO collaboVO) throws SQLException; 
	
	public void modifyCollaboDetail(CollaboVO collaboVO) throws SQLException;

	public void modifyCollaboNotice(CollaboVO collaboVO) throws SQLException;
	
	//collabo-none 모달창에서 회원의 프로젝트 불러오기
	public List<CollaboVO> getProjectTitleCollabo(String userId) throws SQLException;
	
	//콜라보 제안 메일 발송
	public void sendInviteCollaboMail(CollaboCommand cmd) throws SQLException;
	
	public String getCprojectNameByCprojNo(String cprojNo) throws SQLException;
	
	public List<CollaboVO> getCprojectNameByProjNo(String projNo)throws SQLException;
	
	//유저 역할 관련
	public String getUserRole(CollaboVO collaboVO) throws SQLException;

	public void modifyUserRole(CollaboVO collaboVO) throws SQLException;

}
