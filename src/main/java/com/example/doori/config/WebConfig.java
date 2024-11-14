package com.example.doori.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	// CORS(Cross-Origin Resource Sharing) 설정 메서드
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 경로에 대해 CORS 허용 설정
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000") // http://localhost:3000 도메인만 허용
			.allowedMethods("GET","POST","PUT","DELETE"); // 허용할 HTTP 메서드 목록
	}
	
}
