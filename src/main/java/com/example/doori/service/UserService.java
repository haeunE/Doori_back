package com.example.doori.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.doori.domain.RoleType;
import com.example.doori.domain.User;
import com.example.doori.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	
	// username으로 중복있는지 확인
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
		
    // 회원가입 시 username, email, tel 중복되지 않도록 구현
    public void checkUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (userRepository.findByTel(user.getTel()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }
    }
	
	
	// 유저 정보 DB저장
	public void saveUser(User user) {
		checkUser(user);
		user.setRole(RoleType.MEMBER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
}
