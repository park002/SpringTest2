package com.spring.jaeho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<BoardDTO> listBoard(int start, int end,String searchOption,String keyword) throws Exception {
		// System.out.println("SELECT * FROM board LIMIT#{pageBegin},#{pageScale}\n"
		// +pagination.getPageBegin() + ", " +pagination.getPageScale());
		// #{start} #{end} 에 입력 될 값을 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return sqlSession.selectList(namespace + ".listBoard", map);
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
		sqlSession.update(namespace + ".updateCount", b_no);

	}

	// 총 게시글 개수 확인
	@Override
	public int getBoardListCnt(String searchOption,String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne(namespace + ".getBoardListCnt",map);
	}
}
