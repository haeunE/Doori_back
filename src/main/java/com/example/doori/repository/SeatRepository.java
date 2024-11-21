package com.example.doori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Reservation;
import com.example.doori.domain.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
	List<Seat> findByReservation(Reservation reservation);
}
