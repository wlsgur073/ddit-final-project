package com.probada.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/test")
public class SeokTest {
	
	@RequestMapping("/SearchTest")
	public String main() {
		String url = "/web-app/common/searchResult";
		return url;
	}
	
}
