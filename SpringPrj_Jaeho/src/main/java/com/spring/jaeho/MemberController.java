package com.spring.jaeho;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(MemberDTO dto,HttpSession session,HttpServletResponse response) {
		Integer EmailCheck = service.getUserEmailChecked(dto);
		if (EmailCheck == 1) {
			System.out.println("�̸��� ������ �� �� ");
			
			boolean logincheck = service.login(dto,session); //���̵�� ��й�ȣ�� �´ٸ� . ���̵��,�г����� �˷���� .
			ModelAndView mav = new ModelAndView();
			if (logincheck) {
				System.out.println("�α��� ����");
				mav.setViewName("/member/home");
				mav.addObject("msg","success");
			} 
			else if (logincheck == false) {
				System.out.println("�α��ν��� ���̵� �н����带 Ȯ�� ���ּ��� ");
				mav.setViewName("member/login");
				mav.addObject("msg","failure");
//		    	session.setAttribute("pwnot", "���̵� �н����带 Ȯ�����ּ���");
			}
			return mav;
		}
		else {
			System.out.println("�̸��� ������ �ȵ� �� ");
			response.setContentType("text/html; charset=UTF-8");
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('����Ͻ� �̸��Ϸ� ȸ�������� ���ֽñ� �ٶ��ϴ�.'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
