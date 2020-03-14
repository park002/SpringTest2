package com.spring.jaeho.memberdao;
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

//ID ��������
public boolean selectId(MemberDTO dto);

//ID ã�� 
public String SearchID(MemberDTO dto);
//PW ã��
public String SearchPW(MemberDTO dto);

//���� PW ��ȸ
public String selectPW(MemberDTO dto);

//��й�ȣ ����
public void updatePW(MemberDTO dto);

}
