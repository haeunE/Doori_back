package com.example.doori.domain;

import java.util.Date;

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
public class Timetable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIMETABLE_ID")
	private Integer id;
	
	//상영 날짜 시간
	@Column(nullable = false, length = 50)
	private Date movieDate;
	
	//영화정보 - movieId(fk)
	@ManyToOne
	@JoinColumn(name="MOVIE_ID", nullable = false)//해당 시간에 상영하는 영화 정보가져오기
	private Movie movieId;
}
