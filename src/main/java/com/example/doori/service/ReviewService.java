package com.example.doori.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.doori.domain.Movie;
import com.example.doori.domain.Review;
import com.example.doori.domain.Timetable;
import com.example.doori.domain.User;
import com.example.doori.dto.ReviewDTO;
import com.example.doori.repository.MovieRepository;
import com.example.doori.repository.ReviewRepository;
import com.example.doori.repository.TimetableRepository;
import com.example.doori.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final MovieRepository movieRepository;
	private final TimetableRepository timetableRepository;
	private final ReviewRepository reviewRepository;
	
	
    // 인증된 사용자 이름 가져오기
    private Optional<String> getAuthenticatedUsername() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }
        return Optional.of(principal.toString());
    }
    
    // 인증된 사용자 정보 가져오기
    public User getUser() {
        return getAuthenticatedUsername()
            .flatMap(userRepository::findByUsername)
            .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
    }
	
    // review저장
	public void reviewSave(ReviewDTO reviewDTO) {
		Review review = new Review();
		Movie movie = movieRepository.findById(reviewDTO.getMovieId()).get();
		Timetable timetable = timetableRepository.findById(reviewDTO.getTimetableId()).get();
		
		review.setReviewScope(reviewDTO.getRating());
		review.setReviewContent(reviewDTO.getReview());
		review.setMovieId(movie);
		review.setTimetableId(timetable);
		review.setUserId(getUser());
		
		reviewRepository.save(review);
	}
	
	// review의 timetableList 보내주기
	public List<Integer> getReviewsTB(){
		List<Review> reviews = reviewRepository.findByUserId(getUser());
		List<Integer> timetableLists = new ArrayList<>();		
		for(Review r : reviews) {
			timetableLists.add(r.getTimetableId().getId());
		}
		return timetableLists;
	}
	
	// review 불러오기 - user로 가져오기 
	public List<Review> getReviewsUser(){
		return reviewRepository.findByUserId(getUser());
	}
	
}
