//package com.spring.jaeho.memberservice;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import com.spring.jaeho.memberdto.MemberDTO2;
//
//public interface MemberService2 {
//	
//	public void insertMember(MemberDTO2 dto);
//	
//    //ȸ�����Խ� ���� ����
//	public void MailSend(MemberDTO2 dto , String e_mail, HttpServletRequest request);
//	
//	//IDã���� ���� ����
//	public void SearchIDMailSend(String m_id,String e_mail,HttpServletRequest request);
//	
//	//PW ã�� �� ���� ����
//	public void SearchPWMailSend(MemberDTO2 dto,String Hash,HttpServletRequest request);
//    
//	//Ư���� ����ڰ� �̸��� ������ ���ؼ� �̸��� �����Ϸᰡ �ǵ��� ���ִ� �Լ�
//	public boolean setUserEmailChecked(String code);
//	
//	//ȸ���α���
//	public boolean login(MemberDTO2 dto,HttpSession session);
//	
//	//ȸ�� id�� �̸��� ������ �Ǿ����� �ȵǾ�����.
//	public int getUserEmailChecked(MemberDTO2 dto);
//
//	//���ǿ� ����� �޼ҵ� 
//	public MemberDTO2 viewMember(MemberDTO2 dto);
//	
//	//ID ��������
//	public boolean selectId(MemberDTO2 dto);
//	
//	//ID ã��
//	public String SearchID(MemberDTO2 dto);
//    //PWã��
//	public String SearchPW(MemberDTO2 dto);
//	
//	//���� PW ��ȸ
//	public String selectPW(MemberDTO2 dto);
//	
//	//��й�ȣ ����
//	public void updatePW(MemberDTO2 dto);
//
//	//ȸ��Ż��
//	public void Remove(String m_name);
//
//	
//}
