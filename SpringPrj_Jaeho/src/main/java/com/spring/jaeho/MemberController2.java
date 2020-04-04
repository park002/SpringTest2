//package com.spring.jaeho;
//
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.spring.Mail.SHA256;
//import com.spring.jaeho.memberdto.MemberDTO2;
//import com.spring.jaeho.memberservice.MemberService2;
//
//
//public class MemberController2 {
//	@Autowired
//	MemberService2 service;
//
//	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
//	public String loginform() {
//		return "/member/loginform";
//	}
//
//	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
//	public String EmailCheckAction(MemberDTO2 dto, HttpServletRequest Request) {
//		dto.setM_userEmailHash(SHA256.getSHA256(dto.getM_userEmail()));
//		service.insertMember(dto);
//		service.MailSend(dto, dto.getM_userEmail(), Request);
//		return "/member/login";
//	}
//
//	@RequestMapping(value = "/EmailCheckAction", method = RequestMethod.GET)
//	public String EmailCheckAction(@RequestParam String code) {
//		service.setUserEmailChecked(code);
//		return "member/login";
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(MemberDTO2 dto, HttpSession session, Model model, HttpServletResponse response) {
//		boolean SelectID = service.selectId(dto);
//		if (SelectID == false) {
//			model.addAttribute("SelectIdmsg", SelectID);
//			return "member/login";
//		}
//		Integer EmailCheck = service.getUserEmailChecked(dto);
//		if (EmailCheck == 1) {
//			boolean logincheck = service.login(dto, session);
//			if (logincheck) {
//				return "redirect:/board/listAll";
//			} else if (logincheck == false) {
//				model.addAttribute("Loginmsg", logincheck);
//			}
//		} 
//		else {
//			model.addAttribute("Emailmsg", EmailCheck);
//		  }
//		return "member/login";
//	}
//
//	private static final Pattern ID_REGEX = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
//
//	@ResponseBody
//	@RequestMapping(value = "/duplicate", method = RequestMethod.POST)
//	public String duplicateCheck(@RequestParam("m_id") String m_id, MemberDTO2 dto) {
//		// Pattern 클래스는 패턴을 저장하고 Matcher라는 클래스는 검사결과를 저장한다.
//		// 그래서 Matcher의 함수인 matches 함수를 사용하여 결과를 boolean 으로 리턴받을 수 있다.
//		// matches() 주어진 텍스트 전체와 패턴이 일치하는 경우 true 반환 (일치,불일치 확인)
//		if (!ID_REGEX.matcher(m_id).matches()) { // 특수 문자가 들어 갔다면.
//			return "regex";
//
//		} else if (m_id.trim().length() == 0) {
//			return "blank";
//		} else {
//			dto.setM_id(m_id);
//			boolean duplicateCheck = service.selectId(dto);
//			// 기본자료형을 문자 열로 변경한다.
//			return String.valueOf(duplicateCheck);
//		}
//	}
//
//	@RequestMapping(value = "/FindMe", method = RequestMethod.GET)
//	public String FindMe() {
//		return "/member/FindMe";
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/SearchID", method = RequestMethod.POST)
//	public ResponseEntity<String> SearchID(@RequestParam("m_userEmail") String m_userEmail, MemberDTO2 dto,
//			HttpServletRequest request) {
//		ResponseEntity<String> entity = null;
//		String m_id = service.SearchID(dto); // 찾을 ID
//		if (m_id != null) {
//			service.SearchIDMailSend(m_id, m_userEmail, request);
//			entity = new ResponseEntity<String>("success", HttpStatus.OK);// 200
//		} else {
//			entity = new ResponseEntity<String>("failure", HttpStatus.INTERNAL_SERVER_ERROR);// 500 에러
//		}
//
//		return entity;
//
//		// mav.addObject("Member_id", m_id);
//		// mav.setViewName("/member/FindMe");
//
////		public Map<String, String> SearchID(@RequestParam("m_name") String m_name,
////			@RequestParam("m_userEmail") String m_userEmail,MemberDTO dto,HttpServletRequest request) {
//
//		// return Collections.singletonMap("Member_id", service.SearchID(dto));
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/SearchPW", method = RequestMethod.POST)
//	public ResponseEntity<String> SearchPW(@RequestBody MemberDTO2 dto, HttpServletRequest request) {
//		ResponseEntity<String> entity = null;
//		dto.setM_password(service.selectPW(dto));
//		String Hash = service.SearchPW(dto);
//		if (Hash != null) {
//			service.SearchPWMailSend(dto, Hash, request);
//			entity = new ResponseEntity<String>("success", HttpStatus.OK);
//		} else {
//			entity = new ResponseEntity<String>("failure", HttpStatus.INTERNAL_SERVER_ERROR);// 500 내부서버오류
//		}
//		return entity;
//	}
//
//	@RequestMapping(value = "/Searchpassword", method = RequestMethod.GET)
//	public String SearchMailpassword(@RequestParam("code") String code, @RequestParam("Password") String Password,
//			MemberDTO2 dto) {
//		// update set
//		dto.setM_userEmailHash(code);
//		dto.setM_password(Password);
//		service.updatePW(dto);
//		return "redirect:/";
//	}
//
//	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
//	public String Logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//
//	@RequestMapping(value = "/Remove", method = RequestMethod.GET)
//	public String Remove(@RequestParam("m_name") String m_name) {
//		service.Remove(m_name);
//		return "member/Remove";
//
//	}
//
//}
