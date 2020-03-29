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
	public String reservation(ReservationDTO dto, HttpSession session) throws ParseException {
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
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+dto.toString());
        service.reservationInsert(dto);
        
    
        
         //System.out.println("����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+price);
        //System.out.println("��¥ ���� * �밡�� =>"+diffDays+"*"+roomprices+"="+price);
		
		// insert => �����ȣ,��Ÿ��,����,��� �� �Ŀ�
		// insert �ϱ� ���� �����ؼ� ��Ÿ���� ���� ȸ���� �ְ� �׷���, üũ�� üũ�ƿ� ã�� ���� ���ϰ� !
		// ������ count �� ���� �ִٸ� view �ܿ��� �˼��մϴ� ���� ������ ��� �ϸ��
		// ��Ÿ�Կ� �´� ���ݵ� �����ͼ� �������̺� ȸ�� ���̵� �־��ش�
		// �� �Ŀ� ���� ReservationCheck �� !
		return "/reservation/reservation"; // ����Ȯ�� ���ִ� ������ ������ �ѱ��� ~

	}

}
