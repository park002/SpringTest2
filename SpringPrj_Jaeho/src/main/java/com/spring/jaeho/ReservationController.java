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
import org.springframework.web.servlet.ModelAndView;

import com.spring.jaeho.Reservationdto.ReservationDTO;
import com.spring.jaeho.Reservationservice.ReservationService;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {
	@Autowired
	ReservationService service;
 //�����ϱ� ��ũ ������!!!!!!!!!!!!!!!����
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public String reservation(HttpSession session, ReservationDTO dto,Model model) {
		dto.setM_id("ekem159");
		String confirmation_payment = service.PayCheck(dto.getM_id()); 
		model.addAttribute("confirmation_payment",confirmation_payment);
		return "/reservation/reservation";
	}

	// �����ϱ� ��ư������
	@RequestMapping(value = "/reservation1", method = RequestMethod.POST)
	public String reservation(ReservationDTO dto, HttpSession session,Model model) throws ParseException {
		dto.setM_id("ekem159");
		String ReservationNumber = UUID.randomUUID().toString(); // UUID ���� �����ȣ�� ����� ��
		dto.setReservation_number(ReservationNumber);
		service.reservation_number_people(dto);
		int RoomPrice = service.RoomPrice(dto);
		// üũ�� üũ�ƿ� ��¥�� ���̸� ���Ѵ� 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = formatter.parse(dto.getReservation_data_in()); // üũ��
		Date endDate = formatter.parse(dto.getReservation_data_out()); // üũ�ƿ�
		long diff = endDate.getTime() - beginDate.getTime(); // üũ�ƿ� - üũ�� �ð����� ����
		long diffDays = diff / (24 * 60 * 60 * 1000) + 1;// ��¥ ��� �Ϸ翹���� 0�̹Ƿ� +1���ش�.
		// ��¥ ���� * �� ����
		int price = RoomPrice * (int) diffDays;
		dto.setPrice(price);
		service.reservationInsert(dto);
		int duplicateFind = service.DuplicateFind(dto);
		model.addAttribute("reservation_data_in",beginDate);
		model.addAttribute("reservation_data_out",endDate);
		model.addAttribute("adult",dto.getAdult());
		model.addAttribute("child",dto.getChild());
		model.addAttribute("price",dto.getPrice());
		model.addAttribute("reservation_number",dto.getReservation_number());
		model.addAttribute("duplicateFind",duplicateFind);
		model.addAttribute("room_type",dto.getRoom_type());
		model.addAttribute("m_id",dto.getM_id());
		return "/reservation/ReservationCheck";
	}
	// �����ϱ�
	@RequestMapping(value = "/ReservationPay", method = RequestMethod.GET)
	public String ReservationPay(@RequestParam ("number") String number,Model model) {
		service.PayCheckUpdate(number);
		return "/index";
	}

	// ���� ��ȸ/���
	@RequestMapping(value = "/ReservationSelect", method = RequestMethod.GET)
	public String ReservationSelect(HttpSession session, ReservationDTO dto, Model model) { // �ϴ� ���� �����Դ� ġ��
		dto.setM_id("ekem159");
		
		
		model.addAttribute("SelectNumber", dto);
		return "/reservation/ReservationSelect";

	}

}
