package com.example.doori.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MOVIE_ID")
	private Integer id;
	
	//포스터
	@Column(nullable = false, unique=true, columnDefinition = "TEXT")
	private String moviePoster;
	
	//시간(분)
	@Column(nullable = false)
	private String runningtime;
	
	//제목
	@Column(nullable = false, unique=true, length = 500)
	private String title;
	
	//감독
	@Column(nullable = false, length = 50)
	private String director;
	
	//배우
	@Column(nullable = false)
	@Size(max=1000)
	private String actor;
	
	//줄거리
	@Column(nullable = false, columnDefinition = "TEXT")
	private String plot;
	
	//개봉일
	@Column(nullable = false, length = 50)
	private String releaseDte;
	
	//장르
	@Column(nullable = false, length = 20)
	private String genre;
	
	//심의여부
	@Column(nullable = false, length = 30)
	@ColumnDefault("'all'")
	private String ratedYn;
}
