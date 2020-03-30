package com.spring.jaeho.Reservationdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void reservation_number_people(ReservationDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".reservation_number_people",dto);
		
	}
	@Override
	public int RoomPrice(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne(namespace+".RoomPrice",dto);
	}
	@Override
	public void reservationInsert(ReservationDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.selectOne(namespace+".reservationInsert",dto);
	}
	@Override
	public int DuplicateFind(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".DuplicateFind",dto);
	}
	
  

	
}
