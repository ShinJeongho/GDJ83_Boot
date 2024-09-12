package com.winter.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.members.MemberUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//시큐리티에서 무시할 것들 나열
		return web -> web
						.ignoring()
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**")
						
						;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception{
		String message = URLEncoder.encode("로그인실패", "UTF-8");
		
		
		
		security.
				cors().
				and().
				csrf().
				disable();
		//권한에 관련된 설정
		security.authorizeHttpRequests(
				(authorizeRequest)->
					authorizeRequest
						.requestMatchers("/").permitAll()
						.requestMatchers("/qna/list").permitAll()
						.requestMatchers("/qna/*").authenticated()
						.requestMatchers("/notice/list", "/notice/detail").permitAll()
						.requestMatchers("/notice/*").hasRole("ADMIN")
						.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
						.requestMatchers("/member/add", "/member/login", "/member/check").permitAll()
						.requestMatchers("/member/*").authenticated()
						.anyRequest().permitAll()
						
		 )//끝부분
		
		//form login 관련 설정
		.formLogin(
				login ->
					login.loginPage("/member/login")       //개발자 만든 폼사용하기위해
					//.defaultSuccessUrl("/")
					.successHandler(handler)
					//.failureUrl("/member/login?message="+message)
					.failureHandler(failHandler)
					//파라미터이름이 username이 아니라 id로 사용한 경우 
					//.usernameParameter("id")
					//파라미터이름이 password가 아니라 pw로 사용한 경우
					//.passwordParameter("password")
					.permitAll()
				)
				//로그아웃 관련
				.logout(
						logout ->
								logout
								//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //로그아웃url 지정
								.logoutUrl("/member/logout") //로그아웃 url 지정
								.logoutSuccessHandler(null)
								.logoutSuccessUrl("/") //성공시 url
								.invalidateHttpSession(true)//true 세션 소멸 false 면 세션 만료 x
								//.deleteCookies("") //쿠키삭제 
				)
				//자동로그인관련
				.rememberMe(
						remember ->
								remember
								//파라미터이름
								.rememberMeParameter("rememberMe")
								//토큰의 유효시간
								.tokenValiditySeconds(60)
								//token 생성시 값, 필수값, 개발자가 값 설정 
								.key("rememberme")
								//로그인할때 인증절차 userdetailservice 
								.userDetailsService(memberUserService)
								//로그인이 성공했을 경우 진행할 Handler
								.authenticationSuccessHandler(handler)
								.useSecureCookie(false)
			)
				//동시세션 
				.sessionManagement(
						sessionManager ->
								sessionManager
									//최대 허용 갯수, -1이면 무한대
									.maximumSessions(1)
									//false 라면 이전사용자 만료, true면 새로운사용자 만료
									.maxSessionsPreventsLogin(false)
									//세션만료되면 리다이렉트할 주소
									.expiredUrl("/member/check")
									
				)
				//소셜 로그인 항목
				.oauth2Login(
						oauth2 ->
							oauth2.userInfoEndpoint(
										user -> user.userService(memberUserService)
									)
				)
							
				
				
		;
		
		return security.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
