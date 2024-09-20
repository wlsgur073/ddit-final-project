package com.probada.alert.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.probada.alert.vo.AlertVO;
import com.probada.user.vo.UserVO;
import com.probada.util.UserUtil;

@Controller
public class AlertController {
	
	@Autowired
	private UserUtil userUtil;
	
	@ResponseBody
	@RequestMapping(value="/app/addToAlertList.do", method = RequestMethod.POST)
	public Map<String,String> addToAlertList(@RequestBody Map<String,String> alertData) {
		userUtil.insertAlert(alertData);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		alertData.put("nickname", alertData.get("serderNickName"));
		alertData.put("writeTime", sdf.format(new Date()));
		return alertData;
	}
	
	@ResponseBody
	@RequestMapping(value="/app/updateAlertList.do", method = RequestMethod.POST)
	public Map<String, Object> updateAlertList(HttpSession session) {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
				
		int alertCount = 0;
		List<AlertVO> alertList = new ArrayList<AlertVO>();
		
		alertList = userUtil.getUserAlertList(userVO.getNickname());
		alertCount = userUtil.getAlertCount(userVO.getNickname());
		
//		시간별 내림차순 정렬
		Collections.sort(alertList, new Comparator<AlertVO>() {
			@Override
			public int compare(AlertVO a1, AlertVO a2) {
				return a1.getWriteTime().compareTo(a2.getWriteTime());
			}
		});
		
		retMap.put("alertList", alertList);
		retMap.put("alertCount", alertCount);
		
		return retMap;
	}
	
}
