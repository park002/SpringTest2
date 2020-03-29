package com.spring.jaeho.Reservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jaeho.Reservationdao.ReservationDAO;
import com.spring.jaeho.Reservationdto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDAO dao;

	@Override
	public void insertReservation(ReservationDTO dto) {
		// TODO Auto-generated method stub
      dao.insertReservation(dto);
	}

}
