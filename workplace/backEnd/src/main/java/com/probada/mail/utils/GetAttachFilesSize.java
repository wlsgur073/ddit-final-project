package com.probada.mail.utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GetAttachFilesSize {
	
	public static double attachFileSize(List<MultipartFile> multiFiles) throws Exception {
		double attachFileSize = 0;
		
		if(multiFiles != null) {
			for(MultipartFile multi : multiFiles) {
				attachFileSize += multi.getSize() / (1024 * 1024);
			}
		}
		return attachFileSize;
	}
}
