package com.example.doori.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
// 영화예매 결제 시 자동 인덱스 생성
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEAT_ID")
	private Integer id;
	
	//좌석 번호
	@Column(name="SEAT_NB", nullable = false, length = 5)
	private String seatNb;
	
	//예약번호
	@ManyToOne
	@JoinColumn(name="RESERVATION_ID", nullable = false) // 예약테이블과 조인하여 좌석 수 계산 및 남은 좌석 확인 가능
	private Reservation reservation;
}
