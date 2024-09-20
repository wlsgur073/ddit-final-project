package com.probada.mail.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.probada.mail.dao.MailDAO;
import com.probada.mail.utils.MakeFileName;
import com.probada.mail.vo.AttachVO;
import com.probada.mail.vo.MailVO;

@Service
public class MailServiceImpl implements MailService {
	
	private MailDAO mailDAO;
	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}
	
	//받은메일 리스트 조회
	@Override
	public List<MailVO> getReceiveMailList(String userTo) throws SQLException {
		List<MailVO> receiveMailList = mailDAO.selectReceiveMailList(userTo);
		for(MailVO mailVO : receiveMailList) {
			addAttachList(mailVO);	
		}
		return receiveMailList;
	}
	
	//보낸메일 리스트 조회
	@Override
	public List<MailVO> getSendMailList(String userFrom) throws SQLException {
		List<MailVO> sendMailList = mailDAO.selectSendMailList(userFrom);
		for(MailVO mailVO : sendMailList) {
			addAttachList(mailVO);	
		}
		return sendMailList;
	}
	
	//임시메일 리스트 조회
	@Override
	public List<MailVO> getTempMailList(String userFrom) throws SQLException {
		List<MailVO> tempMailList = mailDAO.selectTempMailList(userFrom);
		for(MailVO mailVO : tempMailList) {
			addAttachList(mailVO);	
		}
		return tempMailList;
	}
	
	//휴지통 리스트 조회
	@Override
	public List<MailVO> getTrashMailList(String userId) throws SQLException {
		List<MailVO> trashMailList = mailDAO.selectTrashMailList(userId);
		for(MailVO mailVO : trashMailList) {
			addAttachList(mailVO);	
		}
		return trashMailList;
	}
	
	//메일 상세 조회
	@Override
	public MailVO getMailByMailNo(int mailNo) throws SQLException {
		MailVO mailVO = mailDAO.selectMailByMailNo(mailNo);
		addAttachList(mailVO);
		return mailVO;
	}
	
	//받은메일 상세 조회
	@Override
	public MailVO getReceiveMailByMailNo(int mailNo) throws SQLException {
		mailDAO.changeMailStatus(mailNo);
		MailVO mailVO = mailDAO.selectMailByMailNo(mailNo);
		addAttachList(mailVO);
		return mailVO;
	}

	//첨부파일 리스트 조회
	private void addAttachList(MailVO mailVO) throws SQLException {
		if(mailVO == null) {
			return;
		}
		
		int mailNo = mailVO.getMailNo();
		List<AttachVO> attachList = null;
		if(mailNo != 0) {
			attachList = mailDAO.selectMailAttachList(mailNo);
			if(attachList != null) {
				attachList = MakeFileName.parseFileNameFromAttaches(attachList, "\\$\\$");
			}
		}
		mailVO.setAttachList(attachList);
	}
	
	//받은메일 복구
	@Override
	public void returnReceiveMail(int mailNo) throws SQLException {
		mailDAO.returnReceiveMail(mailNo);
	}
	
	//보낸메일 복구
	@Override
	public void returnSendMail(int mailNo) throws SQLException {
		mailDAO.returnSendMail(mailNo);
	}
	
	//내게 쓴메일 복구
	@Override
	public void returnMineMail(int mailNo) throws SQLException {
		mailDAO.returnMineMail(mailNo);
	}
	
	//받은메일 삭제(휴지통으로)
	@Override
	public void deleteReceiveMailToTrash(int mailNo) throws SQLException {
		mailDAO.deleteReceiveMailToTrash(mailNo);
	}

	//보낸메일 삭제(휴지통으로)
	@Override
	public void deleteSendMailToTrash(int mailNo) throws SQLException {
		mailDAO.deleteSendMailToTrash(mailNo);
	}
	
	//내게 쓴 메일 삭제(휴지통으로)	
	@Override
	public void deleteMineMailToTrash(int mailNo) throws SQLException {
		mailDAO.deleteMineMailToTrash(mailNo);
	}
	
	//임시메일 삭제(완전 삭제)
	@Override
	public void deleteTempMail(int mailNo) throws SQLException {
		mailDAO.deleteTempMail(mailNo);
	}
	
	//휴지통 삭제(받은 메일)
	@Override
	public void deleteTrashReceiveMail(int mailNo) throws SQLException {
		mailDAO.deleteTrashReceiveMail(mailNo);
	}
	
	//휴지통 삭제(보낸 메일)
	@Override
	public void deleteTrashSendMail(int mailNo) throws SQLException {
		mailDAO.deleteTrashSendMail(mailNo);
	}
	
	//휴지통 삭제(내게 쓴 메일)
	@Override
	public void deleteTrashMineMail(int mailNo) throws SQLException {
		mailDAO.deleteTrashMineMail(mailNo);
	}
	
	//메일 및 첨부파일 등록
	@Override
	public void registMailAttachFile(MailVO mailVO) throws SQLException {
		
		int mailNo = mailDAO.selectMailSequenceNextValue();
		
		mailVO.setMailNo(mailNo);
		
		mailDAO.registMail(mailVO);
		
		if(mailVO.getAttachList() != null) {
			for(AttachVO attachVO : mailVO.getAttachList()) {
				attachVO.setMailNo(mailNo);
				attachVO.setAttacher(mailVO.getUserFrom());
				mailDAO.registAttachFile(attachVO);
			}
		}
	}

	//임시메일 및 첨부파일 등록
	@Override
	public void registTempMailAttachFile(MailVO mailVO) throws SQLException {
		int mailNo = mailDAO.selectMailSequenceNextValue();
		mailVO.setMailNo(mailNo);
		
		mailDAO.registTempMail(mailVO);
		
		if(mailVO.getAttachList() != null) {
			for(AttachVO attachVO : mailVO.getAttachList()) {
				attachVO.setMailNo(mailNo);
				attachVO.setAttacher(mailVO.getUserFrom());
				mailDAO.registAttachFile(attachVO);
			}
		}
	}
	
	//첨부파일 조회
	@Override
	public AttachVO getAttachByAttachNo(int attachNo) throws SQLException {
		AttachVO attachVO = mailDAO.selectAttachByAttachNo(attachNo);
		return attachVO;
	}
	
	//첨부파일 삭제
	@Override
	public void deleteAttachByAttachNo(int attachNo) throws SQLException {
		mailDAO.deleteAttachByAttachNo(attachNo);
	}
	
	//임시메일 전송
	@Override
	public void tempMailToSendMail(MailVO mailVO) throws SQLException {
		mailDAO.tempMailToSendMail(mailVO);
		
		if(mailVO.getAttachList() != null) {
			for(AttachVO attachVO : mailVO.getAttachList()) {
				attachVO.setMailNo(mailVO.getMailNo());
				attachVO.setAttacher(mailVO.getUserFrom());
				mailDAO.registAttachFile(attachVO);
			}
		}
	}
	
	//아이디를 닉네임으로 변경
	@Override
	public String getNicknameByUserId(String userId) throws SQLException {
		String nickname = mailDAO.selectNicknameByUserId(userId);
		return nickname;
	}
	
	//닉네임을 아이디로 변경
	@Override
	public String getUserIdByNickname(String nickname) throws SQLException {
		String userId = mailDAO.selectUserIdByNickname(nickname);
		return userId;
	}
}
