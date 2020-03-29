package com.spring.jaeho.Reservationservice;

import com.spring.jaeho.Reservationdto.ReservationDTO;

public interface ReservationService {
public void reservation_number_people(ReservationDTO dto);

public int RoomPrice(ReservationDTO dto);
public void reservationInsert(ReservationDTO dto);

}
