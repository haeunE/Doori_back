package com.example.doori.domain;



import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RESERVATION_ID")
	private Integer id;
	
	//결제 총가격
	@Column(nullable = false)
	private String price;
	
	//결제일
	@CreationTimestamp
	private Timestamp createDate;
	
	//회원 정보 - userId(fk)
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)//join으로 한 회원의 예약정보 가져오기 위해
	private User userId;
	
	//예약 영화 정보-tableTableId(fk)
	@ManyToOne
	@JoinColumn(name = "TIMETABLE_ID", nullable = false) // 예약한 영화 및 시간대 정보 가져오기
	private Timetable timetableId;
	
	// seat 좌석 정보를 뽑아내기 위한 양방향 관계 설정
	@OneToMany(mappedBy = "reservationId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("id desc")
	@ToString.Exclude
	@JsonIgnore
	private List<Seat> seatList;
}
