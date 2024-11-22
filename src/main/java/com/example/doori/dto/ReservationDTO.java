package com.example.doori.dto;

import java.util.Date;
import java.util.List;

import com.example.doori.domain.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
	
	// 영화 정보
	private String moviePoster;// movie 내부 
	private String titile; // movie 내부
	private String runningtime; // movie 내부
	private Date movieDate; // timetable 내부 - 영화 시작 시간
	
	
	// 예약 정보
	private Integer reservationId; // reservation 내부
	private String price; // reservation 내부
	private List<String> seatNm; // seat 내부
	
}
