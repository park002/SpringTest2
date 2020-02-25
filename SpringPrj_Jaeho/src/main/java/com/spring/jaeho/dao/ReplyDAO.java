package com.spring.jaeho.dao;

import java.util.List;

import com.spring.jaeho.dto.ReplyDTO;

public interface ReplyDAO {
public void insertReply(ReplyDTO dto);
public List<ReplyDTO> listReply(int b_no);


}
