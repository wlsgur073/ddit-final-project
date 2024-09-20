package com.probada.mail.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.probada.mail.vo.AttachVO;

public class MakeFileName {
	
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}
	
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		String[] uuidFileName = fileName.split(delimiter);
		return uuidFileName[uuidFileName.length - 1];
	}
	
	public static List<AttachVO> parseFileNameFromAttaches(List<AttachVO> attachList, String delimiter){
		List<AttachVO> renamedAttachList = new ArrayList<AttachVO>();
		
		if(attachList != null) {
			for(AttachVO attachVO : attachList) {
				String fileName = attachVO.getFileName();				//DB상의 fileName
				fileName = parseFileNameFromUUID(fileName, delimiter);	//uuid가 제거된 fileName
				
				//fileName
				attachVO.setFileName(fileName);
				renamedAttachList.add(attachVO);
			}
		}
		return renamedAttachList;
	}
}
