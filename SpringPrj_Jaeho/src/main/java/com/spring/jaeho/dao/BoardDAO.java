package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.BoardDTO;
import com.spring.jaeho.page.Pagination2;

public interface BoardDAO {
	// 삽입
	public void insertBoard(BoardDTO dto) throws Exception;

	// 글목록보기, 페이징처리
	public List<BoardDTO> listBoard(Pagination2 pagination) throws Exception;

	// 총게시글 개수 확인
	public int getBoardListCnt() throws Exception;

	// 글상세보기
	public BoardDTO detailBoard(int b_no) throws Exception;

	// 글삭제하기
	public void delete(int b_no) throws Exception;

	// 글수정하기
	public void update(BoardDTO dto) throws Exception;

	// 조회수 증가
	public void updateCount(int b_no) throws Exception;

}
