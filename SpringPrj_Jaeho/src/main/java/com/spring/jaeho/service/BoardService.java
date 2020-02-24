package com.spring.jaeho.service;

import java.util.List;


import com.spring.jaeho.dto.BoardDTO;

import com.spring.jaeho.page.Pagination;

public interface BoardService {
	public void insertBoard(BoardDTO dto) throws Exception;

	public List<BoardDTO> listBoard(int start,int end,String searchOption, String keyword) throws Exception;

	public BoardDTO detailBoard(int b_no) throws Exception;

	// �ۻ����ϱ�
	public void deleteboard(int b_no) throws Exception;

	// �� �����ϱ�
	public void updateboard(BoardDTO dto) throws Exception;

	// ��ȸ�� +1
	public void updateCount(int b_no) throws Exception;

	// ��ü �Խñ� ���� ��������
	public int getBoardListCnt(String searchOption, String keyword) throws Exception;

}