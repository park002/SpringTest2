package com.spring.jaeho;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.service.ReplyService;

@RestController
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	ReplyService service;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertReply(ReplyDTO dto, HttpSession session) {// Ä¿¸Çµå°´Ã¼
		String userId = (String) session.getAttribute("userId");
		dto.setReplyer(userId);
		System.out.println("´ñ±ÛÀÛ¼ºÀÚ ===================>"+dto.getReplyer());
		service.insertReply(dto);
	}

	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
	public ModelAndView listReply(@RequestParam("b_no") int b_no) {
		List<ReplyDTO> list = service.listReply(b_no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/reply/replyList");
		return mav;
	}

	@RequestMapping(value = "/listJson")
	public List<ReplyDTO> listJson(@RequestParam("b_no") int b_no) {
		List<ReplyDTO> list = service.listReply(b_no);
		System.out.println(list);
		return list;

	}

}
