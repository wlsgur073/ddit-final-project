package com.probada.mail.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.probada.mail.vo.AttachVO;

public class GetAttachesByMultipartFileAdapter {
	
	public static List<AttachVO> save(List<MultipartFile> multiFiles, String savePath) throws Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		//저장 -> AttachVO -> list.add
		if(multiFiles != null) {
			for(MultipartFile multi : multiFiles) {
				String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				long fileSize = multi.getSize();
				File target = new File(savePath, fileName);
				
				target.mkdirs();
				
				multi.transferTo(target);
				
				AttachVO attachVO = new AttachVO();
				attachVO.setFilePath(savePath);
				attachVO.setFileName(fileName);
				attachVO.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
				attachVO.setFileSize(fileSize);
				attachList.add(attachVO);
			}
		}
		return attachList;
	}
}
