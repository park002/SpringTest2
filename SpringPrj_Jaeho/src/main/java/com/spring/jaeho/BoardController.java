package com.spring.jaeho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.jaeho.dto.BoardDTO;
import com.spring.jaeho.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	BoardService service;

	@RequestMapping(value = "/createform", method = RequestMethod.GET)
	public String createGET(BoardDTO dto) throws Exception {
		System.out.println(" /board/create 입니다. GET방식");
		return "/board/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDTO dto, RedirectAttributes rttr) throws Exception {
		service.insertBoard(dto);
		System.out.println("게시판등록 성공 createPOST()");
		rttr.addFlashAttribute("msg", "성공");
		return "redirect:/board/listAll"; // redirct 자체가 응답이다 . 302
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET) // 글목록
	public void listAll(Model model) throws Exception {
		
		// System.out.println(service.listBoard());
		model.addAttribute("boardList", service.listBoard());
		System.out.println("전체 목록 페이지 listAll() 성공");
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET) // getBoardview
	public String detailBoard(Model model, @RequestParam("b_no") int b_no) throws Exception {
		System.out.println("게시글 번호=>" + b_no + "번의 상세 페이지");
		model.addAttribute("boardContent", service.detailBoard(b_no));
		service.updateCount(b_no);
	
		return "board/BoardContent";
	}

	@RequestMapping("/delete")
	public String deleteBoard(@RequestParam("b_no") int b_no) throws Exception {
		service.deleteboard(b_no);
		System.out.println("deleteboard ()완료");
		return "redirect:/board/listAll";
	}

	@RequestMapping("/updateBoardForm")
	public ModelAndView updateBoardForm(@RequestParam("b_no") int b_no, @ModelAttribute("upboard") BoardDTO dto)
			throws Exception { // 게시글 번호 잘넘어옴
		ModelAndView mav = new ModelAndView();
		BoardDTO board = service.detailBoard(b_no);
		mav.addObject("upboard", board);
		mav.setViewName("/board/update");
		return mav;
	}

	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(BoardDTO dto) throws Exception {
		service.updateboard(dto);
		System.out.println("updateBoard() 성공적");
		return "redirect:/board/listAll";
	}

}
