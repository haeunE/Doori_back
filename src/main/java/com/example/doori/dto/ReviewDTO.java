package com.example.doori.dto;

import lombok.Data;

@Data
public class ReviewDTO {
	
	private Double rating; // reviewScope임
	
	private String review; // reviewContent임 
	
	private Integer movieId; //Movie의 movieId

}
