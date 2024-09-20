package com.probada.request.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.request.service.RequestService;
import com.probada.request.vo.RequestVO;

@Controller
@RequestMapping("app/myWork")
public class MyRequestController {
	
	@Autowired
	private RequestService requestService;
	
	//Your리퀘스트 리스트 출력
	@RequestMapping("/getYourRequest")
	@ResponseBody
	public ResponseEntity<List<RequestVO>> yourRequestToJSON(String userTo) throws Exception {
		ResponseEntity<List<RequestVO>> entity = null;
		List<RequestVO> yourRequestList = null;
		
		try {
			yourRequestList = requestService.getYourRequestList(userTo);
			entity = new ResponseEntity<List<RequestVO>>(yourRequestList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<RequestVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	//Your리퀘스트 범주
	@RequestMapping("/getYourRequestSort")
	@ResponseBody
	public ResponseEntity<List<RequestVO>> yourRequestSortToJSON(String userTo) throws Exception {
		ResponseEntity<List<RequestVO>> entity = null;
		List<RequestVO> yourRequestSort = null;
		
		try {
			yourRequestSort = requestService.getYourRequestSort(userTo);
			entity = new ResponseEntity<List<RequestVO>>(yourRequestSort, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<RequestVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	//My리퀘스트 리스트 출력
	@RequestMapping("/getMyRequest")
	@ResponseBody
	public ResponseEntity<List<RequestVO>> myRequestToJSON(String userFrom) throws Exception {
		ResponseEntity<List<RequestVO>> entity = null;
		List<RequestVO> myRequestList = null;
		
		try {
			myRequestList = requestService.getMyRequestList(userFrom);
			entity = new ResponseEntity<List<RequestVO>>(myRequestList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<RequestVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	//My리퀘스트 범주
	@RequestMapping("/getMyRequestSort")
	@ResponseBody
	public ResponseEntity<List<RequestVO>> myRequestSortToJSON(String userFrom) throws Exception {
		ResponseEntity<List<RequestVO>> entity = null;
		List<RequestVO> myRequestSort = null;
		
		try {
			myRequestSort = requestService.getMyRequestSort(userFrom);
			entity = new ResponseEntity<List<RequestVO>>(myRequestSort, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<RequestVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	//Your리퀘스트 상태 변경(승낙/거절)
	@RequestMapping(value="/changeRequestStatus", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<String>> changeRequestStatus(String status, String reqNo, String reqRes) throws Exception {
		ResponseEntity<List<String>> entity = null;
		List<String> msg = new ArrayList<String>();
		
		try {
			if(status.equals("A702")) {
				msg.add("리퀘스트를 수락했습니다.");
			}else {
				msg.add("리퀘스트를 거절했습니다.");				
			}
			requestService.changeRequestStatus(status, reqNo, reqRes);
			entity = new ResponseEntity<List<String>>(msg, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
