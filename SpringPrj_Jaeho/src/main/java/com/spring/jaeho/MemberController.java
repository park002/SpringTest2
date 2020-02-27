package com.spring.jaeho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.spring.jaeho.memberdto.MemberDTO;
import com.spring.jaeho.memberservice.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;

	
	public String insertMember(MemberDTO dto) {
		service.insertMember(dto);
		return null;

	}

}
