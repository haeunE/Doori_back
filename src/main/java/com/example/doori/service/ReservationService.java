package com.example.doori.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	
	
	public List<Timetable> stringToTimestamp(String inputDate) throws ParseException {
        // 날짜 변환
		System.out.println(inputDate);
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        System.out.println(df);
        Date date = df.parse(inputDate);
        Timestamp t = new Timestamp(date.getTime());
        // DB 조회
        return timetableRepository.findByDate(t);
    }
	public List<String> timestampToString(List<Timetable> timetables) throws ParseException {
		// 결과를 담을 리스트
	    List<String> formattedDates = new ArrayList<>();

	    // 날짜 형식 지정
	    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	    // 각 Timetable의 movieDate를 변환
	    for (Timetable timetable : timetables) {
	        Timestamp movieDate = timetable.getMovieDate(); // 기존 Timestamp 가져오기
	        String formattedDate = df.format(movieDate);   // 문자열로 변환
	        formattedDates.add(formattedDate);            // 결과 리스트에 추가
	    }

	    return formattedDates; // 변환된 날짜 문자열 리스트 반환
	}

}
