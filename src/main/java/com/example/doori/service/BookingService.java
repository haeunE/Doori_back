package com.example.doori.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.doori.domain.Reservation;
import com.example.doori.domain.Seat;
import com.example.doori.domain.Timetable;
import com.example.doori.domain.User;
import com.example.doori.dto.BookingDTO;
import com.example.doori.dto.ReservationDTO;
import com.example.doori.repository.ReservationRepository;
import com.example.doori.repository.SeatRepository;
import com.example.doori.repository.TimetableRepository;
import com.example.doori.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	
	private final UserRepository userRepository;
	private final ReservationRepository reservationRepository;
	private final SeatRepository seatRepository;
	private final TimetableRepository timetableRepository;
	
    // 인증된 사용자 이름 가져오기
    private Optional<String> getAuthenticatedUsername() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }
        return Optional.of(principal.toString());
    }
    
    // 인증된 사용자 정보 가져오기
    public User getUser() {
        return getAuthenticatedUsername()
            .flatMap(userRepository::findByUsername)
            .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
    }
    
    // timetable 정보 가져오기
    public Timetable getTimetable(Integer id) {
    	return timetableRepository.findById(id).get();
    }
    
    
    // reservation 저장 
    public Reservation saveReservation(String price, Integer timetableId) {
    	User user = getUser();
    	Reservation reservation = new Reservation();
    	reservation.setPrice(price);
    	reservation.setTimetableId(getTimetable(timetableId));
    	reservation.setUserId(user);
    	return reservationRepository.save(reservation);   	
    }
    
   
    // seat 저장 
    public void saveBooking(BookingDTO bookingDTO) {
    	List<Seat> seatList = new ArrayList<>();
    	
    	for(int i=0; i<bookingDTO.getSeatNb().size();i++) {
    		Reservation reservationId = saveReservation(bookingDTO.getPrice(), bookingDTO.getTimetableId());
    	    
    		Seat seat = new Seat();
    		seat.setSeatNb(bookingDTO.getSeatNb().get(i));
    		seat.setReservationId(reservationId);
    		
    		seatRepository.save(seat);	    
    	}
    }
    
    // 예약된 seat가져오기
    public List<String> getReservedSeat(Integer timetableId){
    	return seatRepository.findByTimetableId(getTimetable(timetableId));
    }
    
    
    // user가 가진 reservation가져오기
    public List<ReservationDTO> getReservationInfo(){
    	User user = getUser();
    	List<Reservation> reservations = reservationRepository.findByUserId(user);
    	
    	// reservationDTO에 필요한 정보들만 저장
    	List<ReservationDTO> rLists = new ArrayList<>();
    	// timetable이 동일할때 reservationDTO여러번 생성 방지
    	Map<Integer, ReservationDTO> timetableMap = new HashMap<>();
    
    	
    	for(Reservation r : reservations) {
    		Integer timetableId = r.getTimetableId().getId(); 		
    		ReservationDTO dto = timetableMap.get(timetableId);
    		
    		if(dto==null) {
    		dto = new ReservationDTO();
    		dto.setTimetableId(r.getTimetableId().getId());
    		dto.setMoviePoster(r.getTimetableId().getMovieId().getMoviePoster());
    		dto.setTitle(r.getTimetableId().getMovieId().getTitle());
    		dto.setRunningtime(r.getTimetableId().getMovieId().getRunningtime());
    		dto.setMovieDate(r.getTimetableId().getMovieDate());
    		dto.setPrice(r.getPrice());
    		dto.setSeatNm(new ArrayList<>());  // 좌석 리스트 초기화
    		}
    		for(Seat s : r.getSeatList()) {
    			dto.getSeatNm().add(s.getSeatNb());
    		}
            timetableMap.put(timetableId, dto);
    	}
    	 List<ReservationDTO> reservationList = new ArrayList<>(timetableMap.values());
    	return reservationList;
    }
    

}
