package com.example.doori.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.doori.domain.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

}
