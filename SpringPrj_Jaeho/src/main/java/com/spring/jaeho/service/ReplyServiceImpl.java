package com.spring.jaeho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.ReplyDAO;
import com.spring.jaeho.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDAO dao;

	@Override
	public void insertReply(ReplyDTO dto) {
		dao.insertReply(dto);

	}

}
