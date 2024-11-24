package com.example.doori.dto;

import java.sql.Timestamp;
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
	private String title; // movie 내부
	private String runningtime; // movie 내부
	private Date movieDate; // timetable 내부 - 영화 시작 시간
	private Integer movieId; // review에 보내줄 movie정보들 보내 줘야 함
	
	
	// 예약 정보
	private Integer timetableId; // reservation 내부
	private Timestamp createDate; // 결제일
	private String price; // reservation 내부
	private List<String> seatNm; // seat 내부
	
	private List<Integer> reservationId;
	
}
