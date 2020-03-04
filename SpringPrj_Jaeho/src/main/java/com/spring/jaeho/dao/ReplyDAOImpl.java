package com.spring.jaeho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	SqlSession sqlSession;
	private static String namespace = "com.spring.jaeho.mybatis.mapper.ReplyMapper";

	@Override
	public void insertReply(ReplyDTO dto) {
		sqlSession.insert(namespace + ".insertReply", dto);
	}

	@Override
	public List<ReplyDTO> listReply(int b_no, int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList(namespace + ".listReply", map);
	}

	@Override
	public int countReply(int b_no) {
		return sqlSession.selectOne(namespace + ".countReply", b_no);
	}

}
