package com.spring.jaeho.memberdao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.memberdto.MemberDTO;

@Repository
public class MebmerDAOImpl implements MemberDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	 private static String namespace="com.spring.jaeho.mybatis.mapper.MemberMapper";
	@Override
	public void insertMember(MemberDTO dto) {
		sqlSession.insert(namespace+".insertMember",dto);
		
	}
	

}
