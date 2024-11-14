package com.example.doori.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestControllter {
	@GetMapping("/test")
	public String getMethodName() {
		return "안녕하세요";
	}
	
}
