package com.probada.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class app_probada {
	
	@RequestMapping("/index")
	public String main() {
		String url = "/web-app/common/app-index";
		return url;
	}
	@RequestMapping("/project-list")
	public String project() {
		String url = "/web-app/project/project-list";
		return url;
	}
	
	@RequestMapping("/collabo-none")
	public String collaboNone() {
		String url = "/web-app/collabo/collabo-none";
		return url;
	}

	@RequestMapping("/collabo-list")
	public String collabo() {
		String url = "/web-app/collabo/collabo-list";
		return url;
	}
	
	@RequestMapping("/myWork")
	public ModelAndView myWork(ModelAndView mnv, String mail) {
		String url = "/web-app/myWork/myWork";
		
		mnv.setViewName(url);
		
		if(mail == null) {
			mnv.addObject("mail", "dashBoard");			
		}else if(mail.equals("mail")) {
			mnv.addObject("mail", "receiveMail");
		}else if(mail.equals("send")) {
			mnv.addObject("mail", "sendMail");
		}
		return mnv;
	}
	
	@RequestMapping("/test")
	public String home() {
		String url = "/web-app/common/plain_page";
		return url;
	}
	
	@RequestMapping("/pricing")
	public String pricing() {
		String url = "/web-app/common/pricing";
		return url;
	}
	
	@RequestMapping("/tag")
	public String tag() {
		String url = "/web-app/common/tag-page";
		return url;
	}
	
}
