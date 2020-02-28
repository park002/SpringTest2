package com.spring.jaeho.memberservice;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.jaeho.memberdao.MemberDAO;
import com.spring.jaeho.memberdto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	MemberDAO dao;

	@Override
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}

	@Override
	public void MailSend(MemberDTO dto, String e_mail, HttpServletRequest request) {
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 호텔델루나에 방문해 주셔서 감사합니다. 아래 링크 클릭시 회원가입 인증이 완료됩니다.</h2><p>"
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/aaaaaaaaaaaa?code="
				+ dto.getM_userEmailHash() + "'>델루나 회원인증</a></h3></p>";
		try {
			System.out.println(e_mail);
			mail.setSubject("[본인인증] 호텔 델루나 인증 메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); //수신자
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com"));  //송신자
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
