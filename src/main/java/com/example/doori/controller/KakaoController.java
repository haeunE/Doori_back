package com.example.doori.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.doori.service.JwtService;

@RestController
public class KakaoController {
	 @Autowired
	 private JwtService jwtService;
	 
	 @PostMapping("/doori/login/kakao")
	 public ResponseEntity<?> kakaologin(@RequestBody String username) {
	  
	System.out.println(username);
	 String jwts = jwtService.getToken(username);
	 return ResponseEntity.ok()
						 .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
						 .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
						 .build();
	 }
	
}
