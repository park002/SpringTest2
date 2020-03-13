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
		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�ڵ��糪�� �湮�� �ּż� �����մϴ�. �Ʒ� ��ũ Ŭ���� ȸ������ ������ �Ϸ�˴ϴ�.</h2><p>"
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/member/EmailCheckAction?code="
				+ dto.getM_userEmailHash() + "'>���糪 ȸ������</a></h3></p>";
		try {
			System.out.println(e_mail);
			mail.setSubject("[��������] ȣ�� ���糪 ���� �����Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // ������
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // �۽���
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
		boolean logincheck = dao.login(dto); //true false �� �� �޴´� 
		if(logincheck) { //���� ��� 
			MemberDTO dto2 = viewMember(dto);  //dto ���� id,password ���ε� �Ǿ� �ִ� .
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
	
		return  dao.SearchID(dto); //ã�� id 
	}
	@Override
	public void SearchIDMailSend(String m_id, String e_mail, HttpServletRequest request) {
		// TODO Auto-generated method stub
	System.out.println("!!!!!!!!!!!!!!!!!!!!!! id�� �Գ�=>"+m_id);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>�ȳ��ϼ��� ȣ�� ���糪 �Դϴ�</h2><p>"
				+ "<h3><a href='http://localhost:8090" + request.getContextPath() + "/'>���糪 �α���</a></h3></p>" 
		     	+	"<p>������ ID ��"+m_id +"�Դϴ� �����մϴ�.</p>";
		try {
			mail.setSubject("[����] ��û�Ͻ� ���糪 ID �Դϴ� . �����մϴ� ", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(e_mail)); // ������
			mail.setFrom(new InternetAddress("ekekekem159@gmail.com")); // �۽���
			mailSender.send(mail);
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
	

}
