package com.spring.jaeho.memberdao;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberDAO {

	public MemberDTO duplicateCheck(String m_id);

	public void MemberInsert(MemberDTO dto);

	public String MemberLogin(MemberDTO dto);

}
