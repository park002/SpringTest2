package com.spring.jaeho;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.jaeho.dto.BoardDTO;
import com.spring.jaeho.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	BoardService service;

//	@RequestMapping("/create")
//	public String joinForm(BoardDTO dto) {
//		System.out.println(" /board/create 입니다. GET방식");
//		return "create";
//	}

	@RequestMapping(value = "/createform", method = RequestMethod.GET)
	public String createGET(BoardDTO dto) throws Exception {
		System.out.println(" /board/create 입니다. GET방식");
		return "create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDTO dto, Model model) throws Exception {
		
		service.insertBoard(dto);
    
		model.addAttribute("result", "성공");
		return "success";

	}

}
