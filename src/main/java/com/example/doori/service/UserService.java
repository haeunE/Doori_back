package com.example.doori.service;

import java.util.Optional;

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

    // 회원정보 DB에서 찾기
    public Optional<User> userDetail(String username) {
        return userRepository.findByUsername(username);
    }

    // 현재 비밀번호 검증   
    public boolean checkPassword(String password, String username) {
    	User user = userRepository.findByUsername(username).get();
    	if(passwordEncoder.matches(password, user.getPassword())) {
    		return true;
    	}
    	 return false; 	
    }
    
    
    // 회원 비밀번호 수정
    public void userUpdate(String username, String newPassword) {
    	User user = userRepository.findByUsername(username).get();
    	user.setPassword(passwordEncoder.encode(newPassword));
 
    	userRepository.save(user);
    }
    
    
    
    // 회원 탈퇴 처리
    public void userDelete(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 관련 데이터 삭제 (예: user의 댓글, 게시글 등 다른 관계가 있을 수 있음)
        // 예를 들어, 댓글, 게시글 등의 연관된 데이터를 삭제해야 할 수 있습니다.
        // 이 부분은 해당 프로젝트의 요구사항에 맞게 추가할 수 있습니다.
        
        // DB에서 사용자 삭제
        userRepository.delete(user);
    }
}


