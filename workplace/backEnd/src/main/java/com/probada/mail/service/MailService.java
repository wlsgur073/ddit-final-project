package com.probada.mail.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.mail.vo.AttachVO;
import com.probada.mail.vo.MailVO;

public interface MailService {
	
	//받은메일 리스트 조회
	public List<MailVO> getReceiveMailList(String userTo) throws SQLException;

	//보낸메일 리스트 조회
	public List<MailVO> getSendMailList(String userFrom) throws SQLException;
	
	//임시메일 리스트 조회
	public List<MailVO> getTempMailList(String userFrom) throws SQLException;
	
	//휴지통 리스트 조회
	public List<MailVO> getTrashMailList(String userId) throws SQLException;
	
	//메일 상세 조회
	public MailVO getMailByMailNo(int mailNo) throws SQLException;

	//메일 상세 조회
	public MailVO getReceiveMailByMailNo(int mailNo) throws SQLException;
	
	//받은메일 복구
	public void returnReceiveMail(int mailNo) throws SQLException;
	
	//보낸메일 복구
	public void returnSendMail(int mailNo) throws SQLException;
	
	//내게 쓴 메일 복구
	public void returnMineMail(int mailNo) throws SQLException;
	
	//받은메일 삭제(휴지통으로)
	public void deleteReceiveMailToTrash(int mailNo) throws SQLException;
	
	//보낸메일 삭제(휴지통으로)	
	public void deleteSendMailToTrash(int mailNo) throws SQLException;
	
	//내게 쓴 메일 삭제(휴지통으로)	
	public void deleteMineMailToTrash(int mailNo) throws SQLException;
	
	//임시메일 삭제(완전 삭제)
	public void deleteTempMail(int mailNo) throws SQLException;
	
	//휴지통 삭제(받은 메일)
	public void deleteTrashReceiveMail(int mailNo) throws SQLException;
	
	//휴지통 삭제(보낸 메일)
	public void deleteTrashSendMail(int mailNo) throws SQLException;
	
	//휴지통 삭제(내게 쓴 메일)
	public void deleteTrashMineMail(int mailNo) throws SQLException;
	
	//메일 및 첨부파일 등록
	public void registMailAttachFile(MailVO mailVO) throws SQLException;

	//임시메일 및 첨부파일 등록
	public void registTempMailAttachFile(MailVO mailVO) throws SQLException;
	
	//첨부파일 조회
	public AttachVO getAttachByAttachNo(int attachNo) throws SQLException;
	
	//첨부파일 삭제
	public void deleteAttachByAttachNo(int attachNo) throws SQLException;
	
	//임시메일 전송
	public void tempMailToSendMail(MailVO mailVO) throws SQLException;
	
	//아이디를 닉네임으로 변경
	public String getNicknameByUserId(String userId) throws SQLException;
	
	//닉네임을 아이디로 변경
	public String getUserIdByNickname(String nickname) throws SQLException;
}
