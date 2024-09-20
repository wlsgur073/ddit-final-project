package com.probada.document.dao;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.document.vo.FileVO;
import com.probada.document.vo.ProjectUserVO;
import com.probada.issue.vo.IssueVO;
import com.probada.task.vo.TaskVO;



public interface DocumentDAO {
	//내 대시보드 파일리스트 조회
	public List<FileVO> selectDocumentListByUserId(String userId) throws SQLException;

	//전체 file을 조회
	List<FileVO> selectDocList() throws SQLException;

	//파일 하나 조회
	FileVO selectDocumentByPath(String docId) throws SQLException;

	//콜라보 파일 등록
	public void insertDocumentCollabo(FileVO document) throws SQLException;

	//file insert
	public void insertDocument(FileVO document) throws SQLException;

	//file update
	public void updateDocument(FileVO document) throws SQLException;

	//file 삭제
	public void deleteDocument(String docId) throws SQLException;


	//아이디로 문서 조회
	public List<FileVO> selectDocumentByUserId(String userId) throws SQLException;

	//나의 프로젝트 중 디폴트폴더만 조회
	public List<FileVO> selectDefaultDirectoryMyProject(String userId)throws SQLException;

	//나의 프로젝트 중 내가 만든 폴더만 조회
	public List<FileVO> selectDirectoryMyProject(String userId)throws SQLException;


	public List<FileVO> selectDashDocumentByProjNo(String projNo) throws SQLException;

	//프로젝트 문서관리 파트 디폴트 폴더
	public List<FileVO> selectDocumentByDefaultProj(String projectNum) throws SQLException;

	//프로제트 문서관리 내가 만든 폴더 파일
	public List<FileVO> selectDocumentByMyProj(ProjectUserVO user) throws SQLException;

	public List<FileVO> selectDocumentListByProjNo(ProjectUserVO user) throws SQLException;

	public List<FileVO> selectDocumentListForProjDetail(String projNo) throws SQLException;

	public List<FileVO> selectDocumentListBytaskTitleANDprojNo(TaskVO taskVO) throws SQLException;

	public List<FileVO> selectDocumentListByIssueTitleAndProjNo(IssueVO issueVO) throws SQLException;

	//콜라보 업무 문서 리스트 출력
	public List<FileVO> selectDocumentListBytaskTitleANDcprojNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	//콜라보 문서관리
	public List<FileVO> selectDocumentListForCprojDetail(String cprojNo) throws SQLException;

	public List<FileVO> selectDocumentListByCprojNo(String cprojNo) throws SQLException;

	//시퀀스
	String selectDocumentSeqNext() throws SQLException;

	public String selectProjByTitle(String title) throws SQLException;
}
