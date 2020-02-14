package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.BoardDTO;

public interface BoardDAO {
	//삽입
	 public void insertBoard(BoardDTO dto) throws Exception;
   //글목록보기 
	 public List<BoardDTO> listBoard() throws Exception;
	//글상세보기
	 public BoardDTO detailBoard(int b_no) throws Exception;
   //글삭제하기
	 public void delete(int b_no) throws Exception;
	 




}
