package com.example.doori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Review;
import com.example.doori.domain.User;
import com.example.doori.domain.Movie;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	List<Review> findByMovieId(Movie movieId);
	List<Review> findByUserId(User userId);
}
