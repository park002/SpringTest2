package com.spring.jaeho.Reservationdao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.jaeho.Reservationdto.ReservationDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	private static String namespace = "com.spring.jaeho.mybatis.mapper.ReservationMapper";
	
	@Override
	public void insertReservation(ReservationDTO dto) {
		// TODO Auto-generated method stub
		System.out.println("dto========DAO= ====>"+dto);
		sqlSession.insert(namespace+".insertReservation"+dto);
		
	}
	
}
