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
		System.out.println(" /board/create �Դϴ�. GET���");
		return "/board/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDTO dto, RedirectAttributes rttr) throws Exception {
		service.insertBoard(dto);
		System.out.println("�Խ��ǵ�� ���� createPOST()");
		rttr.addFlashAttribute("msg", "����");
		return "redirect:/board/listAll"; // redirct ��ü�� �����̴� . 302
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		System.out.println(service.listBoard());
		model.addAttribute("boardList", service.listBoard());
		System.out.println("��ü ��� ������ listAll() ����");
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailBoard(Model model, @RequestParam("b_no") int b_no) throws Exception {
		System.out.println("�Խñ� ��ȣ=>"+b_no+"���� �� ������");
		model.addAttribute("boardContent", service.detailBoard(b_no));
		return "board/BoardContent";
	}
	@RequestMapping("/delete")
	public String deleteboard(Model model, @RequestParam("b_no") int b_no) {
		
		return null;
		
	}
	
	

}
