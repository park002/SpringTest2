package com.spring.jaeho;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jaeho.memberdto.MemberDTO;
import com.spring.jaeho.memberservice.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberService service;

	@RequestMapping("/LoginLogOut")
	public String LoginLogout() {
		return "/member/LoginLogOut";
	}

	@RequestMapping("/MemberInsert")
	public String MemberInsert() {
        
		return "/member/MemberInsert";
	}
	
    @ResponseBody
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String MemberLogin(MemberDTO dto,HttpSession session,Model model) {
    	System.out.println(dto.toString());
	    String member = service.MemberLogin(dto);
	    System.out.println("member �� �Ӱ������� "+member);
        session.setAttribute("m_id", dto.getM_id());
	    if(member!=null) { //ID PW �´ٸ�
	         return "/index";
	    }else { //ID PW Ʋ���ٸ�
	    	model.addAttribute("login", member);
	      return "/member/LoginLogOut";
	    }
	}

	

	@RequestMapping(value = "/family", method = RequestMethod.POST)
	public String MemberFamily(MemberDTO dto) {
		
        service.MemberInsert(dto);
        
		return "/member/JoinSuccess";
	}

	private static final Pattern ID_REGEX = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
	@ResponseBody
	@RequestMapping(value = "/duplicate", method = RequestMethod.POST)
	public int IdDuplicate(@RequestParam("m_id") String m_id) {
		// ������ �������θ�,Ư������,���� ���� ����, ���� ���� 5-12�� ���Ϸ� ���ֽñ� �ٶ��ϴ�.
		MemberDTO duplicateCheck = service.duplicateCheck(m_id);
		if (duplicateCheck == null) { 
			if (ID_REGEX.matcher(m_id).matches()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 0;
		}
	}
}
