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
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}
}
