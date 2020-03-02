package com.spring.jaeho.memberdao;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.memberdto.MemberDTO;

@Repository
public class MebmerDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;

	private static String namespace = "com.spring.jaeho.mybatis.mapper.MemberMapper";

	@Override
	public void insertMember(MemberDTO dto) {
		sqlSession.insert(namespace + ".insertMember", dto);
		System.out.println(dto.isM_userEmailChecked()); // false
	}

	@Override
	public boolean setUserEmailChecked(String code) {
		sqlSession.update(namespace + ".setUserEmailChecked", code);
		return true;
	}

	@Override
	public boolean login(MemberDTO dto) {
		String logincheck = sqlSession.selectOne(namespace + ".login", dto);
		System.out.println("�α���ü��!~!~~!~!!~!~!~!~!~!~!~~!!~!~!!~!~!~!!~ =>" + logincheck); // name���� ��� ����
		return (logincheck == null) ? false : true;
	}
	@Override
	public int getUserEmailChecked(MemberDTO dto) {
		Integer EmailCheck = sqlSession.selectOne(namespace + ".getUserEmailChecked", dto);
        System.out.println("�̸���ü��~!~!!~!~!~!!~ �̸��� ���� �� ���̵� ���� �ƴ��� =>"+EmailCheck);
		return (EmailCheck == 0) ? 0 : 1;
	}
	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		return sqlSession.selectOne(namespace+".viewMember",dto);
	}

}
