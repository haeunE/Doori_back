package com.example.doori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.dto.BookingDTO;
import com.example.doori.service.BookingService;

@RestController
@RequestMapping("/doori")
public class Bookingcontroller {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public ResponseEntity<?> booking(@RequestBody BookingDTO bookingDTO){
		
		bookingService.saveBooking(bookingDTO);
		return new ResponseEntity<>("예약완료 되었습니다", HttpStatus.OK);
	}
	
//	
//	@GetMapping("/reservation/seats")
//	public ResponseEntity<?> reservedSeat(){
//		
//	}

}
