package com.spring.jaeho;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.page.ReplyPager;
import com.spring.jaeho.service.ReplyService;

@RestController
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	ReplyService service;

//
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	public void insertReply(ReplyDTO dto, HttpSession session) {// 커맨드객체
//		String userId = (String) session.getAttribute("userId");
//		dto.setReplyer(userId);
//		// System.out.println("댓글작성자 ===================>"+dto.getReplyer());
//		service.insertReply(dto);
//	}

	// RestConroller 방식으로 json에 전달하여 댓글입력.
	// ResponseEntity : 데이터 + http status code 404,500 에러 등 개발자가 에러를 전송하고싶은 데이터와 함께
	// 전송할 수 있어서 세밀한 제어가 가능.
	// ResponseBody : 객체를 json으로 반환하겠다.
	// RequestBody :json을 객체로 받겠다.
	@RequestMapping(value = "/insertRest", method = RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyDTO dto, HttpSession session) {
		ResponseEntity<String> entity = null;

		try {
			// 세션에 저장된 회원아이디를 댓글작성자에 세팅 .
			String userId = (String) session.getAttribute("userId");
			dto.setReplyer(userId);
			// 댓글입력(삽입) 메소드 호출
		
			service.insertReply(dto);
			
	
			// 댓글입력이 성공하면 성공메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 댓글입력이 실패하면 실패메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// HTTP status 메시지 리턴.
		return entity;
	}

	// json으로 반환하여 목록생성
	//댓글리스트
	// PathVariable: url에 명시된 변수를 받아온다.
	@RequestMapping(value = "/list/{b_no}/{curPage}", method = RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("b_no") int b_no, @PathVariable int curPage, ModelAndView mav,
			HttpSession session) {
		// 페이징처리 댓글갯수 구하는 메소드 필요 .
		int count = service.countReply(b_no);

		ReplyPager replyPager = new ReplyPager(count, curPage);
		// 현재 페이지의 페이지 시작번호
		int start = replyPager.getPageBegin(); // 0 0행부터
		int end = replyPager.getPageScale(); // 5 5개씩 보여주겠다.
		List<ReplyDTO> list = service.listReply(b_no, start, end, session);
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+list.get(0).getB_date());
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		mav.setViewName("board/replyList");
		return mav; // board/replyList.jsp로 포워딩

	}

//댓글 상세보기
	@RequestMapping(value = "/detail/{r_no}", method = RequestMethod.GET)
	public ModelAndView replyModify(@PathVariable("r_no") int r_no, ModelAndView mav) {
		ReplyDTO dto = service.replyDetail(r_no);
		mav.addObject("dto", dto);
		mav.setViewName("/reply/replyDetail");
		return mav;
	}

	// 댓글수정
	@RequestMapping(value = "/update/{r_no}", method = RequestMethod.PATCH)
	public ResponseEntity<String> replyModify(@PathVariable("r_no") int r_no, @RequestBody ReplyDTO dto) {
		ResponseEntity<String> entity = null;
		try {
			dto.setR_no(r_no);
			service.replyModify(dto);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 댓글삭제
	@RequestMapping(value = "/delete/{r_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> replydelete(@PathVariable("r_no") int r_no) {
		ResponseEntity<String> entity = null;
		try {
			service.replydelete(r_no);
			entity =new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
//	static {
//		
//		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
//		
//		list.getB_no(); //1
//		row.getB_no(); //2
//		
//		list.get(0).getB_no();
//		
//		for(ReplyDTO row : list)
//			row.getB_no(); //3
//		
//	}

//	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
//	public ModelAndView listReply(@RequestParam("b_no") int b_no) {
//		List<ReplyDTO> list = service.listReply(b_no);
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", list);
//		mav.setViewName("/reply/replyList");
//		return mav;
//	}
//
//	@RequestMapping(value = "/listJson")
//	public List<ReplyDTO> listJson(@RequestParam("b_no") int b_no) {
//		List<ReplyDTO> list = service.listReply(b_no);
//	// System.out.println(list);
//		return list;
//	}

}
