//package com.spring.jaeho.memberservice;
//
//import javax.mail.internet.InternetAddress;
//
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMessage.RecipientType;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import com.spring.jaeho.memberdao.MemberDAO2;
//import com.spring.jaeho.memberdto.MemberDTO2;
//
//
//public class MemberServiceImpl2 implements MemberService2 {
//
//	private JavaMailSender mailSender;
//
//	MemberDAO2 dao;
//
//	@Override
//	public void insertMember(MemberDTO2 dto) {
//		dao.insertMember(dto);
//	}
//
//	@Override
//	public void MailSend(MemberDTO2 dto, String e_mail, HttpServletRequest request) {
//		MimeMessage mail = mailSender.createMimeMessage();
//		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�ڵ��糪�� �湮�� �ּż� �����մϴ�. �Ʒ� ��ũ Ŭ���� ȸ������ ������ �Ϸ�˴ϴ�.</h2><p>"
//				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/EmailCheckAction?code="
//				+ dto.getM_userEmailHash() + "'>���糪 ȸ������</a></h3></p>" + "<img src=http://localhost:8090"
//				+ request.getContextPath() + "/resources/img/���糪3.PNG>";
//		try {
//			mail.setSubject("[��������] ȣ�� ���糪 ���� �����Դϴ�", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // ������
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // �۽���
//			mailSender.send(mail);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public boolean setUserEmailChecked(String code) {
//		dao.setUserEmailChecked(code);
//		return true;
//	}
//
//	@Override
//	public boolean login(MemberDTO2 dto, HttpSession session) {
//		boolean logincheck = dao.login(dto);
//		if (logincheck) {
//			MemberDTO2 dto2 = viewMember(dto);
//			session.setAttribute("userId", dto2.getM_id());
//			session.setAttribute("userName", dto2.getM_name());
//			session.setAttribute("userEmail", dto2.getM_userEmail());
//		}
//		return logincheck;
//
//	}
//
//	@Override
//	public int getUserEmailChecked(MemberDTO2 dto) {
//		Integer EmailCheck = dao.getUserEmailChecked(dto);
//		return EmailCheck;
//	}
//
//	@Override
//	public MemberDTO2 viewMember(MemberDTO2 dto) {
//		// TODO Auto-generated method stub
//		return dao.viewMember(dto);
//	}
//
//	@Override
//	public boolean selectId(MemberDTO2 dto) {
//
//		return dao.selectId(dto);
//	}
//
//	@Override
//	public String SearchID(MemberDTO2 dto) {
//
//		return dao.SearchID(dto); // ã�� id
//	}
//
//	@Override
//	public void SearchIDMailSend(String m_id, String e_mail, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//
//		MimeMessage mail = mailSender.createMimeMessage();
//		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�� ���糪 �Դϴ�</h2><p>" + "<h3><a href='http://localhost:8090"
//				+ request.getContextPath() + "/'>���糪 �α���</a></h3></p>" + "<p>������ ID��  <b>" + m_id
//				+ "</b> �Դϴ� �����մϴ�.</p>" + "<br><img src=http://localhost:8090" + request.getContextPath()
//				+ "/resources/img/���糪4.PNG>";
//		try {
//			mail.setSubject("[����] ��û�Ͻ� ���糪 ID �Դϴ� . �����մϴ� ", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // ������
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // �۽���
//			mailSender.send(mail);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//	}
//
//	@Override
//	public void SearchPWMailSend(MemberDTO2 dto, String Hash, HttpServletRequest request) {
//		MimeMessage mail = mailSender.createMimeMessage();
//		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�� ���糪 �Դϴ�</h2><p>" + "<h3><a href='http://localhost:8090"
//				+ request.getContextPath() + "/member/Searchpassword?code=" + Hash + "&Password=" + dto.getM_password()
//				+ "'>���糪 �α���</a></h3></p>" + "<p>������ �ӽ� ��й�ȣ��  <b>" + Hash
//				+ "</b> �Դϴ� �����մϴ�. <p>���� ��ũ Ŭ�� �� ��й�ȣ �ڵ� ������ �˴ϴ�.</p></p>" + "<br><img src=http://localhost:8090"
//				+ request.getContextPath() + "/resources/img/���糪6.PNG>";
//		try {
//			mail.setSubject("[����] ��û�Ͻ� ���糪 Password �Դϴ� . �����մϴ� ", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(dto.getM_userEmail())); // ������
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // �۽���
//			mailSender.send(mail);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Override
//	public String SearchPW(MemberDTO2 dto) {
//		return dao.SearchPW(dto);
//	}
//
//	@Override
//	public String selectPW(MemberDTO2 dto) {
//		// TODO Auto-generated method stub
//		return dao.selectPW(dto);
//	}
//
//	@Override
//	public void updatePW(MemberDTO2 dto) {
//		// TODO Auto-generated method stub
//		dao.updatePW(dto);
//
//	}
//
//	@Override
//	public void Remove(String m_name) {
//		// TODO Auto-generated method stub
//		dao.Remove(m_name);
//
//	}
//
//}
