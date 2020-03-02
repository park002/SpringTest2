package com.spring.jaeho.memberdao;

import javax.servlet.http.HttpServletRequest;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberDAO {
	
public void insertMember(MemberDTO dto);

//특정한 사용자가 이메일 검증을 통해서 이메일 인증완료가 되도록 해주는 함수
public boolean setUserEmailChecked(String code);

//회원로그인
public boolean login(MemberDTO dto);

// id 이메일 인증 여부 
public int getUserEmailChecked(MemberDTO dto);

//세션에 담아줄 메소드 
public MemberDTO viewMember(MemberDTO dto);


}
