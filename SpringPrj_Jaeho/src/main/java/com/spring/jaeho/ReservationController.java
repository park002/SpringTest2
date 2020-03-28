package com.spring.jaeho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reservation/")
public class ReservationController {
   
	@RequestMapping(value = "/rrr", method = RequestMethod.GET)
	public String reservation() {
		
		return "/reservation/reservation";
		
	}
	
}
