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
//		String htmlStr = "<h2>안녕하세요 호텔델루나에 방문해 주셔서 감사합니다. 아래 링크 클릭시 회원가입 인증이 완료됩니다.</h2><p>"
//				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/EmailCheckAction?code="
//				+ dto.getM_userEmailHash() + "'>델루나 회원인증</a></h3></p>" + "<img src=http://localhost:8090"
//				+ request.getContextPath() + "/resources/img/델루나3.PNG>";
//		try {
//			mail.setSubject("[본인인증] 호텔 델루나 인증 메일입니다", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // 수신자
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // 송신자
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
//		return dao.SearchID(dto); // 찾을 id
//	}
//
//	@Override
//	public void SearchIDMailSend(String m_id, String e_mail, HttpServletRequest request) {
//		// TODO Auto-generated method stub
//
//		MimeMessage mail = mailSender.createMimeMessage();
//		String htmlStr = "<h2>안녕하세요 호텔 델루나 입니다</h2><p>" + "<h3><a href='http://localhost:8090"
//				+ request.getContextPath() + "/'>델루나 로그인</a></h3></p>" + "<p>귀하의 ID는  <b>" + m_id
//				+ "</b> 입니다 감사합니다.</p>" + "<br><img src=http://localhost:8090" + request.getContextPath()
//				+ "/resources/img/델루나4.PNG>";
//		try {
//			mail.setSubject("[인증] 요청하신 델루나 ID 입니다 . 감사합니다 ", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // 수신자
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // 송신자
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
//		String htmlStr = "<h2>안녕하세요 호텔 델루나 입니다</h2><p>" + "<h3><a href='http://localhost:8090"
//				+ request.getContextPath() + "/member/Searchpassword?code=" + Hash + "&Password=" + dto.getM_password()
//				+ "'>델루나 로그인</a></h3></p>" + "<p>귀하의 임시 비밀번호는  <b>" + Hash
//				+ "</b> 입니다 감사합니다. <p>위의 링크 클릭 시 비밀번호 자동 변경이 됩니다.</p></p>" + "<br><img src=http://localhost:8090"
//				+ request.getContextPath() + "/resources/img/델루나6.PNG>";
//		try {
//			mail.setSubject("[인증] 요청하신 델루나 Password 입니다 . 감사합니다 ", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(dto.getM_userEmail())); // 수신자
//			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // 송신자
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
