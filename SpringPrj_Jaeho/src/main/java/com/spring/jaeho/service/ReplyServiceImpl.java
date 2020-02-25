package com.spring.jaeho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.ReplyDAO;
import com.spring.jaeho.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDAO dao;

	@Override //��� ����
	public void insertReply(ReplyDTO dto) {
		dao.insertReply(dto);
	}

	@Override //��۸���Ʈ
	public List<ReplyDTO> listReply(int b_no) {
		return dao.listReply(b_no);
	}

}
