package com.example.doori.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.Movie;
import com.example.doori.domain.Review;
import com.example.doori.domain.User;
import com.example.doori.dto.ReviewDTO;
import com.example.doori.repository.MovieRepository;
import com.example.doori.service.MovieService;
import com.example.doori.service.ReviewService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/doori")
@RequiredArgsConstructor
public class ReviewController {
	@Autowired
	public final ReviewService reviewService;
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/review")
	public ResponseEntity<?> review(@RequestBody ReviewDTO reviewDTO){
		reviewService.reviewSave(reviewDTO);
		return new ResponseEntity<>("review등록 했습니다.", HttpStatus.OK);
	}
	
	@GetMapping("/movies/20/reviews")
    public ResponseEntity<?> myreviews( ) {
//        System.out.println("PathVariable some: " + some);

//        if ("user".equals(some)) {
//            User user = reviewService.getUser();
//            List<Review> findbyuser = reviewService.findbyuser(user);
//            System.out.println("Reviews by user: " + findbyuser);
//            return new ResponseEntity<>(findbyuser, HttpStatus.OK);
//        } else {
//            String[] num = some.split("/"); // Split the path into parts
//            System.out.println("Split path segments: " + Arrays.toString(num));
//
//            // Access the last part of the path (movie ID)
//            Integer movieId = Integer.valueOf(num[num.length - 1]);
			Optional<Movie>  movie = movieService.getMovie(20);
            List<?> findbymovie = reviewService.findbymovie(movie.get());
            
//            System.out.println("Reviews by movie ID " + movieId + ": " + findbymovie);
            return new ResponseEntity<>(findbymovie, HttpStatus.OK);
//        }
    }
	
}
