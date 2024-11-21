package com.example.doori.repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Movie;
import com.example.doori.domain.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Integer>{
	@Query("SELECT t FROM Timetable t WHERE FUNCTION('DATE', t.movieDate) = :date ORDER BY t.movieDate asc")
    List<Timetable> findByDate(@Param("date") Timestamp date);

}
