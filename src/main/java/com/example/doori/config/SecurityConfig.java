package com.example.doori.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example.doori.filter.JwtFilter;
import com.example.doori.security.AuthEntryPoint;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//CSRF 보호 기능 비활성화
		http.csrf().disable();
		//CORS 활성화
		http.cors();
		
		//세션 관리 설정
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//엔드포인트 접근 권한 설정 ( 첫 접속 포인트 )
		http.authorizeRequests()
			// (로그인, 로그아웃 , 홈 , 영화 목록)페이지 만 접근 가능

			.antMatchers(HttpMethod.POST,"/doori/login","/doori/signup", "/doori/login/kakao","/doori/movies/**", "/doori/usernamecheck","/doori/reservation").permitAll()
			.antMatchers(HttpMethod.PUT,"doori/userupdate").permitAll()
			.antMatchers(HttpMethod.DELETE, "doori/userdelete").permitAll()
			.antMatchers(HttpMethod.GET,"/doori/home","doori/userupdate","/doori/movies/**","/doori/test","/doori/reservation").permitAll()
			.anyRequest().authenticated() //나머지 모든 요청은 인증 필요
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authEntryPoint) //인증실패 시 authEntryPoint로 처리
			.and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	//CORS설정을 정의하는 메서드 (무분별한 접근 막기)
	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://localhost:3000"); // 특정 오리진을 허용(예: 로컬호스트의 프론트엔드)
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
		source.registerCorsConfiguration("/**", config);//모든 경로에 대해 CORS설정 적용
		return source;
	}
	
	//비밀번호 암호화를 위한 PasswordEncoder를 Bean으로 등록
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();// BCryptPasswordEncoder를 사용하여 비밀번호를 암호화
	}
	
	// AuthenticationManager를 Bean으로 등록 (사용자 인증을 위한 매니저)
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager(); // AuthenticationManager를 AuthenticationConfiguration에서 가져옴
	}
}
