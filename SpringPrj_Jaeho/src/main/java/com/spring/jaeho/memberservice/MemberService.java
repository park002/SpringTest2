package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberService {
	
	public void insertMember(MemberDTO dto);
   
	public void MailSend(MemberDTO dto , String e_mail, HttpServletRequest request);
    
	//특정한 사용자가 이메일 검증을 통해서 이메일 인증완료가 되도록 해주는 함수
	public boolean setUserEmailChecked(String code);
	
	//회원로그인
	public boolean login(MemberDTO dto,HttpSession session);
	
	//회원 id가 이메일 인증이 되었는지 안되었는지.
	public int getUserEmailChecked(MemberDTO dto);
}
