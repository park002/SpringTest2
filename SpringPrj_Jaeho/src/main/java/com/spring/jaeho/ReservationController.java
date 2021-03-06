package com.spring.jaeho;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.Reservationdto.ReservationDTO;
import com.spring.jaeho.Reservationservice.ReservationService;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {
	@Autowired
	ReservationService service;

	// 예약하기 링크 누르면!!!!!!!!!!!!!!!여기
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public String reservation(HttpSession session, ReservationDTO dto, Model model) {
		dto.setM_id("admin");
		String confirmation_payment = service.PayCheck(dto.getM_id());
		System.out.println("null이여야한다...." +confirmation_payment);
		model.addAttribute("confirmation_payment", confirmation_payment);
		return "/reservation/reservation";
	}

	// 예약하기 버튼누르면
	@RequestMapping(value = "/reservation1", method = RequestMethod.POST)
	public String reservation(ReservationDTO dto, HttpSession session, Model model) throws ParseException {
		dto.setM_id("admin");

		int duplicateFind = service.DuplicateFind(dto);
		if (duplicateFind >= 1) {
			model.addAttribute("duplicateFind", duplicateFind);
			return "/reservation/ReservationCheck";
		}

		String ReservationNumber = UUID.randomUUID().toString(); // UUID 생성 예약번호로 사용할 것
		dto.setReservation_number(ReservationNumber);
		service.reservation_number_people(dto);
		int RoomPrice = service.RoomPrice(dto);
		// 체크인 체크아웃 날짜간 차이를 구한다
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = formatter.parse(dto.getReservation_data_in()); // 체크인
		Date endDate = formatter.parse(dto.getReservation_data_out()); // 체크아웃
		long diff = endDate.getTime() - beginDate.getTime(); // 체크아웃 - 체크인 시간차이 뺴기
		long diffDays = diff / (24 * 60 * 60 * 1000) + 1;// 날짜 계산 하루예약은 0이므로 +1해준다.
		// 날짜 차이 * 룸 가격
		int price = RoomPrice * (int) diffDays;
		dto.setPrice(price);
		System.out.println("@@@@" + dto.toString());
		service.reservationInsert(dto);
		// model.addAttribute("reservation", dto);
		model.addAttribute("reservation_data_in", beginDate);
		model.addAttribute("reservation_data_out", endDate);
		// model.addAttribute("duplicateFind",duplicateFind);
		model.addAttribute("adult", dto.getAdult());
		model.addAttribute("child", dto.getChild());
		model.addAttribute("price", dto.getPrice());
		model.addAttribute("reservation_number", dto.getReservation_number());
		model.addAttribute("room_type", dto.getRoom_type());
		model.addAttribute("m_id", dto.getM_id());
		return "/reservation/ReservationCheck";
	}
	// 결제하기
	@RequestMapping(value = "/ReservationPay", method = RequestMethod.GET)
	public String ReservationPay(@RequestParam("number") String number, Model model) {
		service.PayCheckUpdate(number);
		return "/index";
	}
	// 예약 조회/취소 링크 클릭 했을 경우 
	@RequestMapping(value = "/ReservationSelect", method = RequestMethod.GET)
	public String ReservationSelect(HttpSession session, ReservationDTO dto, Model model) {
		// 일단 세션 가져왔다 치자
		dto.setM_id("admin");
		String confirmation_payment = service.PayCheck(dto.getM_id());
		System.out.println("결제 했는지 안했는지==>"+confirmation_payment);
		dto = service.ReservationSelect(dto);
	
		model.addAttribute("confirmation_payment", confirmation_payment);
		model.addAttribute("dto", dto);
		return "/reservation/ReservationSelect";
		
	}
	// 예약취소
	@RequestMapping(value = "/ReservationCancel", method = RequestMethod.POST)
	public String ReservationCancel() {
		service.ReservationDelete();
		return "/index";
	}
	
	//비밀번호 체크 
    @ResponseBody
    @RequestMapping(value="/ReservationPasswordCheck", method=RequestMethod.POST)
	public boolean PasswordCheck(@RequestParam ("m_id") String m_id , @RequestParam ("m_password") String m_password) {
    	String SearchPW = service.SearchPW(m_id);
    	if(SearchPW.equals(m_password)) {
    		service.ReservationDelete();
    		return true;
    	}
    	else  return false;
	}
	
	

}
