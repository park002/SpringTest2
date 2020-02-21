package com.spring.jaeho;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spring.jaeho.page.Pagination2;
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

	@RequestMapping(value = "/listAll", method = RequestMethod.GET) // �۸�� ,����¡ ó��
	public ModelAndView listAll(@RequestParam(defaultValue = "1")int curPage,@RequestParam(defaultValue = "title") String searchOption
			,@RequestParam(defaultValue = "") String keyword ) throws Exception {
		
		//���ڵ� ���� ��� 
		int count = service.getBoardListCnt(searchOption,keyword);
		
		Pagination2 pagination = new Pagination2(count, curPage); // �Խñ� ��ü ����, ���� ��������ȣ
		int start = pagination.getPageBegin();
		int end = pagination.getPageScale();
		//List<BoardDTO> list = service.listBoard(pagination);
		List<BoardDTO> list = service.listBoard(start,end,searchOption,keyword);
		
		// �����͸� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		map.put("boardPager", pagination);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("board/listAll");
		return mav;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET) // getBoardview
	public String detailBoard(Model model, @RequestParam("b_no") int b_no) throws Exception {
		System.out.println("�Խñ� ��ȣ=>" + b_no + "���� �� ������");
		model.addAttribute("boardContent", service.detailBoard(b_no));
		service.updateCount(b_no);
		return "board/BoardContent";
	}

	@RequestMapping("/delete")
	public String deleteBoard(@RequestParam("b_no") int b_no) throws Exception {
		service.deleteboard(b_no);
		System.out.println("deleteboard ()�Ϸ�");
		return "redirect:/board/listAll";
	}

	@RequestMapping("/updateBoardForm")
	public ModelAndView updateBoardForm(@RequestParam("b_no") int b_no, @ModelAttribute("upboard") BoardDTO dto)
			throws Exception { // �Խñ� ��ȣ �߳Ѿ��
		ModelAndView mav = new ModelAndView();
		BoardDTO board = service.detailBoard(b_no);
		mav.addObject("upboard", board);
		mav.setViewName("/board/update");
		return mav;
	}

	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(BoardDTO dto) throws Exception {
		service.updateboard(dto);
		System.out.println("updateBoard() ������");
		return "redirect:/board/listAll";
	}

}
