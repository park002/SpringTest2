package com.spring.jaeho.memberdao;

import javax.servlet.http.HttpServletRequest;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberDAO {
	
public void insertMember(MemberDTO dto);

//Ư���� ����ڰ� �̸��� ������ ���ؼ� �̸��� �����Ϸᰡ �ǵ��� ���ִ� �Լ�
public boolean setUserEmailChecked(String code);

//ȸ���α���
public boolean login(MemberDTO dto);

// id �̸��� ���� ���� 
public int getUserEmailChecked(MemberDTO dto);

//���ǿ� ����� �޼ҵ� 
public MemberDTO viewMember(MemberDTO dto);


}
