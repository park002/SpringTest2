package com.spring.jaeho.dao;

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
		    sqlSession.insert(namespace+".insertBoard", dto);
	}
}
