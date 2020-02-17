package com.spring.jaeho.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	private static String namespace = "com.spring.jaeho.mybatis.mapper.BoardMapper";

	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		sqlSession.insert(namespace + ".insertBoard", dto);
	}

	@Override
	public List<BoardDTO> listBoard() throws Exception {
		return sqlSession.selectList(namespace + ".listBoard");
	}

	@Override
	public BoardDTO detailBoard(int b_no) throws Exception {
		return sqlSession.selectOne(namespace + ".detailBoard", b_no);
	}

	@Override
	public void delete(int b_no) throws Exception {
		sqlSession.delete(namespace + ".delete", b_no);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		sqlSession.update(namespace + ".update", dto);

	}
	@Override
	public void updateCount(int b_no) throws Exception {
      sqlSession.update(namespace +".updateCount", b_no);
		
	}
}
