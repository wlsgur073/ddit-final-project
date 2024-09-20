package com.probada.collabo.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.command.CollaboCommand;
import com.probada.collabo.vo.CollaboVO;
import com.probada.mail.vo.MailVO;
import com.probada.project.vo.ProjectVO;
import com.probada.user.vo.UserVO;

public interface CollaboDAO {
	
	public List<CollaboVO> selectCollaboList() throws SQLException;
	
	public List<CollaboVO> selectCollaboListByUserId(String userId) throws SQLException;
	
	public List<CollaboVO> selectCollaboSubProj(String cprojNo)throws SQLException;
	
	public int selectCollaboSeqNext()throws SQLException;
	
	public void insertCollaboUserRelation(CollaboVO collaboVO)throws SQLException;
	
	//콜라보 상세에서 사용되는 유저리스트
	public List<UserVO> selectUserByCprojNo(String cprojNo) throws SQLException;
	
	//콜라보 갯수 
	public int collaboCount(String userId)throws SQLException;
	
	//유저의 콜라보 개수 
	public int selectCprojectCountInCprojByUserId(String userId) throws SQLException;
	
	public CollaboVO selectCollaboByCprojNo(String cprojNo) throws SQLException;

	public void insertCollabo(CollaboVO collaboVO)throws SQLException;
	
	public void updateCollaboDetail(CollaboVO collaboVO) throws SQLException;

	public void updateCollaboNotice(CollaboVO collaboVO) throws SQLException;
	
	//collabo-none 모달창에서 회원의 프로젝트 불러오기
	public List<CollaboVO> selectProjectTitleCollabo(String userId) throws SQLException;
	
	//제안 메일 발송 
	public void registInviteCollaboMail(CollaboCommand cmd) throws SQLException;
	
	//메일 번호 시퀀스
	public int selectCollaboMailSequenceNextValue()throws SQLException;
	
	public String selectCprojectNameByCprojNo(String cprojNo) throws SQLException;
	
	//칼럼이 여러개니까 List로 받는다.
	public List<CollaboVO> selectCprojectNameByProjNo(String projNo)throws SQLException;
	
	//유저 역할
	public String selectUserRole(CollaboVO collaboVO) throws SQLException;

	public void updateUserRole(CollaboVO collaboVO) throws SQLException;


}
