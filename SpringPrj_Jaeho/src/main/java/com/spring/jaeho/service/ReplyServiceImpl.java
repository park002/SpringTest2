package com.spring.jaeho.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.ReplyDAO;
import com.spring.jaeho.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDAO dao;

	@Override // ´ñ±Û »ðÀÔ
	public void insertReply(ReplyDTO dto) {
		dao.insertReply(dto);
	}

	@Override // ´ñ±Û¸®½ºÆ®
	public List<ReplyDTO> listReply(int b_no, int start, int end, HttpSession session) {
		List<ReplyDTO> items = dao.listReply(b_no, start, end);
		String userId = (String) session.getAttribute("userId");
		return items;
	}

	@Override
	public int countReply(int b_no) {
		// TODO Auto-generated method stub
		return dao.countReply(b_no);
	}

	@Override
	public void replyModify(ReplyDTO dto) {
		dao.replyModify(dto);
	}

	@Override
	public ReplyDTO replyDetail(int r_no) {
		
		return dao.replyDetail(r_no);
	}
	@Override
	public void replydelete(int r_no) {
	    dao.replydelete(r_no);
		
	}
}
