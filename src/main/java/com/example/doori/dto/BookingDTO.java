package com.example.doori.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BookingDTO {
	
	// 가격
	private String price;
	// 영화 예약정보있는 컬럼의 id
	private Integer timetableId;
	// 좌석 정보
	private List<String> seatNb;

}
