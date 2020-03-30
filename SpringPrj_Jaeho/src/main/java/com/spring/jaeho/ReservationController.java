package com.spring.jaeho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/reservation1", method = RequestMethod.POST)
	public ModelAndView reservation(ReservationDTO dto, HttpSession session) throws ParseException {
		ModelAndView mav = new ModelAndView();
		String ReservationNumber = UUID.randomUUID().toString(); // UUID 생성 예약번호로 사용할 것
		dto.setReservation_number(ReservationNumber);
		service.reservation_number_people(dto);
		int RoomPrice=service.RoomPrice(dto);
		//체크인 체크아웃 날짜간 차이를 구한다 ASD
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date beginDate = formatter.parse(dto.getReservation_data_in()); //체크인
	    Date endDate = formatter.parse(dto.getReservation_data_out()); //체크아웃
	    long diff = endDate.getTime() - beginDate.getTime(); //체크아웃 - 체크인 시간차이 뺴기 
        long diffDays = diff / (24 * 60 * 60 * 1000)+1;//날짜 계산 하루예약은 0이므로 +1해준다.
        //날짜 차이 * 룸 가격 
        int price = RoomPrice * (int)diffDays;
        dto.setPrice(price);	
        dto.setM_id("ekem159");
        //String id = (String)session.getAttribute("userId");
        //&& id !=null
       // System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+dto.toString());
        service.reservationInsert(dto);
         int duplicateFind = service.DuplicateFind(dto);
        mav.addObject("reservation_data_in",beginDate);
        mav.addObject("reservation_data_out",endDate);
        mav.addObject("adult", dto.getAdult());
        mav.addObject("child",dto.getChild());
        mav.addObject("price",price);
        mav.addObject("reservation_number",ReservationNumber);
        mav.addObject("duplicateFind", duplicateFind);
        mav.addObject("room_type",dto.getRoom_type());
        mav.addObject("m_id", dto.getM_id());
        mav.setViewName("/reservation/ReservationCheck");
         //System.out.println("가격!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+price);
        //System.out.println("날짜 차이 * 룸가격 =>"+diffDays+"*"+roomprices+"="+price);
		
		// insert => 예약번호,방타입,성인,어린이 한 후에
		// insert 하기 전에 조인해서 방타입을 가진 회원이 있고 그러면, 체크인 체크아웃 찾고 예약 못하게 !
		// 조인한 count 가 값이 있다면 view 단에서 죄송합니다 방의 예약이 됬다 하면됌
		// 룸타입에 맞는 가격도 가져와서 예약테이블에 회원 아이디도 넣어준다
		// 그 후에 리턴 ReservationCheck 로 !
		return mav;// 예약확인 해주는 곳으로 데이터 넘기자 ~
	}
	
	@RequestMapping(value = "/ReservationPay" , method = RequestMethod.GET)
	public String ReservationPay(@RequestParam ("number") String ReservationNumber) {
		System.out.println(ReservationNumber);
		//service.reservationConfirm(ReservationNumber);
		
	return null;
	
	
	}
	

}
