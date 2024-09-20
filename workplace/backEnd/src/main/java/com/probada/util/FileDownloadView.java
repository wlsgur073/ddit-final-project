package com.probada.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;

public class FileDownloadView implements View{

	private String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public void render(Map<String, ?> model
					 , HttpServletRequest request
					 , HttpServletResponse response) throws Exception {

		String savedPath = "C:/"+(String)model.get("savedPath");
		System.out.println("savedPath=>"+savedPath);

		String fileName = (String)model.get("fileName");
		System.out.println("fileName=>"+fileName);

		File downloadFile = new File(savedPath);
		FileInputStream inStream = new FileInputStream(downloadFile);

		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(downloadFile.getName());
		if(mimeType != null) {
			this.contentType = mimeType;
		}

		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());

		String headerKey = "Content-Disposition";

		//한글깨짐방지 : ISO-8859-1
		String header = request.getHeader("User-Agent");

		if(header.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}else {
			fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		}
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
	}
}
