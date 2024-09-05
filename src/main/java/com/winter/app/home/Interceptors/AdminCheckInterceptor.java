package com.winter.app.home.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.winter.app.members.MemberVO;
import com.winter.app.members.RoleVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			 // 세션에서 사용자 정보 가져오기
			MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
			
			// 로그인이 되어 있지 않으면 로그인 페이지로 리다이렉트
			if(memberVO ==null) {
				response.sendRedirect("/member/login");
				return false; // 요청 차단
			}
			// 사용자 역할 중 관리자 권한이 있는지 확인
			for(RoleVO roleVO:memberVO.getVos()) {
				if(roleVO.getRoleName().equals("ROLE_ADMIN")) {
					return true; // 관리자 권한이 있을 경우 요청 통과
				}
			}
			 // 관리자 권한이 없을 경우 에러 메시지와 함께 메인 페이지로 포워딩
			request.setAttribute("msg", "관리자 전용");
			request.setAttribute("path", "/");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
			view.forward(request, response);
			
			return false; // 요청 차단
			
			
			
		}
}
