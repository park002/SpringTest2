package com.spring.jaeho;

import javax.servlet.http.HttpServletRequest;
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
		return "/member/loginform";
	}

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
	public String login(MemberDTO dto, HttpSession session) {
		Integer EmailCheck = service.getUserEmailChecked(dto);
		if (EmailCheck == 1) {
			System.out.println("이메일 인증이 된 자 ");
			boolean logincheck = service.login(dto, session);
			if (logincheck) {
				System.out.println("로그인 성공");
				session.setAttribute("login", true);
				session.setAttribute("m_id", dto.getM_id());
				return "redirect:/board/listAll";
			} else if (logincheck == false) {
				System.out.println("로그인실패 아이디나 패스워드를 확인 해주세요 ");
//		    	session.setAttribute("pwnot", "아이디나 패스워드를 확인해주세요");
				return "redirect:/member/loginform";
			}
		} else {
			System.out.println("이메일 인증이 안된 자 ");
			return "redirect:/member/loginform";
		}
		return null;
	}

}
