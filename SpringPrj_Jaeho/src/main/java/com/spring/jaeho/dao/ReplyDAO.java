package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyDAO {
	//����Է�
public void insertReply(ReplyDTO dto);
 //��� ���
public List<ReplyDTO> listReply(int b_no,int start,int end);
//��۰���
public int countReply(int b_no);
//��ۼ���
public void replyModify(ReplyDTO dto);
//��ۻ󼼺���
public ReplyDTO replyDetail(int r_no);
//��ۻ���
public void replydelete(int r_no);
}
