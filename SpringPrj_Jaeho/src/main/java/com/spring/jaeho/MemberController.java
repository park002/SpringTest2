package com.spring.jaeho;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String login(MemberDTO dto, HttpSession session, Model model, HttpServletResponse response) {
		boolean SelectID = service.selectId(dto);
		if (SelectID == false) {
			model.addAttribute("SelectIdmsg", SelectID);
			return "member/login";
		}
		Integer EmailCheck = service.getUserEmailChecked(dto);
		if (EmailCheck == 1) {
			boolean logincheck = service.login(dto, session); // 아이디와 비밀번호가 맞다면 . 아이디와,닉네임을 알려줘라 .
			if (logincheck) {
				return "redirect:/board/listAll";
			} else if (logincheck == false) {
				model.addAttribute("Loginmsg", logincheck);
			}
		} else {
			model.addAttribute("Emailmsg", EmailCheck);

		}
		return "member/login";
	}
	
	private static final Pattern ID_REGEX = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
	
	@ResponseBody
	@RequestMapping(value = "/duplicate", method = RequestMethod.POST)
	public String duplicateCheck(@RequestParam("m_id") String m_id, MemberDTO dto) {
		System.out.println(m_id);

		if(!ID_REGEX.matcher(m_id).matches()) { // 특정 문자열이 있다면 true, 없다면 false 반환 contains()
			return "regex";
			
		} else if (m_id.trim().length() == 0) {
			return "blank";
		} else {
			dto.setM_id(m_id);
			boolean duplicateCheck = service.selectId(dto); //null 일 경우 false
			System.out.println(duplicateCheck);
			//기본자료형을 문자 열로 변경한다.
			return String.valueOf(duplicateCheck);
		}
	}
}
