package com.example.doori.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.doori.domain.Movie;
import com.example.doori.repository.MovieRepository;


@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;

	public List<Movie> getMovieList(){
		return movieRepository.findAllByOrderByIdDesc();
	}
	public Optional<Movie> getMovie(Integer id) {
		return movieRepository.findById(id);
	}
	
}
