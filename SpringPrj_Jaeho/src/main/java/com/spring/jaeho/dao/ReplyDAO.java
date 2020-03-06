package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyDAO {
	//´ñ±ÛÀÔ·Â
public void insertReply(ReplyDTO dto);
 //´ñ±Û ¸ñ·Ï
public List<ReplyDTO> listReply(int b_no,int start,int end);
//´ñ±Û°¹¼ö
public int countReply(int b_no);
//´ñ±Û¼öÁ¤
public void replyModify(ReplyDTO dto);
//´ñ±Û»ó¼¼º¸±â
public ReplyDTO replyDetail(int r_no);
//´ñ±Û»èÁ¦
public void replydelete(int r_no);
}
