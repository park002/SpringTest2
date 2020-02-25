package com.spring.jaeho.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
    @Autowired
	SqlSession sqlSession;
    private static String namespace="com.spring.jaeho.mybatis.mapper.ReplyMapper";
    
    @Override
    public void insertReply(ReplyDTO dto) {
    	sqlSession.insert(namespace+".insertReply",dto);
    }
    @Override
    public List<ReplyDTO> listReply(int b_no) {
    	return sqlSession.selectList(namespace+".listReply",b_no);
    }
    
    
	
}
