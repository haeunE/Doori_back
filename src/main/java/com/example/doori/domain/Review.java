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
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_ID")
	private Long id;
	
	//평점
	@Column(nullable = false)
	private Double reviewScope;
	
	//관람평
	private String reviewContent;
	
	//작성일
	@Column(nullable = false)
	private String createDate;
	
	//작성자 - userId(fk)
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User userId;
	
	//영화정보 - movieId(fk)
	@ManyToOne
	@JoinColumn(name="MOVIE_ID", nullable = false)
	private Movie movieId;
}
