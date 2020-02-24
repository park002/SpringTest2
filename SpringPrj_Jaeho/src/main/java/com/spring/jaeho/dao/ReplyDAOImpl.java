package com.spring.jaeho.dao;

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
    
    
	
}
