package com.example.doori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Seat;
import com.example.doori.domain.Timetable;
import com.example.doori.domain.User;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{
	
	@Query("SELECT s.seatNb "+
			"FROM Seat s "+
			"JOIN s.reservationId r "+
			"WHERE r.timetableId = :timetableId")
	List<String> findByTimetableId(@Param("timetableId") Timetable timetableId);

}
