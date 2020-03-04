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

}
