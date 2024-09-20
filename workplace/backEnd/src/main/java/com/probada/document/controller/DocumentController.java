package com.probada.document.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.probada.document.service.DocumentService;
import com.probada.document.vo.FileVO;
import com.probada.document.vo.ProjectUserVO;
import com.probada.user.vo.UserVO;
import com.probada.util.CollaboUtil;
import com.probada.util.ProjectUtil;

/*
 * 문서 가이드 1/25ver
 *
 * 조건. 1) 프로젝트 생성 시에 무조건 프로젝트 폴더와 하위 폴더(업무, 이슈 등등을 지정해야한다)
 *      2) 파일 하나 업로드 삭제, 이름 바꾸기 하나씩만
 *      3) 폴더 삭제 시 하위 파일이 삭제 안된다 디비만  삭제 안되고 실제 파일은 삭제가 됨
 *      4) 같은 이름 하지마셈 에러로 막아 놓긴 함
 *      5) 폴더 생성  가급적 자제
 *      6) 휴지통 수정해야함
 *      7) 프로젝트 문서관리, 내 작업 문서관리 read메서드만 다르게 만듦
 *      8) etc "0" 공용으로 보일 거, etc "1" 나만 보일 거 디렉토리만 이렇게 하고 파일은 무조건 다 0
 */





