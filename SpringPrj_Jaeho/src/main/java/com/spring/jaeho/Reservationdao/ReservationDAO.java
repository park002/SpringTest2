package com.spring.jaeho.Reservationdao;

import java.util.List;

import com.spring.jaeho.Reservationdto.ReservationDTO;

public interface ReservationDAO {
	public void reservation_number_people(ReservationDTO dto);
    public int RoomPrice(ReservationDTO dto);
    public void reservationInsert(ReservationDTO dto);
    public int DuplicateFind(ReservationDTO dto);

    public String PayCheck(String m_id);
    
    public void PayCheckUpdate(String number);
    
    
 
    
}
