package com.spring.jaeho;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.dto.BoardDTO;
import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.page.Pagination;
import com.spring.jaeho.service.BoardService;
import com.spring.jaeho.service.ReplyService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	BoardService service;
	
	@Autowired
	ReplyService replyService;

	@RequestMapping(value = "/createform", method = RequestMethod.GET)
	public String createGET(BoardDTO dto,HttpSession session,Model model) throws Exception {
		String userId=(String)session.getAttribute("userId");
		model.addAttribute("userId",userId);
		System.out.println(" /board/create 입니다. GET방식");
		return "/board/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDTO dto) throws Exception {
//		 String writer = (String)session.getAttribute("m_id");
//	    dto.setB_writer(writer);	
		service.insertBoard(dto);
		System.out.println("게시판등록 성공 createPOST()");
		return "redirect:/board/listAll"; // redirct 자체가 응답이다 . 302
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET) // 글목록 ,페이징 처리
	public ModelAndView listAll(@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(required = false, defaultValue = "title") String searchOption,
			@RequestParam(required = false) String keyword, HttpSession session) throws Exception {
	
		// 연결유지 getAttribute 메소드가 Object 타입 이므로 형변환 해준다
		// 레코드 갯수 계산
	
		int count = service.getBoardListCnt(searchOption, keyword);
		// Pagination2 pagination = new Pagination2(); // 게시글 전체 갯수, 현재 페이지번호
		Pagination pagination = new Pagination(count, curPage);
		int start = pagination.getPageBegin();
		int end = pagination.getPageScale();
		// List<BoardDTO> list = service.listBoard(pagination);
		List<BoardDTO> list = service.listBoard(start, end, searchOption, keyword);
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		map.put("boardPager", pagination);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// map.put("boardPager", search);
		ModelAndView mav = new ModelAndView();
		String userName = (String) session.getAttribute("userName");
		mav.addObject("userName", userName);
		mav.addObject("map", map);
		mav.setViewName("board/listAll");
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailBoard(Model model, @RequestParam("b_no") int b_no,HttpSession session) throws Exception {
		int ReplyCount = replyService.countReply(b_no); //댓글의 수 구하기 . 무결성 제약조건 해결방법1. 
		//부모테이블의 주키가 자식테이블의 외래키로 사용중.
		String userId = (String)session.getAttribute("userId");
		model.addAttribute("boardContent", service.detailBoard(b_no));
		model.addAttribute("userId",userId);
		model.addAttribute("ReplyCount",ReplyCount);
		service.updateCount(b_no);
		
		return "board/BoardContent";
	}

	@RequestMapping("/delete")
	public String deleteBoard(@RequestParam("b_no") int b_no) throws Exception {
		service.deleteboard(b_no);
		System.out.println("deleteboard ()완료");
		return "redirect:/board/listAll";
	}


	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public String updateBoard(BoardDTO dto) throws Exception {
		service.updateboard(dto);
		System.out.println("updateBoard() 성공적");
		return "redirect:/board/listAll";
	}

}
