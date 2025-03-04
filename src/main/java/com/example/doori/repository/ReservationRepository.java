package com.example.doori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Reservation;
import com.example.doori.domain.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	List<Reservation> findByUserId(User userId);

}

