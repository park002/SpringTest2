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
			@RequestParam("reservation_data_out") String Date_Out,HttpSession session) { //üũ�� üũ �ƿ��� ������ �Ѵ� 
		// ���� �ѹ� ���� ==> �׸��� ���� ����Ǿ��ֳ� ���ֳ� ==> �׸��� �� ������ �������� 
		//���� ��ȣ �����ؼ� insert ���� �غ��� db�� �־�� 
	       String ReservationNumber= UUID.randomUUID().toString(); //UUID ���� �����ȣ�� ����� �� 
	     	//String userId = (String) session.getAttribute("userId");
	       //session.getAttribute("userID");
	     // dto.setM_id(userId);
	       dto.setReservation_number(ReservationNumber);
	       service.insertReservation(dto);
	       //insert => �����ȣ,��Ÿ��,����,��� �� �Ŀ� 
	       //insert �ϱ� ���� �����ؼ� ��Ÿ���� ���� ȸ���� �ְ� �׷���, üũ�� üũ�ƿ� ã��  ���� ���ϰ� ! 
	       //������ count �� ���� �ִٸ� view �ܿ��� �˼��մϴ� ���� ������ ��� �ϸ�� 
	       //��Ÿ�Կ� �´� ���ݵ� �����ͼ�  �������̺� ȸ�� ���̵� �־��ش� 
	       //�� �Ŀ� ���� ReservationCheck �� ! 
	        
	   
     	 System.out.println("DTO=====>@@@@@@@@@@" + dto.toString());
     	 
     	 
		return "/reservation/reservation"; //����Ȯ�� ���ִ� ������ ������ �ѱ���  ~ 
		
	}
	
}
