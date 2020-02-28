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
		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�ڵ��糪�� �湮�� �ּż� �����մϴ�. �Ʒ� ��ũ Ŭ���� ȸ������ ������ �Ϸ�˴ϴ�.</h2><p>"
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/aaaaaaaaaaaa?code="
				+ dto.getM_userEmailHash() + "'>���糪 ȸ������</a></h3></p>";
		try {
			System.out.println(e_mail);
			mail.setSubject("[��������] ȣ�� ���糪 ���� �����Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); //������
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com"));  //�۽���
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
