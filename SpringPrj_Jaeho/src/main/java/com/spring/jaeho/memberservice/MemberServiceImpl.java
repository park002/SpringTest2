package com.spring.jaeho.memberservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.memberdao.MemberDAO;
import com.spring.jaeho.memberdto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
 @Autowired
  MemberDAO dao;
	
	@Override
		public MemberDTO duplicateCheck(String m_id) {
			// TODO Auto-generated method stub
			return dao.duplicateCheck(m_id);
		}
	@Override
		public void MemberInsert(MemberDTO dto) {
			 dao.MemberInsert(dto);
		}
	@Override
		public String MemberLogin(MemberDTO dto) {
			// TODO Auto-generated method stub
		
			return dao.MemberLogin(dto);
		}
}
