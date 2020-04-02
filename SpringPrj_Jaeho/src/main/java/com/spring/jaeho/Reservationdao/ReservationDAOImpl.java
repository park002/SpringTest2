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
		sqlSession.insert(namespace + ".reservation_number_people", dto);

	}

	@Override
	public int RoomPrice(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".RoomPrice", dto);
	}

	@Override
	public void reservationInsert(ReservationDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.selectOne(namespace + ".reservationInsert", dto);
	}

	@Override
	public int DuplicateFind(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".DuplicateFind", dto);
	}

	@Override
	public String PayCheck(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".PayCheck", m_id);
	}

	@Override
	public void PayCheckUpdate(String number) {
		// TODO Auto-generated method stub
		sqlSession.selectOne(namespace + ".PayCheckUpdate", number);

	}

	@Override
	public void ReservationDelete() {
		// TODO Auto-generated method stub
		sqlSession.selectOne(namespace + ".ReservationDelete");
		
	}
	@Override
	public ReservationDTO ReservationSelect(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".ReservationSelect", dto);
	}
	@Override
	public String SearchPW(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".SearchPW",m_id);
	}

}
