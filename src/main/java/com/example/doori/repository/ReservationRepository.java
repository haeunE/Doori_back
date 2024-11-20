package com.example.doori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}
