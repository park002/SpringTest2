package com.spring.jaeho.memberservice;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/EmailCheckAction?code="
				+ dto.getM_userEmailHash() + "'>델루나 회원인증</a></h3></p>";
		try {
			System.out.println(e_mail);
			mail.setSubject("[본인인증] 호텔 델루나 인증 메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // 수신자
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // 송신자
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean setUserEmailChecked(String code) {
		dao.setUserEmailChecked(code);
		return true;
	}
	@Override
	public boolean login(MemberDTO dto,HttpSession session) {
		boolean logincheck = dao.login(dto); //true false 로 만 받는다 
		if(logincheck) { //참일 경우 
			MemberDTO dto2 = viewMember(dto);  //dto 에는 id,password 바인딩 되어 있다 .
			session.setAttribute("userId",dto2.getM_id());
			session.setAttribute("userName",dto2.getM_name());
			session.setAttribute("userEmail", dto2.getM_userEmail());
		}
		return logincheck;
		
	}
	@Override
	public int getUserEmailChecked(MemberDTO dto) {
		   Integer EmailCheck=dao.getUserEmailChecked(dto);
		return EmailCheck;
	}
	@Override
	public MemberDTO viewMember(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.viewMember(dto);
	}
	
	@Override
	public boolean selectId(MemberDTO dto) {
		
		return  dao.selectId(dto);
	}
	
	@Override
	public String SearchID(MemberDTO dto) {
	
		return  dao.SearchID(dto); //찾을 id 
	}
	@Override
	public void SearchIDMailSend(String m_id, String e_mail, HttpServletRequest request) {
		// TODO Auto-generated method stub
	System.out.println("!!!!!!!!!!!!!!!!!!!!!! id잘 왔나=>"+m_id);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 호텔 델루나 입니다</h2><p>"
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/'>델루나 로그인</a></h3></p>" 
		     	+	"<p>귀하의 ID 는"+m_id +"입니다 감사합니다.</p>";
		try {
			mail.setSubject("[인증] 요청하신 델루나 ID 입니다 . 감사합니다 ", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // 수신자
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // 송신자
			mailSender.send(mail);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
	

}
