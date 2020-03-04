package com.spring.jaeho.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyService {
	//´ñ±Û»ðÀÔ
	public void insertReply(ReplyDTO dto);
	//´ñ±Û¸®½ºÆ®
	public List<ReplyDTO>listReply(int b_no,int start,int end,HttpSession session);
	//´ñ±Û°³¼ö
	public int countReply(int b_no);
	

}
