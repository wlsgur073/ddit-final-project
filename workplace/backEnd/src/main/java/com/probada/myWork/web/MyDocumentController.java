package com.probada.myWork.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/app/myWork")
public class MyDocumentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyDocumentController.class);

	@Autowired
	private DocumentService documentService;

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
	private FileVO upload (HttpSession session,String path, @RequestParam("file")MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse res) throws Exception{

		String name;
		String realFileName;
		String req = documentService.seqDoc();
		String realUploadPath = "c:/"+path;						// 3조 PMS probada/업무
		String fileName = uploadFile.getOriginalFilename();		// jQuery.jpg
		String fullPath = path+"/"+fileName;					// 3조 PMS probada/업무/jQuery.jpg

		int size = Integer.parseInt(uploadFile.getSize()+"");

		String extension = fileName.substring(fileName.lastIndexOf("."));
		if(extension== null) {
			extension = "";
		}

		//프로젝트 번호 알아내기 (프로젝트 명이 중복 안된다는 조건하에)
		String projectTitle= fullPath.substring(0,fullPath.indexOf("/"));

		String projNo = documentService.getProjByTitle(projectTitle);
		LOGGER.debug("projNo : {}",projNo);
		if(projNo == null) {
			projNo = "63";
			
		}
		LOGGER.debug("projNo 2차 : {}",projNo);
		//같은 이름 중복 방지
		name  = sameFileProcess(res, fullPath);
		if(name.equals("sameName")) {
			res.sendError(res.SC_BAD_REQUEST);
			
		}

		if(fileName.substring(0,fileName.lastIndexOf(".")).equals("")) {
			realFileName = req.substring(3,req.length()).concat("제목없음");
		}else {
			realFileName = fileName.substring(0,fileName.lastIndexOf("."));
		}

		File storeFile = new File(realUploadPath,fileName);
		storeFile.mkdirs();

		uploadFile.transferTo(storeFile);

		FileVO doc = new FileVO();

		String seq = documentService.seqDoc();

		doc.setEtc("0");
		doc.setDOC_NO(seq);

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

		
		return doc;
	}



	@PostMapping(value="/Create")
	@ResponseBody
	private FileVO createFolder(String target,String doc_NO,HttpServletResponse res,HttpSession session) throws Exception{
		
		UserVO userVO= (UserVO) session.getAttribute("userVO");
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
		//0으로 
		doc.setPROJ_NO("63");
		doc.setUSER_ID(userVO.getUserId());


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
	private FileVO update (String target, String path, String doc_NO, String name, String extension,HttpServletResponse res,HttpSession session) throws Exception{

		
		UserVO userVO= (UserVO) session.getAttribute("userVO");
		String projectTitle= null;
		String projNo = null;
		
		FileVO doc = new FileVO();
		doc = documentService.getDocOne(doc_NO);

		if(target == null || path==null) {
			path = name;
		}else {
			path = target+"/"+name+extension;
			projectTitle= doc.getName();
			projNo = documentService.getProjByTitle(projectTitle);

		}
	

		File originFile = new File("c:/"+doc.getPath());
		
		if(projNo == null) {
			projNo = "63";
		}
		
		doc.setPROJ_NO(projNo);
		doc.setUSER_ID(userVO.getUserId());
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
	public List<FileVO> read(String target,String path, HttpSession session) throws Exception {
		
		
		
		LOGGER.debug("read target: {}",target);
		LOGGER.debug("read path: {}",path);
		
		UserVO userVO= (UserVO) session.getAttribute("userVO");
		//내 작업 문서관리
		List<FileVO> result = myWork(target,userVO.getUserId());

		
		LOGGER.debug("result: {}",result);
		return result;

	}



	@PostMapping(value="/Destroy")
	@ResponseBody
	public FileVO remove(String doc_NO, String path, String name, String extension) throws Exception {
		
		FileVO trash = new FileVO();
		FileVO doc = new FileVO();
		FileVO result = new FileVO();
		String orginPath = null;
		String trashPath = null;
		String trashRootPath = null;
		String trashRoom = null;
		
		
		LOGGER.debug("Destroy path: {}",path);
		
		if(path!=null && name!= null && extension !=null && doc_NO !=null) {

			
			if(path.contains("휴지통")) {
				
			
				
				if(path.substring(0,3).equals("휴지통")) {
					
					trashRoom = path.substring(0,3);
					
				}else {
					trashRoom = path.substring(path.indexOf("/")+1,path.indexOf("통/")+1);
				}
				
			
			}else {
				trashRoom = path;
			}
			

			if(trashRoom.equals("휴지통")) {

				doc = documentService.getDocOne(doc_NO);
				result = documentService.getDocOne(doc_NO);

				if (doc != null) {

					File file = new File("C:/" + doc.getPath());

					if (file.exists()) {
						file.delete();
					}

					documentService.removeDocument(doc_NO);

				}

				
			//휴지통이 아닐 시
			}else {

				File originFile = new File("c:/"+path);


				if(originFile.isDirectory()) {
					originFile.delete();
					trash = documentService.getDocOne(doc_NO);
					documentService.removeDocument(doc_NO);

				}else {

					trash = documentService.getDocOne(doc_NO);
					
					//그냥 휴지통으로 가는 경우			
					//if(path !=null) {
						
					trashRootPath = path.substring(0,path.indexOf("/"));
					trashPath = trashRootPath+"/휴지통/"+name+extension;
					LOGGER.debug("trashRootPath11 : {}",trashRootPath);
					LOGGER.debug("trashPath11 : {}",trashPath);
						
					//}
					
					LOGGER.debug("trashPathresutl1ds : {}",trashPath);
					
					trash.setPath(trashPath);
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


	private List<FileVO> myWork(String target, String userId) throws Exception{


		List<FileVO> fileVO = new ArrayList<FileVO>();
		List<FileVO> realFileList = new ArrayList<>();
		boolean flag = true;


		fileVO = documentService.getMyDocument(userId);



		for ( FileVO vo: fileVO) {

			if(vo.getPath().equals("루트")) {
				
				
			}else {

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

		}
		return realFileList;

	}
}













