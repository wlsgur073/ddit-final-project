package com.probada.myWork.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/myWork")
public class MyWorkController {
	
	@RequestMapping("/myTaskRegistForm")
	public String myTaskRegistForm() throws Exception {
		String url = "/web-app/myWork/my-task-regist";
		return url;
	}
	
	@RequestMapping("/myTaskModifyForm")
	public String taskModifyForm() throws Exception {
		String url = "/web-app/myWork/my-task-modify";
		
		return url;
	}
	
	@RequestMapping("/myTaskDetail")
	public String taskDetail() throws Exception {
		String url = "/web-app/myWork/myTaskDetail";
		
		return url;
	}	
	
	@RequestMapping("/issueRegistForm")
	public String issueRegistForm() throws Exception {
		String url = "/web-app/myWork/my-issue-regist";
		return url;
	}
	
	@RequestMapping("/issueDetail")
	public String issueDetail() throws Exception {
		String url = "/web-app/myWork/my-issue-detail";
		return url;
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm() throws Exception {
		String url = "/web-app/myWork/my-issue-modify";
		return url;
	}
	
	@RequestMapping("/mailRegistForm")
	public String mailRegistForm() throws Exception {
		String url = "/web-app/myWork/my-mail-regist";
		return url;
	}
	
	@RequestMapping("/mailDetail")
	public String mailDetail() throws Exception {
		String url = "/web-app/myWork/my-mail-detail";
		return url;
	}
	@RequestMapping("/mail")
	public String mail() throws Exception {
		String url = "/web-app/myWork/my-mail";
		return url;
	}
	@RequestMapping("/issue")
	public String issue() throws Exception {
		String url = "/web-app/myWork/my-issue";
		return url;
	}
}
