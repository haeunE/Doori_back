package com.example.doori.service;

import org.springframework.stereotype.Service;

import com.example.doori.domain.RoleType;
import com.example.doori.domain.User;
import com.example.doori.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	// 중복 되는거 있는지 확인 method들
	// username으로 중복있는지 확인
	public User checkUsername(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	// 회원가입 시 username, email, tel 중복되지 않도록 구현
	public User checkUser(User user) {
		if(checkUsername(user.getUsername()) != null) {
			
		}
		
		
	}
	
	
	// 유저 정보 DB저장
	public void saveUser(User user) {
		user.setRole(RoleType.MEMBER);
		userRepository.save(user);
	}
	
	
}
