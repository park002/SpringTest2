package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.BoardDTO;

public interface BoardDAO {
	//����
	 public void insertBoard(BoardDTO dto) throws Exception;
   //�۸�Ϻ��� 
	 public List<BoardDTO> listBoard() throws Exception;
	//�ۻ󼼺���
	 public BoardDTO detailBoard(int b_no) throws Exception;
   //�ۻ����ϱ�
	 public void delete(int b_no) throws Exception;
	 




}
