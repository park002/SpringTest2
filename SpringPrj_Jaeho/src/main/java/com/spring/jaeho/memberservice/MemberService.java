package com.spring.jaeho.memberservice;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberService {
	
	public MemberDTO duplicateCheck(String m_id);
	
	public void MemberInsert(MemberDTO dto);
	
	public String MemberLogin(MemberDTO dto);
}
