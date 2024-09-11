package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	@Autowired
	private MemberMapper memberMapper;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String message ="로그인 실패";
		
		log.error("Exception : {} ", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비번틀림
			message = "비밀번호확인해봐라";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			//id틀림
			message = "없는 ID입 니다";
		}
		
		if(exception instanceof AccountExpiredException) {
			//isAccountNonExpired 가 false 일 경우 (계정의 유효기간 만료)
			message = "유효기간이 만료된 계정입니다";
		}
		
		if(exception instanceof LockedException) {
			//계정이 잠겨있음 isAccountNonLocked가 false 경우
			message = "잠겨있는 계정입니다";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			//isCredentialisNonexpired가 false 비번 유효기간 만료
			message = "만료된 비밀번호 입니다";
		}
		
		if(exception instanceof DisabledException) {
			//isEnabled가 false 일 경우 휴면계정일경우
			message = "휴면계정입니다";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("/member/login?message="+message);
		
	}
}
