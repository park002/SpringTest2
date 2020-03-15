package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberService {
	
	public void insertMember(MemberDTO dto);
	
    //회원가입시 메일 전송
	public void MailSend(MemberDTO dto , String e_mail, HttpServletRequest request);
	
	//ID찾을때 메일 전송
	public void SearchIDMailSend(String m_id,String e_mail,HttpServletRequest request);
	
	//PW 찾을 때 메일 전송
	public void SearchPWMailSend(MemberDTO dto,String Hash,HttpServletRequest request);
    
	//특정한 사용자가 이메일 검증을 통해서 이메일 인증완료가 되도록 해주는 함수
	public boolean setUserEmailChecked(String code);
	
	//회원로그인
	public boolean login(MemberDTO dto,HttpSession session);
	
	//회원 id가 이메일 인증이 되었는지 안되었는지.
	public int getUserEmailChecked(MemberDTO dto);

	//세션에 담아줄 메소드 
	public MemberDTO viewMember(MemberDTO dto);
	
	//ID 존재유무
	public boolean selectId(MemberDTO dto);
	
	//ID 찾기
	public String SearchID(MemberDTO dto);
    //PW찾기
	public String SearchPW(MemberDTO dto);
	
	//기존 PW 조회
	public String selectPW(MemberDTO dto);
	
	//비밀번호 변경
	public void updatePW(MemberDTO dto);

	//회원탈퇴
	public void Remove(String m_name);

	
}
