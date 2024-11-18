package com.example.doori.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
    // 현재 인증된 사용자의 username을 가져오는 메서드
    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
	
    // 회원 정보 조회
    @GetMapping("/userupdate")
    public ResponseEntity<?> getUserDetails() {
        String username = getAuthenticatedUsername();
        Optional<User> user = userService.userDetail(username);
        return user.map(ResponseEntity::ok)
                   .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
	// 회원정보 수정
    @PutMapping("/userupdate")
    public ResponseEntity<?> userupdate(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        String username = getAuthenticatedUsername();  // JWT에서 username 추출

        // 유효성 검사
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        try {
            // 현재 비밀번호를 받아와서 검증
            String currentPassword = userDTO.getCurrentPassword(); // DTO에서 현재 비밀번호 가져오기
            User user = modelMapper.map(userDTO, User.class);  // DTO를 Entity로 변환

            // 회원 정보 수정 시 비밀번호를 포함하여 서비스에 전달
            userService.userUpdate(user, username, currentPassword);  // 서비스로 수정 요청
            
            return new ResponseEntity<>("정보수정 완료", HttpStatus.OK);
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정보수정 실패: " + e.getMessage());
        }
    }
	
	// 회원 탈퇴 구현부   
    // 회원탈퇴
    @DeleteMapping("/userdelete")
    public ResponseEntity<?> deleteUser() {
        String username = getAuthenticatedUsername();  // JWT에서 username 추출

        try {
            // 회원 탈퇴 처리
            userService.deleteUser(username);
            
            // 탈퇴 후, 사용자 세션 종료 (로그아웃)
            SecurityContextHolder.clearContext();

            return new ResponseEntity<>("회원 탈퇴가 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("회원 탈퇴 실패: " + e.getMessage());
        }
    }

}