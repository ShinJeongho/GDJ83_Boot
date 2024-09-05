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
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 url이 왔을때 어떤 Interceptorfmf 실행 할 것인가?
		// /qna/list -> 로그인 Interceptor 
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list");
		
		// 관리자 권한이 필요한 경로에 AdminCheckInterceptor 적용
        registry.addInterceptor(adminCheckInterceptor)
                .addPathPatterns("/admin/**"); // /admin/** 경로는 관리자만 접근 가능
    }
		
	}
	
	

