package com.spring.MailController;
import java.security.MessageDigest;

public class SHA256 {
	//이메일에 해쉬를 적용해서 값을 반환받겠다는 메서드 
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest =MessageDigest.getInstance("SHA-256"); //SHA-256으로 알고리즘 적용
			byte [] salt="hello!".getBytes(); //해킹안당하려고 salt 적용
			digest.reset();
			digest.update(salt);
			byte [] chars = digest.digest(input.getBytes("UTF-8")); //실제로 hash 에 적용한값을 chars 변수에 담는다
			 for(int i=0; i<chars.length; i++) { //문자열 형태로 만들어주기 
				 String hex=Integer.toHexString(0xff & chars[i]);
				 if(hex.length()==1) result.append("0"); //1자리 값인경우 0을붙혀 총 두자리 값을가지는 16진수형태로 출력할 수 있게 만든다
				 result.append(hex);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return result.toString();	
	}
	
}