package com.example.doori.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.domain.User;
import com.example.doori.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // contoller + responsebody 역
@RequiredArgsConstructor // final로 autoworid없이 의존성 주입 가능
@RequestMapping("/doori")  // 페이지들 doori로 시작 
public class UserController {

	private final UserService userService;
	
	// 회원가입 구현부
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
	
		return new ResponseEntity<>("회원가입성공", HttpStatus.OK);
	}
	
	// 로그인 구현부
	
	
}