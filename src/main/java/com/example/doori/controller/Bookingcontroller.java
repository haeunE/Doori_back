package com.example.doori.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.Reservation;
import com.example.doori.dto.BookingDTO;
import com.example.doori.dto.ReservationDTO;
import com.example.doori.repository.ReservationRepository;
import com.example.doori.service.BookingService;

@RestController
@RequestMapping("/doori")
public class Bookingcontroller {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ReservationRepository reservationRepository;
	//seat에 대한 method
	// booking시 reservation, seat 컬럼 생성 됨
	@PostMapping("/booking")
	public ResponseEntity<?> booking(@RequestBody BookingDTO bookingDTO){
		System.out.println(bookingDTO);
		
		bookingService.saveBooking(bookingDTO);
		return new ResponseEntity<>("예약완료 되었습니다", HttpStatus.OK);
	}
	
	// reserved seat 보여줌
	@GetMapping("/reservation/seats")
	public ResponseEntity<?> reservedSeat(@RequestParam Integer timetableId){
		
		List<String> reservedSeats = bookingService.getReservedSeat(timetableId);
		return new ResponseEntity<>(reservedSeats, HttpStatus.OK);
	}
	
	// user에 따른 예약정보에 대한 method
	@GetMapping("/myreservation")
	public ResponseEntity<?> userReservations(){
		List<ReservationDTO> rLists = bookingService.getReservationInfo();		
		return new ResponseEntity<>(rLists, HttpStatus.OK);
	}
	
	

}
