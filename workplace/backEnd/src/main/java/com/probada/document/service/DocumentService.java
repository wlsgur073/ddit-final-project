package com.probada.document.service;

import java.sql.SQLException;
import java.util.List;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.document.vo.FileVO;
import com.probada.document.vo.ProjectUserVO;
import com.probada.issue.vo.IssueVO;
import com.probada.task.vo.TaskVO;



public interface DocumentService {

	public List<FileVO> getDocumentListByUserId(String userId) throws SQLException;

	public List<FileVO> getDocList() throws SQLException;

	public void registDocument(FileVO document) throws SQLException;

	//콜라보 파일 등록
	public void registDocumentCollabo(FileVO document) throws SQLException;

	public void modifyDocument(FileVO document) throws SQLException;

	public FileVO getDocOne(String docId) throws SQLException;


	public void removeDocument(String docId) throws SQLException;

	public String seqDoc() throws SQLException;


	public List<FileVO> getDashDocumentByProjNo(String projNo) throws SQLException;

	public List<FileVO> getMyDocument(String userId) throws SQLException;

	public List<FileVO> getProjectDocument(ProjectUserVO user) throws SQLException;
	
	public List<FileVO> getDocumentListByProjNo(ProjectUserVO user) throws SQLException;

	public List<FileVO> getDocumentListForProjDetail(String projNo) throws SQLException;

	public String getProjByTitle(String title) throws SQLException;

	public List<FileVO> getDocumentListBytaskTitleANDprojNo(TaskVO taskVO) throws SQLException;

	public List<FileVO> getDocumentListByIssueTitleAndProjNo(IssueVO issueVO) throws SQLException;

	public List<FileVO> getDocumentListBytaskTitleANDcprojNo(CollaboTaskVO collaboTaskVO) throws SQLException;
	
	//콜라보 문서관리
	public List<FileVO> getDocumentListForCprojDetail(String cprojNo) throws SQLException;
	
	public List<FileVO> getDocumentListByCprojNo(String	cprojNo) throws SQLException;
	
}
