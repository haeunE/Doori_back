package com.example.doori.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.Movie;
import com.example.doori.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/doori/movies")
	public ResponseEntity<?> movieinit() {
		List<Movie> movieList = movieService.getMovieList();
		return new ResponseEntity<>(movieList, HttpStatus.OK);
	}
	@GetMapping("/doori/movies/{id}")
	public ResponseEntity<?> movie_detail(@PathVariable("id") Integer id){
		Optional<Movie> movieInfo = movieService.getMovie(id);
		return new ResponseEntity<>(movieInfo, HttpStatus.OK);
	}
}
