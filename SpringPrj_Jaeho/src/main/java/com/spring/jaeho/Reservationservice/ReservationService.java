package com.spring.jaeho.Reservationservice;

import java.util.Date;

import com.spring.jaeho.Reservationdto.ReservationDTO;

public interface ReservationService {
	public void reservation_number_people(ReservationDTO dto);

	public int RoomPrice(ReservationDTO dto);

	public void reservationInsert(ReservationDTO dto);

	public int DuplicateFind(ReservationDTO dto);

	public String PayCheck(String m_id);

	public void PayCheckUpdate(String number);

}
