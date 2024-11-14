package com.example.doori.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resesrvation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//결제 총가격
	@Column(nullable = false)
	private String price;
	
	//결제일
	@Column(nullable = false)
	private Date createDate;
	
	//회원 정보 - userId(fk)
	private Integer memberId;
	
	//예약 영화 정보-tableTableId(fk)
	private Integer timetableId;
}
