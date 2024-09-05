package com.winter.app.home.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor{
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션에서 로그인 정보 확인
		Object obj = request.getSession().getAttribute("member");
		
		// 로그인된 사용자가 있으면 요청 통과
		if(obj != null) {
			return true;
		}else {
			 // 로그인되지 않은 사용자는 로그인 페이지로 리다이렉트
			response.sendRedirect("/member/login");
			return false;
		}
		
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행 후 추가 작업 (현재는 기본 기능 사용)
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 // 응답 완료 후 추가 작업 (현재는 기본 기능 사용)
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