@Controller
@RequestMapping("/document")
public class DocumentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;
	@Resource(name="projectUtil")
	private ProjectUtil projectUtil;
	@Resource(name="collaboUtil")
	private CollaboUtil collaboUtil;

	@RequestMapping("/")
	public String main() {
		String url = "/web-app/document/documentTest";
		return url;
	}

	@RequestMapping("/chat")
	public String chat() {
		String url = "/web-app/document/chat";
		return url;
	}

	@PostMapping(value="/Upload")
	@ResponseBody
	private void upload (String path, @RequestParam("file")MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse res, String projNo) throws Exception{

		String name;
		String realFileName;
		String req = documentService.seqDoc();
		String uploadPath = "c:/"+path;							// 3조 PMS probada/업무
		String fileName = uploadFile.getOriginalFilename();		// jQuery.jpg
		String fullPath = path+"/"+fileName;					// 3조 PMS probada/업무/jQuery.jpg
		LOGGER.debug("req : {}",req);
		LOGGER.debug("uploadPath : {}",uploadPath);
		LOGGER.debug("fileName : {}",fileName);
		LOGGER.debug("fullPath : {}",fullPath);

		int size = Integer.parseInt(uploadFile.getSize()+"");
		LOGGER.debug("size : {}",size);

		String extension = fileName.substring(fileName.lastIndexOf("."));
		if(extension== null) {
			extension = "";
		}
		LOGGER.debug("extension : {}",extension);

		//같은 이름 중복 방지
		name  = sameFileProcess(res, fullPath);
		if(name.equals("sameName")) {
			res.sendError(res.SC_BAD_REQUEST);
			return;
		}

		if(fileName.substring(0,fileName.lastIndexOf(".")).equals("")) {
			realFileName = req.substring(3,req.length()).concat("제목없음");
		}else {
			realFileName = fileName.substring(0,fileName.lastIndexOf("."));
		}

		File storeFile = new File(uploadPath,fileName);
		storeFile.mkdirs();

		uploadFile.transferTo(storeFile);

		FileVO doc = new FileVO();

		String seq = documentService.seqDoc();

		doc.setEtc("0");
		doc.setDOC_NO(seq);
		//하드코딩

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		String userId = userVO.getUserId();
		doc.setPROJ_NO(projNo);
		doc.setUSER_ID(userId);


		doc.setHasDirectories(false);
		doc.setIsDirectory(false);
		doc.setExtension(extension);
		doc.setName(realFileName);
		doc.setPath(path+"/"+realFileName+extension);
		doc.setSize(size);

		LOGGER.debug("doc : {}",doc);

		documentService.registDocument(doc);


	}



	@PostMapping(value="/Create")
	@ResponseBody
	private FileVO createFolder(String target,String doc_NO,HttpServletResponse res) throws Exception{

		String name;
		String seq = documentService.seqDoc();
		FileVO doc = new FileVO();
		String fileName = "새로운 폴더"+seq;
		String realPath;

		if(target == null) {
			target = "";
			realPath = fileName;
		}else {
			realPath = target+"/"+fileName;
		}

		String uploadPath = "c:/"+target;

		name  = sameFileProcess(res, realPath);
		if(name.equals("sameName")) {
			res.sendError(res.SC_BAD_REQUEST);
			return doc;
		}

		File storeFile = new File(uploadPath, fileName);
		storeFile.mkdirs();



		//하드코딩
		doc.setPROJ_NO("4");
		doc.setUSER_ID("seok@ddit.com");


		doc.setEtc("1");
		doc.setDOC_NO(seq);
		doc.setHasDirectories(true);
		doc.setIsDirectory(true);
		doc.setExtension("");
		doc.setName(fileName);
		doc.setPath(realPath);
		doc.setSize(5000);

		documentService.registDocument(doc);

		return doc;


	}

	@PostMapping(value="/Update")
	@ResponseBody
	private FileVO update (String target, String path, String doc_NO, String name, String extension,HttpServletResponse res) throws Exception{

		if(target == null) {
			path = name;
		}else {
			path = target+"/"+name+extension;
		}

		FileVO doc = new FileVO();
		doc = documentService.getDocOne(doc_NO);

		File originFile = new File("c:/"+doc.getPath());


		//세션에서 가지고 올 id
		doc.setPROJ_NO("4");
		doc.setUSER_ID("seok@ddit.com");
		doc.setPath(path);
		doc.setName(name);
		doc.setExtension(extension);

		documentService.modifyDocument(doc);

		FileVO newDoc = documentService.getDocOne(doc_NO);

		File updateFile = new File("c:/"+newDoc.getPath());

		boolean result = originFile.renameTo(updateFile);

		return doc;
	}




	@PostMapping(value="/Read")
	@ResponseBody
	public List<FileVO> read(String target,ProjectUserVO projectUserVO) throws Exception {

		//내 작업 문서관리
		//List<FileVO> result = myWork(target,"seok2@ddit.com");

		//프로젝트 문서관리
		List<FileVO> fileVO = new ArrayList<FileVO>();
		fileVO = documentService.getProjectDocument(projectUserVO);

		List<FileVO> realFileList = new ArrayList<>();

		for (FileVO vo: fileVO) {


			if(target==null && (vo.getPath().equals(vo.getName()))) {
				realFileList.add(vo);
			}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
				realFileList.add(vo);
			}
		}

		return realFileList;

	}

	@PostMapping(value="/readProjectDoc")
	@ResponseBody
	public List<FileVO> readProjectDoc(String target,ProjectUserVO projectUserVO) throws Exception {

		//내 작업 문서관리
		//List<FileVO> result = myWork(target,"seok2@ddit.com");

		LOGGER.debug(target);
		//프로젝트 문서관리
		List<FileVO> fileVO = new ArrayList<FileVO>();
		fileVO = documentService.getDocumentListByProjNo(projectUserVO);

		List<FileVO> realFileList = new ArrayList<>();

		for (FileVO vo: fileVO) {


			if(target==null && (vo.getPath().equals(vo.getName()))) {
				realFileList.add(vo);
			}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
				realFileList.add(vo);
			}
		}

		return realFileList;

	}
	
	@PostMapping(value="/readCollaboDoc")
	@ResponseBody
	public List<FileVO> readCollaboDoc(String target,String cprojNo) throws Exception {
		
		
		LOGGER.debug("[요청받음] => /readCollaboDoc");
		LOGGER.debug("[요청받음] => /readCollaboDoc ===>cprojNo" + cprojNo);
		
		LOGGER.debug("==> target" + target);
		//프로젝트 문서관리
		List<FileVO> fileVO = new ArrayList<FileVO>();
		fileVO = documentService.getDocumentListByCprojNo(cprojNo);
		
		
		List<FileVO> realFileList = new ArrayList<>();
		
		for (FileVO vo: fileVO) {
			LOGGER.debug("==> fileVO1" + vo);
			if(target==null && (vo.getPath().equals(vo.getName()))) {
				realFileList.add(vo);
			}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
				realFileList.add(vo);
				LOGGER.debug("==> fileVOX" + vo);
			}
		}
		
		return realFileList;
		
	}








	@PostMapping(value="/Destroy")
	@ResponseBody
	public  FileVO remove(String doc_NO, String path, String name, String extension) throws Exception {

		FileVO trash = new FileVO();
		FileVO doc = new FileVO();
		FileVO result = new FileVO();

		if(path!=null && name!= null && extension !=null && doc_NO !=null) {


			String orginPath = path.substring(0,3);


			//path만 이동시키면 되지 않을까??????????
			if(orginPath.equals("휴지통")) {

				doc = documentService.getDocOne(doc_NO);
				result = documentService.getDocOne(doc_NO);

				if (doc != null) {

					File file = new File("C:/" + doc.getPath());

					if (file.exists()) {
						file.delete();
					}

					documentService.removeDocument(doc_NO);

				}

			}else {

				File originFile = new File("c:/"+path);


				if(originFile.isDirectory()) {
					originFile.delete();
					trash = documentService.getDocOne(doc_NO);
					documentService.removeDocument(doc_NO);

				}else {

					trash = documentService.getDocOne(doc_NO);

					trash.setPath("휴지통/"+name+extension);
					trash.setName(name);

					File newFile = new File("c:/"+trash.getPath());

					if(originFile.exists()) {
						FileUtils.moveFile(originFile, newFile);
					}

					documentService.modifyDocument(trash);
				}
				result = trash;
			}//휴지통으로 이동


		}


		return result;


	}




	@RequestMapping("/Download")
	public String download(String path, Model model) throws Exception {


		String url = "downloadFile";

		//디렉토리는 다운이 되지 않으니 무조건 패스가 있는 파일만 /유무 판단 안해도 가능
		String FileName = path.substring(path.lastIndexOf("/")+1);

		model.addAttribute("savedPath", path);
		model.addAttribute("fileName", FileName);
		// path = 3조 PMS probada/업무/circle-dot-o.png

		return url;
	}



	public String sameFileProcess(HttpServletResponse res, String fullPath) throws Exception{


		//String sr2 = String.format("안녕하세요. %s 입니다.","dfdf");
		//같은 파일 등록 시 +1path와 name에 (1)추가할까말까
		List<FileVO> fileVO = new ArrayList<FileVO>();
		String name = "difName";


		fileVO = documentService.getDocList();

		//같은 이름 중복 방지
		for ( FileVO vo: fileVO) {
			if(vo.getPath().equals(fullPath)) {

				name = "sameName";

				return name;
			}
		}
		return name;
	}

	@RequestMapping("/getDocumentListForProjDetail")
	@ResponseBody
	public ResponseEntity<List<FileVO>> getDocumentListForProjDetail(String projNo) throws Exception {
		ResponseEntity<List<FileVO>> entity = null;

			List<FileVO> docList = new ArrayList<FileVO>();
		try {

			docList = documentService.getDocumentListForProjDetail(projNo);
			String projTitle = projectUtil.getProjectNameByProjNo(projNo);
			for (FileVO fileVO : docList) {
				fileVO.setProjTitle(projTitle);
			}
			entity = new ResponseEntity<List<FileVO>>(docList,HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<FileVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	



	private List<FileVO> myWork(String target, String userId) throws Exception{


		List<FileVO> fileVO = new ArrayList<FileVO>();
		List<FileVO> realFileList = new ArrayList<>();
		boolean flag = true;


		fileVO = documentService.getMyDocument(userId);



		for ( FileVO vo: fileVO) {

				if(vo.getPath().equals("휴지통") && flag) {

					flag = false;
					if(target==null && (vo.getPath().equals(vo.getName()))) {
						realFileList.add(vo);
					}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
						realFileList.add(vo);
					}

				}

					if(!vo.getPath().equals("휴지통")) {
						if(target==null && (vo.getPath().equals(vo.getName()))) {
							realFileList.add(vo);
						}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
							realFileList.add(vo);
						}
					}

		}

		return realFileList;

	}



	@RequestMapping("/getDocumentListByUserId")
	@ResponseBody
	public ResponseEntity<List<FileVO>> getDocumentListByUserId(String userId) throws Exception {
		ResponseEntity<List<FileVO>> entity = null;

		try {
			List<FileVO> fileList = documentService.getDocumentListByUserId(userId);
			for(FileVO fileVO : fileList) {
				int size = (fileVO.getSize()) / 1024;
				fileVO.setSize(size);
			}
			entity = new ResponseEntity<List<FileVO>>(fileList, HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<FileVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping("/getDashDocumentByProjNo")
	@ResponseBody
	public ResponseEntity<List<FileVO>> getDocumentListByProjNo(String projNo) throws Exception {
		ResponseEntity<List<FileVO>> entity = null;

		try {
			List<FileVO> fileList = documentService.getDashDocumentByProjNo(projNo);
			entity = new ResponseEntity<List<FileVO>>(fileList, HttpStatus.OK);

		} catch(Exception e) {
			entity = new ResponseEntity<List<FileVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return entity;
	}
	
	/**
	 * 콜라보 문서 리스트 상세조회
	 * @param cprojNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDocumentListForCprojDetail")
	@ResponseBody
	public ResponseEntity<List<FileVO>> getDocumentListForCprojDetail(String cprojNo) throws Exception{
		ResponseEntity<List<FileVO>> entity = null;
		
		List<FileVO> docList = new ArrayList<FileVO>();
		LOGGER.debug("[요청받음] => /getDocumentListForCprojDetail");
		
		try {
			docList = documentService.getDocumentListForCprojDetail(cprojNo);
			String cprojTitle = collaboUtil.getCollaboNameByCprojNo(cprojNo);
			for (FileVO fileVO : docList) {
				fileVO.setCprojTitle(cprojTitle);
			}
			entity = new ResponseEntity<List<FileVO>>(docList,HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<FileVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(),e); // e.printStackTrace(); 보다 LOGGER 쓸 것
			LOGGER.error("/getDocumentListForCprojDetail 시 에러가 발생했습니다.",e); 
		}
		
		return entity;
	}


}






//내가 속한 프로젝트 출력 시
//내가 속한 프로젝트 쿼리로 뽑아서 projNo에 넣으면 완성
//List<Integer> projNo = new ArrayList<>();

//projNo.add(3);
//projNo.add(4);

//List<FileVO> realFileList = new ArrayList<>();
//
//for ( FileVO vo: fileVO) {
//
//	for(int pNo : projNo) {
//
//		if(pNo==Integer.parseInt(vo.getPROJ_NO())) {
//
//
//			if(target==null && (vo.getPath().equals(vo.getName()))) {
//				realFileList.add(vo);
//			}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
//				realFileList.add(vo);
//			}
//
//		}
//
//	}
//
//}



//모든 프로젝트

	/*	fileVO = documentService.getDocList();

		List<FileVO> realFileList = new ArrayList<>();

		for ( FileVO vo: fileVO) {


			if(target==null && (vo.getPath().equals(vo.getName()))) {
				realFileList.add(vo);
			}else if(target != null && (vo.getPath().contains(target+"/"+vo.getName()))){
				realFileList.add(vo);
			}
		}

		return realFileList;*/










