package com.spring.jaeho.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.dao.ReplyDAO;
import com.spring.jaeho.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDAO dao;

	@Override // 댓글 삽입
	public void insertReply(ReplyDTO dto) {
		dao.insertReply(dto);
	}

	@Override // 댓글리스트
	public List<ReplyDTO> listReply(int b_no, int start, int end, HttpSession session) {
		
		List<ReplyDTO> items = dao.listReply(b_no, start, end);
		String userId = (String) session.getAttribute("userId");

		for (ReplyDTO dto : items) {
			System.out.println(dto);
			// 댓글목록중에 비밀댓글이 있는 경우
			if (dto.getB_secret_reply().equals("y")) {
				if (userId == null) { // 로그인이 되어있지 않다면.
					dto.setReplytext("비밀 댓글입니다");
				} 
				else {// 로그인상태일경우
					String writer = dto.getReplyer(); // 댓글작성자 저장
					if (!userId.equals(writer)) { // userId 와 댓글작성자가 다르다면
						dto.setReplytext("비밀댓글입니다");
					}
				}
			}
		}
		return items;
	}

	@Override
	public int countReply(int b_no) {
		// TODO Auto-generated method stub
		return dao.countReply(b_no);
	}

}
