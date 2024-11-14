package com.example.doori.domain;

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
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Integer userId;
	
	//영화정보 - movieId(fk)
	private Integer movieId;
}
