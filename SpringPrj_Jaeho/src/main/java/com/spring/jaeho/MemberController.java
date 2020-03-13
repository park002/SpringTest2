package com.spring.jaeho;

import java.util.Collections;
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
			boolean logincheck = service.login(dto, session); // ���̵�� ��й�ȣ�� �´ٸ� . ���̵��,�г����� �˷���� .
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
		//Pattern Ŭ������ ������ �����ϰ� Matcher��� Ŭ������ �˻����� �����Ѵ�. 
		//�׷��� Matcher�� �Լ��� matches �Լ��� ����Ͽ� ����� boolean ���� ���Ϲ��� �� �ִ�.
		//matches() �־��� �ؽ�Ʈ ��ü�� ������ ��ġ�ϴ� ��� true ��ȯ (��ġ,����ġ Ȯ��)
		//ekem159
		if(!ID_REGEX.matcher(m_id).matches()) { //Ư�� ���ڰ� ��� ���ٸ�.  
			return "regex";
			
		} else if (m_id.trim().length() == 0) {
			return "blank";
		} else {
			dto.setM_id(m_id);
			boolean duplicateCheck = service.selectId(dto); 
			//�⺻�ڷ����� ���� ���� �����Ѵ�.
			return String.valueOf(duplicateCheck);
		}
	}
	
	
	@RequestMapping(value="/FindMe", method=RequestMethod.GET)
	public String FindMe() {
	return "/member/FindMe";	
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/SearchID",method=RequestMethod.POST)
	public String SearchID(@RequestParam("m_name") String m_name,
			@RequestParam("m_userEmail") String m_userEmail,MemberDTO dto,HttpServletRequest request) {
		
	     String m_id = service.SearchID(dto); //ã�� ID
	     
	    service.SearchIDMailSend(m_id, m_userEmail, request);
        if(m_id!=null) { //id�� ���� �ִٸ�
        	return m_id;
        }
             return null;
	    
	    //mav.addObject("Member_id", m_id);
		// mav.setViewName("/member/FindMe");
             
	
//		public Map<String, String> SearchID(@RequestParam("m_name") String m_name,
//			@RequestParam("m_userEmail") String m_userEmail,MemberDTO dto,HttpServletRequest request) {

		
	}
	
}
