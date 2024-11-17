package com.example.doori.controller;


import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.User;
import com.example.doori.domain.UserCredentials;
import com.example.doori.dto.UserDTO;
import com.example.doori.service.JwtService;
import com.example.doori.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // contoller + responsebody 역
@RequiredArgsConstructor // final로 autoworid없이 의존성 주입 가능
@RequestMapping("/doori")  // 페이지들 doori로 시작 
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	// 회원가입 구현부
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
	    // 유효성 검사
	    if (bindingResult.hasErrors()) {
	        StringBuilder errorMessages = new StringBuilder();
	        bindingResult.getAllErrors().forEach(error -> {
	            errorMessages.append(error.getDefaultMessage()).append("\n");
	        });
	        return ResponseEntity.badRequest().body(errorMessages.toString());
	    }
	    
	    try {
	        // 유효성 검사 통과 시 회원가입 로직 처리
	        User user = modelMapper.map(userDTO, User.class);
	        userService.saveUser(user);
	        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
	    } catch (Exception e) {
	        // 예외 처리
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패: " + e.getMessage());
	    }
	}

	
	// 로그인 구현부
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {
	    try {
	        UsernamePasswordAuthenticationToken creds =
	                new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());

	        Authentication auth = authenticationManager.authenticate(creds);
	        String jwts = jwtService.getToken(auth.getName());

	        return ResponseEntity.ok()
	                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
	                            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
	                            .build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패: 사용자 이름 또는 비밀번호가 잘못되었습니다.");
	    }
	}


}