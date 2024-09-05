package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.home.Interceptors.AdminCheckInterceptor;
import com.winter.app.home.Interceptors.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor; // 로그인 체크 인터셉터 주입
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor; // 관리자 권한 체크 인터셉터 주입


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 로그인 인터셉터를 /qna/* 경로에 적용하되 /qna/list는 예외 처리
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*") // /qna/* 경로에서 실행
				.excludePathPatterns("/qna/list"); // /qna/list는 제외
		
		 // 관리자 권한 체크 인터셉터를 /admin/** 경로에 적용
        registry.addInterceptor(adminCheckInterceptor)
                .addPathPatterns("/admin/**"); // /admin/** 경로에서 실행
    }
		
	}
	
	

