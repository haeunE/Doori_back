package com.example.doori.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByTel(String tel);
}
