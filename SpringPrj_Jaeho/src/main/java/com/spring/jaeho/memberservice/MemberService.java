package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberService {
	
	public void insertMember(MemberDTO dto);

	public void MailSend(MemberDTO dto , String e_mail, HttpServletRequest request);

}
