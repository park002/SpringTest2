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
		String ReservationNumber = UUID.randomUUID().toString(); // UUID ���� �����ȣ�� ����� ��
		dto.setReservation_number(ReservationNumber);
		service.reservation_number_people(dto);
		int RoomPrice=service.RoomPrice(dto);
		//üũ�� üũ�ƿ� ��¥�� ���̸� ���Ѵ� ASD
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date beginDate = formatter.parse(dto.getReservation_data_in()); //üũ��
	    Date endDate = formatter.parse(dto.getReservation_data_out()); //üũ�ƿ�
	    long diff = endDate.getTime() - beginDate.getTime(); //üũ�ƿ� - üũ�� �ð����� ���� 
        long diffDays = diff / (24 * 60 * 60 * 1000)+1;//��¥ ��� �Ϸ翹���� 0�̹Ƿ� +1���ش�.
        //��¥ ���� * �� ���� 
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
         //System.out.println("����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+price);
        //System.out.println("��¥ ���� * �밡�� =>"+diffDays+"*"+roomprices+"="+price);
		
		// insert => �����ȣ,��Ÿ��,����,��� �� �Ŀ�
		// insert �ϱ� ���� �����ؼ� ��Ÿ���� ���� ȸ���� �ְ� �׷���, üũ�� üũ�ƿ� ã�� ���� ���ϰ� !
		// ������ count �� ���� �ִٸ� view �ܿ��� �˼��մϴ� ���� ������ ��� �ϸ��
		// ��Ÿ�Կ� �´� ���ݵ� �����ͼ� �������̺� ȸ�� ���̵� �־��ش�
		// �� �Ŀ� ���� ReservationCheck �� !
		return mav;// ����Ȯ�� ���ִ� ������ ������ �ѱ��� ~
	}
	
	@RequestMapping(value = "/ReservationPay" , method = RequestMethod.GET)
	public String ReservationPay(@RequestParam ("number") String ReservationNumber) {
		System.out.println(ReservationNumber);
		//service.reservationConfirm(ReservationNumber);
		
	return null;
	
	
	}
	

}
