package com.spring.jaeho;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.Mail.SHA256;
import com.spring.jaeho.memberdto.MemberDTO;
import com.spring.jaeho.memberservice.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@Autowired
	MemberService service;

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
			return "/member/login";
	}
	
//	@RequestMapping(value = "/loginform", method = RequestMethod.GET)  �Խ��� ���� ���ϰ� ������ ���� ����ġ��
//	public String loginform() {
//		return "/member/loginform";
//	}

	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public String EmailCheckAction(MemberDTO dto, HttpServletRequest Request) {
		dto.setM_userEmailHash(SHA256.getSHA256(dto.getM_userEmail()));
		service.insertMember(dto);
		service.MailSend(dto, dto.getM_userEmail(), Request);
		return "/member/login";
	}

	@RequestMapping(value = "/EmailCheckAction", method = RequestMethod.GET)
	public String EmailCheckAction(@RequestParam(required = false) String code, MemberDTO dto) {
		System.out.println(code);
		service.setUserEmailChecked(code);
		return "member/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO dto,HttpServletRequest Request) {
		
		Integer EmailCheck = service.getUserEmailChecked(dto);
		if (EmailCheck == 1) {
			System.out.println("�̸��� ������ �� �� ");
			boolean logincheck = service.login(dto);
			if (logincheck) {
				System.out.println("�α��� ����");
				HttpSession session = Request.getSession(); 
				session.setAttribute("m_id", dto.getM_id()); //������ ���� �Ӽ��� ����.
				return "redirect:/board/listAll";
			} else if (logincheck == false) {
				System.out.println("�α��ν��� ���̵� �н����带 Ȯ�� ���ּ��� ");
//		    	session.setAttribute("pwnot", "���̵� �н����带 Ȯ�����ּ���");
				return "redirect:/member/loginform";
			}
		} else {
			System.out.println("�̸��� ������ �ȵ� �� ");
			return "redirect:/member/loginform";
		}
		return null;
	}

}
