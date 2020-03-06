package com.spring.jaeho.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyService {
	//��ۻ���
	public void insertReply(ReplyDTO dto);
	//��۸���Ʈ
	public List<ReplyDTO>listReply(int b_no,int start,int end,HttpSession session);
	//��۰���
	public int countReply(int b_no);
	//��ۼ���
	public void replyModify(ReplyDTO dto);
	//��ۻ󼼺���
	public ReplyDTO replyDetail(int r_no);
    //��ۻ���
	public void replydelete(int r_no);
	
}
