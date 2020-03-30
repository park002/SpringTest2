package com.spring.jaeho.Reservationservice;

import java.util.List;

import com.spring.jaeho.Reservationdto.ReservationDTO;

public interface ReservationService {
public void reservation_number_people(ReservationDTO dto);

public int RoomPrice(ReservationDTO dto);
public void reservationInsert(ReservationDTO dto);
public int DuplicateFind(ReservationDTO dto);

}
