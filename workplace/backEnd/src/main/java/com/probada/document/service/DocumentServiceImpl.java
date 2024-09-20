package com.probada.document.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.probada.collabo.vo.CollaboTaskVO;
import com.probada.document.dao.DocumentDAO;
import com.probada.document.vo.FileVO;
import com.probada.document.vo.ProjectUserVO;
import com.probada.issue.vo.IssueVO;
import com.probada.task.vo.TaskVO;


public class DocumentServiceImpl implements DocumentService{



	private DocumentDAO documentDAO;

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}


	@Override
	public List<FileVO> getDocList() throws SQLException {

		List<FileVO> docList = null;
		docList = documentDAO.selectDocList();


		return docList;
	}


	@Override
	public void registDocument(FileVO document) throws SQLException {

		documentDAO.insertDocument(document);

	}


	@Override
	public void modifyDocument(FileVO document) throws SQLException {

		documentDAO.updateDocument(document);

	}


	@Override
	public FileVO getDocOne(String docId) throws SQLException {

		FileVO file = new FileVO();

		file = documentDAO.selectDocumentByPath(docId);

		return file;
	}


	@Override
	public void removeDocument(String docId) throws SQLException {

		documentDAO.deleteDocument(docId);

	}


	@Override
	public String seqDoc() throws SQLException {

		String seq = documentDAO.selectDocumentSeqNext();


		return seq;
	}


	@Override
	public List<FileVO> getMyDocument(String userId) throws SQLException {

		List<FileVO> myDoc = new ArrayList<FileVO>();
		List<FileVO> myDefaultDir = new ArrayList<FileVO>();
		List<FileVO> myDir = new ArrayList<FileVO>();

		myDoc = documentDAO.selectDocumentByUserId(userId);
		myDefaultDir = documentDAO.selectDefaultDirectoryMyProject(userId);
		myDir = documentDAO.selectDirectoryMyProject(userId);




		List<FileVO> joined = new ArrayList<>();
		joined.addAll(myDoc);
		joined.addAll(myDefaultDir);
		joined.addAll(myDir);


		System.out.println("service=>"+joined);


		return joined;
	}


	@Override
	public List<FileVO> getProjectDocument(ProjectUserVO user) throws SQLException {


		List<FileVO> myProjDefaultDoc = new ArrayList<>();
		List<FileVO> myProjDoc = new ArrayList<>();
		List<FileVO> joined = new ArrayList<>();


		myProjDefaultDoc = documentDAO.selectDocumentByDefaultProj(user.getProjNo());
		myProjDoc = documentDAO.selectDocumentByMyProj(user);


		joined.addAll(myProjDefaultDoc);
		joined.addAll(myProjDoc);
		return joined;
	}


	@Override
	public List<FileVO> getDocumentListByProjNo(ProjectUserVO user) throws SQLException {

		List<FileVO> fileList = documentDAO.selectDocumentListByProjNo(user);

		return fileList;
	}


	@Override
	public List<FileVO> getDocumentListForProjDetail(String projNo) throws SQLException {

		List<FileVO> fileList = documentDAO.selectDocumentListForProjDetail(projNo);

		return fileList;
	}


	@Override
	public String getProjByTitle(String title) throws SQLException {
		String list = documentDAO.selectProjByTitle(title);
		return list;
	}


	@Override
	public List<FileVO> getDocumentListBytaskTitleANDprojNo(TaskVO taskVO) throws SQLException {

		List<FileVO> fileList = documentDAO.selectDocumentListBytaskTitleANDprojNo(taskVO);

		return fileList;
	}


	@Override
	public List<FileVO> getDocumentListByIssueTitleAndProjNo(IssueVO issueVO) throws SQLException {
		List<FileVO> fileList = documentDAO.selectDocumentListByIssueTitleAndProjNo(issueVO);
		return fileList;
	}


	@Override
	public void registDocumentCollabo(FileVO document) throws SQLException {

		documentDAO.insertDocumentCollabo(document);
	}


	@Override
	public List<FileVO> getDocumentListByUserId(String userId) throws SQLException {
		List<FileVO> fileList = documentDAO.selectDocumentListByUserId(userId);
		return fileList;
	}


	@Override
	public List<FileVO> getDocumentListBytaskTitleANDcprojNo(CollaboTaskVO collaboTaskVO) throws SQLException {
		List<FileVO> fileList = documentDAO.selectDocumentListBytaskTitleANDcprojNo(collaboTaskVO);
		return fileList;
	}


	@Override
	public List<FileVO> getDashDocumentByProjNo(String projNo) throws SQLException {

		List<FileVO> fileList = documentDAO.selectDashDocumentByProjNo(projNo);

		return fileList;
	}


	@Override
	public List<FileVO> getDocumentListForCprojDetail(String cprojNo) throws SQLException {
		List<FileVO> fileList = documentDAO.selectDocumentListForCprojDetail(cprojNo);
		return fileList;
	}


	@Override
	public List<FileVO> getDocumentListByCprojNo(String cprojNo) throws SQLException {
		List<FileVO> fileList = documentDAO.selectDocumentListByCprojNo(cprojNo);
		return fileList;
	}
}
