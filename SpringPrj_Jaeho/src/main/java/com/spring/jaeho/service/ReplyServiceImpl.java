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

	@Override // ��� ����
	public void insertReply(ReplyDTO dto) {
		dao.insertReply(dto);
	}

	@Override // ��۸���Ʈ
	public List<ReplyDTO> listReply(int b_no, int start, int end, HttpSession session) {
		
		List<ReplyDTO> items = dao.listReply(b_no, start, end);
		String userId = (String) session.getAttribute("userId");

		for (ReplyDTO dto : items) {
			System.out.println(dto);
			// ��۸���߿� ��д���� �ִ� ���
			if (dto.getB_secret_reply().equals("y")) {
				if (userId == null) { // �α����� �Ǿ����� �ʴٸ�.
					dto.setReplytext("��� ����Դϴ�");
				} 
				else {// �α��λ����ϰ��
					String writer = dto.getReplyer(); // ����ۼ��� ����
					if (!userId.equals(writer)) { // userId �� ����ۼ��ڰ� �ٸ��ٸ�
						dto.setReplytext("��д���Դϴ�");
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
