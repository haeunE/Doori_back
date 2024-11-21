package com.example.doori.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doori.domain.Timetable;
import com.example.doori.filter.DateUtil;
import com.example.doori.repository.TimetableRepository;

@Service
public class ReservationService {
	@Autowired 
	private TimetableRepository timetableRepository; 
	
	
	public List<Timetable> getTimetableByDate(String inputDate) throws ParseException {
        // 날짜 변환
        String formattedDate = DateUtil.convertToDateString(
        		DateUtil.convertToTimestamp(inputDate)
        );
        // DB 조회
        return timetableRepository.findByDate(formattedDate);
    }

}
