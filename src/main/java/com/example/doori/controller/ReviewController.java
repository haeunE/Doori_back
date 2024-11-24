package com.example.doori.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.Review;
import com.example.doori.dto.ReviewDTO;
import com.example.doori.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doori")
@RequiredArgsConstructor
public class ReviewController {
	
	public final ReviewService reviewService;
	
	@PostMapping("/review")
	public ResponseEntity<?> review(@RequestBody ReviewDTO reviewDTO){
		reviewService.reviewSave(reviewDTO);
		return new ResponseEntity<>("review등록 했습니다.", HttpStatus.OK);
	}
	
	@GetMapping("/reviews")
	public ResponseEntity<?> myreviews(){
		
		return new ResponseEntity<>("",HttpStatus.OK);
	}
	
	
}
