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
		System.out.println(" /board/create �Դϴ�. GET���");
		return "/board/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDTO dto) throws Exception {
//		 String writer = (String)session.getAttribute("m_id");
//	    dto.setB_writer(writer);	
		service.insertBoard(dto);
		System.out.println("�Խ��ǵ�� ���� createPOST()");
		return "redirect:/board/listAll"; // redirct ��ü�� �����̴� . 302
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET) // �۸�� ,����¡ ó��
	public ModelAndView listAll(@RequestParam(defaultValue = "1") int curPage,
			@RequestParam(required = false, defaultValue = "title") String searchOption,
			@RequestParam(required = false) String keyword, HttpSession session) throws Exception {
	
		// �������� getAttribute �޼ҵ尡 Object Ÿ�� �̹Ƿ� ����ȯ ���ش�
		// ���ڵ� ���� ���
	
		int count = service.getBoardListCnt(searchOption, keyword);
		// Pagination2 pagination = new Pagination2(); // �Խñ� ��ü ����, ���� ��������ȣ
		Pagination pagination = new Pagination(count, curPage);
		int start = pagination.getPageBegin();
		int end = pagination.getPageScale();
		// List<BoardDTO> list = service.listBoard(pagination);
		List<BoardDTO> list = service.listBoard(start, end, searchOption, keyword);
		// �����͸� �ʿ� ����
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
		int ReplyCount = replyService.countReply(b_no); //����� �� ���ϱ� . ���Ἲ �������� �ذ���1. 
		//�θ����̺��� ��Ű�� �ڽ����̺��� �ܷ�Ű�� �����.
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
		System.out.println("deleteboard ()�Ϸ�");
		return "redirect:/board/listAll";
	}


	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public String updateBoard(BoardDTO dto) throws Exception {
		service.updateboard(dto);
		System.out.println("updateBoard() ������");
		return "redirect:/board/listAll";
	}

}
