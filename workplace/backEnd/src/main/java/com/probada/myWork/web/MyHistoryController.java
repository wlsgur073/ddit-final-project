package com.probada.myWork.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.myWork.service.HistoryService;
import com.probada.myWork.vo.HistoryVO;

@Controller
@RequestMapping("/app/myWork")
public class MyHistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	@RequestMapping("/getProjHistory")
	@ResponseBody
	public ResponseEntity<List<HistoryVO>> projHistoryToJSON(String projNo) throws Exception {
		ResponseEntity<List<HistoryVO>> entity = null;
		List<HistoryVO> historyList = null;
		
		try {
			historyList = historyService.getProjHistoryList(projNo);
			entity = new ResponseEntity<List<HistoryVO>>(historyList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<HistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/getProjHistoryDistSort")
	@ResponseBody
	public ResponseEntity<List<HistoryVO>> projHistoryDistSortToJSON(String projNo) throws Exception {
		ResponseEntity<List<HistoryVO>> entity = null;
		List<HistoryVO> myHistoryDistSort = null;
		
		try {
			myHistoryDistSort = historyService.getProjHistoryDistSort(projNo);
			entity = new ResponseEntity<List<HistoryVO>>(myHistoryDistSort, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<HistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/getHistory")
	@ResponseBody
	public ResponseEntity<List<HistoryVO>> historyToJSON(String userId) throws Exception {
		ResponseEntity<List<HistoryVO>> entity = null;
		List<HistoryVO> historyList = null;
		
		try {
			historyList = historyService.getHistoryList(userId);
			entity = new ResponseEntity<List<HistoryVO>>(historyList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<HistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/getMyHistoryProjectSort")
	@ResponseBody
	public ResponseEntity<List<HistoryVO>> myHistoryProjectSortToJSON(String userId) throws Exception {
		ResponseEntity<List<HistoryVO>> entity = null;
		List<HistoryVO> myHistoryProjectSort = null;
		
		try {
			myHistoryProjectSort = historyService.getMyHistoryProjectSort(userId);
			entity = new ResponseEntity<List<HistoryVO>>(myHistoryProjectSort, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<HistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/getMyHistoryDistSort")
	@ResponseBody
	public ResponseEntity<List<HistoryVO>> myHistoryDistSortToJSON(String userId) throws Exception {
		ResponseEntity<List<HistoryVO>> entity = null;
		List<HistoryVO> myHistoryDistSort = null;
		
		try {
			myHistoryDistSort = historyService.getMyHistoryDistSort(userId);
			entity = new ResponseEntity<List<HistoryVO>>(myHistoryDistSort, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<HistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
