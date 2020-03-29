package com.spring.jaeho;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.jaeho.Reservationdto.ReservationDTO;
import com.spring.jaeho.Reservationservice.ReservationService;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {
	
	@Autowired
	ReservationService service;
	
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public String reservation() {
		return "/reservation/reservation";
	}
	
	@RequestMapping(value="/reservation1",method=RequestMethod.POST)
	public String reservation(ReservationDTO dto,@RequestParam("reservation_data_in")String Date_In,
			@RequestParam("reservation_data_out") String Date_Out,HttpSession session) { //체크인 체크 아웃이 찎혀야 한다 
		// 예약 넘버 생성 ==> 그리고 방이 예약되어있나 안있나 ==> 그리고 방 가격을 가져오자 
		//예약 번호 생성해서 insert 까지 해보자 db에 넣어보자 
	       String ReservationNumber= UUID.randomUUID().toString(); //UUID 생성 예약번호로 사용할 것 
	     	//String userId = (String) session.getAttribute("userId");
	       //session.getAttribute("userID");
	     // dto.setM_id(userId);
	       dto.setReservation_number(ReservationNumber);
	       service.insertReservation(dto);
	       //insert => 예약번호,방타입,성인,어린이 한 후에 
	       //insert 하기 전에 조인해서 방타입을 가진 회원이 있고 그러면, 체크인 체크아웃 찾고  예약 못하게 ! 
	       //조인한 count 가 값이 있다면 view 단에서 죄송합니다 방의 예약이 됬다 하면됌 
	       //룸타입에 맞는 가격도 가져와서  예약테이블에 회원 아이디도 넣어준다 
	       //그 후에 리턴 ReservationCheck 로 ! 
	        
	   
     	 System.out.println("DTO=====>@@@@@@@@@@" + dto.toString());
     	 
     	 
		return "/reservation/reservation"; //예약확인 해주는 곳으로 데이터 넘기자  ~ 
		
	}
	
}
