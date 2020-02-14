package com.spring.jaeho;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		System.out.println(service.listBoard());
		model.addAttribute("boardList", service.listBoard());
		System.out.println("전체 목록 페이지 listAll() 성공");
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailBoard(Model model, @RequestParam("b_no") int b_no) throws Exception {
		System.out.println("게시글 번호=>"+b_no+"번의 상세 페이지");
		model.addAttribute("boardContent", service.detailBoard(b_no));
		return "board/BoardContent";
	}
	@RequestMapping("/delete")
	public String deleteboard(Model model, @RequestParam("b_no") int b_no) {
		
		return null;
		
	}
	
	

}
