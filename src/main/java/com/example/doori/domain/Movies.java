package com.example.doori.domain;

import java.sql.Time;

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
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//포스터
	@Column(nullable = false, unique=true)
	private String moviePoster;
	
	//시간(분)
	@Column(nullable = false)
	private String movieRunningtime;
	
	//제목
	@Column(nullable = false, unique=true)
	private String title;
	
	//감독
	@Column(nullable = false)
	private String director;
	
	//배우
	@Column(nullable = false)
	private String actor;
	
	//줄거리
	@Column(nullable = false)
	private String polt;
	
	//개봉일
	@Column(nullable = false)
	private String releaseDte;
	
	//장르
	@Column(nullable = false)
	private String genre;
	
	//심의여부
	@Column(nullable = false)
	private String ratedYn;
}
