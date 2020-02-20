package com.spring.jaeho;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(value = "/listAll", method = RequestMethod.GET) // 글목록 ,페이징 처리
	public ModelAndView listAll(@RequestParam(defaultValue = "1")int curPage ) throws Exception {
		//HttpServletRequest request
//		PageMaker pagemaker = new PageMaker();
//		int totalCount = service.getBoardListCnt();
//		String curPageNum = request.getParameter("curPageNum");
//		String contentnum = request.getParameter("contentNum");
//		int cpagenum = Integer.parseInt(curPageNum);
//		int ccontentnum = Integer.parseInt(contentnum);
//		pagemaker.setTotalCount(totalCount); //전체 게시글 개수 지정
//		pagemaker.setCurPageNum(cpagenum-1);//현재 페이지를 페이지객체에 지정한다  -1을 해야 쿼리에서 사용가능
//		pagemaker.setContentNum(ccontentnum);//한페이지에 몇개씩 게시글을 보여줄건지  지정한다 . 10
//		pagemaker.setCurrentblock(cpagenum);//현재 페이지 블럭이  몇번인지 현재 페이지 번호를 통해서 지정한다 
//		pagemaker.setLastblock(pagemaker.getTotalCount());//마지막블록 번호를 전체 게시글 수를 통해서 정한다 
//		pagemaker.prevnext(cpagenum); //현재 페이지번호로 화살표를 나타낼지 말지 정한다 .
//		pagemaker.setStartPage(pagemaker.getCurrentblock());//시작페이지를 페이지 블록 번호로 정한다 
//		pagemaker.setEndPage(pagemaker.getLastblock(),pagemaker.getCurrentblock()); 
//		
		//마지막 페이지를 마지막  페이지 블록과  현재 페이지 블록 번호로 정한다 .
//		return "/board/listAll";
		//레코드 갯수 계산 
		int count = service.getBoardListCnt();
		
		Pagination2 pagination = new Pagination2(count, curPage); // 게시글 전체 갯수, 현재 페이지번호
		int start = pagination.getPageBegin();
		int end = pagination.getPageEnd();
		//		SELECT * FROM board LIMIT
		 //#{pageBegin},#{pageEnd}
		//List<BoardDTO> list = service.listBoard(pagination);
		List<BoardDTO> list = service.listBoard(pagination);
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count", count);
		map.put("boardPager", pagination);
		
		System.out.println(pagination);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("board/listAll");
		return mav;
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
