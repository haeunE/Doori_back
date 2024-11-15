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

    // 유저 정보 DB 저장
    public void saveUser(User user) {
        // 중복 체크 후 유효성 검사
        checkUser(user);
        
        // 회원 역할을 기본적으로 MEMBER로 설정
        user.setRole(RoleType.MEMBER);
        
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        System.out.println(user);
        // 유저 정보 저장
        userRepository.save(user);
    }
}
