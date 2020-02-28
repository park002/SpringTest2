package com.spring.MailController;
import java.security.MessageDigest;

public class SHA256 {
	//�̸��Ͽ� �ؽ��� �����ؼ� ���� ��ȯ�ްڴٴ� �޼��� 
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest =MessageDigest.getInstance("SHA-256"); //SHA-256���� �˰��� ����
			byte [] salt="hello!".getBytes(); //��ŷ�ȴ��Ϸ��� salt ����
			digest.reset();
			digest.update(salt);
			byte [] chars = digest.digest(input.getBytes("UTF-8")); //������ hash �� �����Ѱ��� chars ������ ��´�
			 for(int i=0; i<chars.length; i++) { //���ڿ� ���·� ������ֱ� 
				 String hex=Integer.toHexString(0xff & chars[i]);
				 if(hex.length()==1) result.append("0"); //1�ڸ� ���ΰ�� 0������ �� ���ڸ� ���������� 16�������·� ����� �� �ְ� �����
				 result.append(hex);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result.toString();	
	}
	
}