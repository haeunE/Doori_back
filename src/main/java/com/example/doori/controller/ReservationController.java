package com.example.doori.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.Movie;
import com.example.doori.filter.DateUtil;
import com.example.doori.service.MovieService;
import com.example.doori.service.ReservationService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ReservationController {
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/doori/reservation")
	public ResponseEntity<?> reservationinit() {
		List<Movie> movieList = movieService.getMovieList();
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}
	@PostMapping("/doori/reservation")
	public ResponseEntity<?> screanMovies(String request) {
		try {
			List<?> date = reservationService.getTimetableByDate(request);
			return new ResponseEntity<>(date, HttpStatus.OK);
		}catch (ParseException e) {
			return ResponseEntity.badRequest().body("Invalid date format. Please use 'yy-MM-dd'.");
		}
	}
	
}
