package com.spring.jaeho.dao;

import java.util.List;


import com.spring.jaeho.dto.BoardDTO;

import com.spring.jaeho.page.Pagination;


public interface BoardDAO {
	// ����
	public void insertBoard(BoardDTO dto) throws Exception;

	// �۸�Ϻ���, ����¡ó��
	public List<BoardDTO> listBoard(int start,int end,String searchOption, String keyword) throws Exception;

	// �ѰԽñ� ���� Ȯ��
	public int getBoardListCnt(String searchOption, String keyword) throws Exception;

	// �ۻ󼼺���
	public BoardDTO detailBoard(int b_no) throws Exception;

	// �ۻ����ϱ�
	public void delete(int b_no) throws Exception;

	// �ۼ����ϱ�
	public void update(BoardDTO dto) throws Exception;

	// ��ȸ�� ����
	public void updateCount(int b_no) throws Exception;

}
