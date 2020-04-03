package com.spring.jaeho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	
	@RequestMapping("/LoginLogOut")
	public String LoginLogout() {
		return "/member/LoginLogOut";
	}
	
	@RequestMapping("/MemberInsert")
	public String MemberInsert() {
		
		return "/member/MemberInsert";
	}
}
