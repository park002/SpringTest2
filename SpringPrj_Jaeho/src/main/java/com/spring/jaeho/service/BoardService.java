package com.spring.jaeho.service;

import java.util.List;

import com.spring.jaeho.dto.BoardDTO;

public interface BoardService {
	 public void insertBoard(BoardDTO dto) throws Exception;
	 public List<BoardDTO> listBoard() throws Exception;
	 public BoardDTO detailBoard(int b_no) throws Exception;
	 //�ۻ����ϱ�
	 public void deleteboard(int b_no) throws Exception;
	 //�� �����ϱ�
	 public void updateboard(BoardDTO dto) throws Exception;
	 

}
