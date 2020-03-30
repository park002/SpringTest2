package com.spring.jaeho.Reservationservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.Reservationdao.ReservationDAO;
import com.spring.jaeho.Reservationdto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDAO dao;

	@Override
	public void reservation_number_people(ReservationDTO dto) {
		// TODO Auto-generated method stub
      dao.reservation_number_people(dto);
	}
	@Override
	public int RoomPrice(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return dao.RoomPrice(dto);
	}
	@Override
	public void reservationInsert(ReservationDTO dto) {
		// TODO Auto-generated method stub
		dao.reservationInsert(dto);
	}
	@Override
	public int DuplicateFind(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return dao.DuplicateFind(dto);
	}

}
