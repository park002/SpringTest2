package com.spring.jaeho.service;

import java.util.List;

import com.spring.jaeho.dto.BoardDTO;
import com.spring.jaeho.page.Pagination2;

public interface BoardService {
	public void insertBoard(BoardDTO dto) throws Exception;

	public List<BoardDTO> listBoard(Pagination2 pagination) throws Exception;

	public BoardDTO detailBoard(int b_no) throws Exception;

	// 글삭제하기
	public void deleteboard(int b_no) throws Exception;

	// 글 수정하기
	public void updateboard(BoardDTO dto) throws Exception;

	// 조회수 +1
	public void updateCount(int b_no) throws Exception;

	// 전체 게시글 개수 가져오기
	public int getBoardListCnt() throws Exception;

}
