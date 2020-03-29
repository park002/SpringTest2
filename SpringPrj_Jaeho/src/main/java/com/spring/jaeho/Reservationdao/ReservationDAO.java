package com.spring.jaeho.Reservationdao;

import com.spring.jaeho.Reservationdto.ReservationDTO;

public interface ReservationDAO {
	public void reservation_number_people(ReservationDTO dto);
    public int RoomPrice(ReservationDTO dto);
    public void reservationInsert(ReservationDTO dto);
    
}
