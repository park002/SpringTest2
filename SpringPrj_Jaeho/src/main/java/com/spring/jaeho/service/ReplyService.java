package com.spring.jaeho.service;

import java.util.List;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyService {
	//��ۻ���
	public void insertReply(ReplyDTO dto);
	//��۸���Ʈ
	public List<ReplyDTO>listReply(int b_no);
	

}
