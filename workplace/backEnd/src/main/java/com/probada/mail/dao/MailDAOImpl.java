package com.probada.mail.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.probada.mail.vo.AttachVO;
import com.probada.mail.vo.MailVO;

@Repository
public class MailDAOImpl implements MailDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//받은메일 리스트 조회
	@Override
	public List<MailVO> selectReceiveMailList(String userTo) throws SQLException {
		List<MailVO> receiveMailList = sqlSession.selectList("Mail-Mapper.selectReceiveMailList", userTo);
		return receiveMailList;
	}
	
	//보낸메일 리스트 조회
	@Override
	public List<MailVO> selectSendMailList(String userFrom) throws SQLException {
		List<MailVO> sendMailList = sqlSession.selectList("Mail-Mapper.selectSendMailList", userFrom);
		return sendMailList;
	}
	
	//임시메일 리스트 조회
	@Override
	public List<MailVO> selectTempMailList(String userFrom) throws SQLException {
		List<MailVO> tempMailList = sqlSession.selectList("Mail-Mapper.selectTempMailList", userFrom);
		return tempMailList;
	}
	
	//휴지통 리스트 조회
	@Override
	public List<MailVO> selectTrashMailList(String userId) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userTo", userId);
		dataMap.put("userFrom", userId);
		
		List<MailVO> trashMailList = sqlSession.selectList("Mail-Mapper.selectTrashMailList", dataMap);
		return trashMailList;
	}
	
	//메일 상세 조회
	@Override
	public MailVO selectMailByMailNo(int mailNo) throws SQLException {
		MailVO mailDetail = sqlSession.selectOne("Mail-Mapper.selectMailDetail", mailNo);
		return mailDetail;
	}
	
	//메일 첨부파일 조회
	@Override
	public List<AttachVO> selectMailAttachList(int mailNo) throws SQLException {
		List<AttachVO> attachList = sqlSession.selectList("Mail-Mapper.selectMailAttachList", mailNo);
		return attachList;
	}
	
	//메일 상태 변경(읽음, 안읽음)
	@Override
	public void changeMailStatus(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.changeMailStatus", mailNo);
	}
	
	//받은메일 복구
	@Override
	public void returnReceiveMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.returnReceiveMail", mailNo);
	}

	//보낸메일 복구
	@Override
	public void returnSendMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.returnSendMail", mailNo);
	}
	
	//내게 쓴 메일 복구
	@Override
	public void returnMineMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.returnMineMail", mailNo);
	}
	
	//받은메일 삭제(휴지통으로)
	@Override
	public void deleteReceiveMailToTrash(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteReceiveMailToTrash", mailNo);
	}
	
	//보낸메일 삭제(휴지통으로)
	@Override
	public void deleteSendMailToTrash(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteSendMailToTrash", mailNo);
	}
	
	//내게 쓴 메일 삭제(휴지통으로)
	@Override
	public void deleteMineMailToTrash(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteMineMailToTrash", mailNo);
	}
	
	//임시메일 삭제(완전 삭제)
	@Override
	public void deleteTempMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteTempMail", mailNo);
	}
	
	//휴지통 삭제(받은 메일)
	@Override
	public void deleteTrashReceiveMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteTrashReceiveMail", mailNo);
	}
	
	//휴지통 삭제(보낸 메일)
	@Override
	public void deleteTrashSendMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteTrashSendMail", mailNo);
	}
	
	//휴지통 삭제(보낸 메일)
	@Override
	public void deleteTrashMineMail(int mailNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteTrashMineMail", mailNo);
	}
	
	//메일 등록
	@Override
	public void registMail(MailVO mailVO) throws SQLException {
		System.out.println(mailVO.getUserFrom() + ", " + mailVO.getUserTo());
		sqlSession.update("Mail-Mapper.registMail", mailVO);
	}

	//임시메일 등록
	@Override
	public void registTempMail(MailVO mailVO) throws SQLException {
		sqlSession.update("Mail-Mapper.registTempMail", mailVO);
	}
	
	//메일 첨부파일 등록
	@Override
	public void registAttachFile(AttachVO attachVO) throws SQLException {
		sqlSession.update("Mail-Mapper.registAttachFile", attachVO);
	}
	
	//메일번호 시퀀스
	@Override
	public int selectMailSequenceNextValue() throws SQLException {
		int mailNo = sqlSession.selectOne("Mail-Mapper.selectMailSequenceNextValue");
		return mailNo;
	}
	
	//첨부파일 조회
	@Override
	public AttachVO selectAttachByAttachNo(int attachNo) throws SQLException {
		AttachVO attachVO = sqlSession.selectOne("Mail-Mapper.selectAttachByAttachNo", attachNo);
		return attachVO;
	}
	
	//첨부파일 삭제
	@Override
	public void deleteAttachByAttachNo(int attachNo) throws SQLException {
		sqlSession.update("Mail-Mapper.deleteAttachByAttachNo", attachNo);	
	}
	
	//임시메일 전송
	@Override
	public void tempMailToSendMail(MailVO mailVO) throws SQLException {
		sqlSession.update("Mail-Mapper.tempMailToSendMail", mailVO);
		
	}
	
	//아이디를 닉네임으로 변경
	@Override
	public String selectNicknameByUserId(String userId) throws SQLException {
		String nickname = sqlSession.selectOne("Mail-Mapper.selectNicknameByUserId", userId);
		return nickname;
	}

	//닉네임을 아이디로 변경
	@Override
	public String selectUserIdByNickname(String userTo) throws SQLException {
		String userId = sqlSession.selectOne("Mail-Mapper.selectUserIdByNickname", userTo);
		return userId;
	}
}
