package com.example.doori.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.doori.domain.Reservation;
import com.example.doori.domain.Seat;
import com.example.doori.domain.Timetable;
import com.example.doori.domain.User;
import com.example.doori.dto.BookingDTO;
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
    	return reservationRepository.save(reservation);   	
    }
    
   
    // seat 저장 
    public void saveBooking(BookingDTO bookingDTO) {
    	List<Seat> seatList = new ArrayList<>();
    	
    	for(int i=0; i<bookingDTO.getSeatNB().size();i++) {
    		Reservation reservationId = saveReservation(bookingDTO.getPrice(), bookingDTO.getTimetableId());
    	    
    		Seat seat = new Seat();
    		
    		seat.setSeatNb(bookingDTO.getSeatNB().get(i));
    		seat.setReservationId(reservationId);
    		
    		seatRepository.save(seat);	    
    	}
    }
}
