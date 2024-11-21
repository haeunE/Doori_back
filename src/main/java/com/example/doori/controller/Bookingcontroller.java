package com.example.doori.controller;

import java.util.List;

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
	
	//seat에 대한 method
	// booking시 reservation, seat 컬럼 생성 됨
	@PostMapping("/booking")
	public ResponseEntity<?> booking(@RequestBody BookingDTO bookingDTO){
		
		bookingService.saveBooking(bookingDTO);
		return new ResponseEntity<>("예약완료 되었습니다", HttpStatus.OK);
	}
	
	// reserved seat 보여줌
	@GetMapping("/reservation/seats")
	public ResponseEntity<?> reservedSeat(@RequestBody Integer timetableId){
		List<String> reservedSeat = bookingService.getReservedSeat(timetableId);
		return new ResponseEntity<>(reservedSeat, HttpStatus.OK);
	}
	
	// 예약정보에 대한 method
	
	
	

}
