package com.spring.jaeho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.service.ReplyService;


@Controller
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	ReplyService service;

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertReply(ReplyDTO dto) {// Ŀ�ǵ尴ü
		service.insertReply(dto);
	}
	
	
	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
	public ModelAndView listReply(@RequestParam("b_no")int b_no ) { 
		List<ReplyDTO> list = service.listReply(b_no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/reply/replyList");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/listJson")
	public List<ReplyDTO> listJson(@RequestParam("b_no") int b_no) {
		List<ReplyDTO> list = service.listReply(b_no);
		System.out.println(list);
		return list;
		
	}

}
