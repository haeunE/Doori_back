package com.example.doori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	List<Movie> findAllByOrderByIdDesc();
}
