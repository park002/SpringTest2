package com.spring.jaeho.memberservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.jaeho.memberdto.MemberDTO;

public interface MemberService {
	
	public void insertMember(MemberDTO dto);
   
	public void MailSend(MemberDTO dto , String e_mail, HttpServletRequest request);
    
	//Ư���� ����ڰ� �̸��� ������ ���ؼ� �̸��� �����Ϸᰡ �ǵ��� ���ִ� �Լ�
	public boolean setUserEmailChecked(String code);
	
	//ȸ���α���
	public boolean login(MemberDTO dto,HttpSession session);
	
	//ȸ�� id�� �̸��� ������ �Ǿ����� �ȵǾ�����.
	public int getUserEmailChecked(MemberDTO dto);
}
