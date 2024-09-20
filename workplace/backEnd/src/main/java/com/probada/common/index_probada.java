package com.probada.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class index_probada {

	@RequestMapping("/index")
	public String index() {
		String url ="/web-index/common/home-page-index";
		return url;
	}
	

	
}
