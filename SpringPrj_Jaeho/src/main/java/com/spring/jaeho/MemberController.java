package com.spring.jaeho;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.MailController.SHA256;
import com.spring.jaeho.memberdto.MemberDTO;
import com.spring.jaeho.memberservice.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping(value="/loginform",method=RequestMethod.GET)
	public String loginform() {
		return "/member/loginform";
	}
	
	@RequestMapping(value="/insertMember", method = RequestMethod.POST)
	public String EmailCheckAction(MemberDTO dto, Model model, HttpServletRequest Request) {
		dto.setM_userEmailHash(SHA256.getSHA256(dto.getM_userEmail()));
		service.insertMember(dto);
		
		service.MailSend(dto, dto.getM_userEmail(), Request);
		return "/member/login";

	}

}
