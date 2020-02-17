package com.spring.jaeho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.BoardDAO;
import com.spring.jaeho.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;

	@Override
	public void insertBoard(BoardDTO dto) throws Exception { // 삽입
		dao.insertBoard(dto);
	}

	@Override
	public List<BoardDTO> listBoard() throws Exception { // 게시글 목록

		return dao.listBoard();
	}

	@Override
	public BoardDTO detailBoard(int b_no) throws Exception {

		return dao.detailBoard(b_no);
	}
	@Override
	public void deleteboard(int b_no) throws Exception {
	   dao.delete(b_no);
		
	}
	@Override
	public void updateboard(BoardDTO dto) throws Exception {
		dao.update(dto);
		
	}
	@Override
	public void updateCount(int b_no) throws Exception {
	    dao.updateCount(b_no);
		
	}
}
