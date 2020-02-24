package com.spring.jaeho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jaeho.dto.ReplyDTO;
import com.spring.jaeho.service.ReplyService;

@Controller
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	ReplyService service;

	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertReply(ReplyDTO dto) {//Ŀ�ǵ尴ü
		service.insertReply(dto);
	}

}
