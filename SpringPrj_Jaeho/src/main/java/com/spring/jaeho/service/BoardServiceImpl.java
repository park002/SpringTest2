package com.spring.jaeho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.BoardDAO;
import com.spring.jaeho.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;

	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		dao.insertBoard(dto);

	}
}
