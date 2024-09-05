package com.winter.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*") // /member/ 하위 경로를 처리하는 컨트롤러
public class MemberController {
	@Autowired
	private MemberService memberService; // 회원 서비스 주입
	
	// 회원 가입 화면 제공 (GET 요청)
	//add
	@GetMapping("add")
	public void add()throws Exception {}
	
	// 회원 가입 처리 (POST 요청)
	@PostMapping("add")
	public String add(MemberVO memberVO)throws Exception {
		int result = memberService.add(memberVO); // 회원 추가 처리
		
		return "redirect:../"; // 회원 추가 후 홈 페이지로 리다이렉트
	}
	
	
	// 로그인 화면 제공 (GET 요청)
	@GetMapping("login")
	public void login()throws Exception {}
	
	// 로그인 처리 (POST 요청)
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session)throws Exception {
		memberVO = memberService.detail(memberVO); // 로그인 처리
		
		// 로그인 성공 시 세션에 사용자 정보 저장
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		
		
		return "redirect:../"; // 로그인 성공 후 홈 페이지로 리다이렉트
	}
	
	// 로그아웃 처리 (GET 요청)
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception {
		session.invalidate(); // 세션 무효화 (로그아웃 처리)
		return "redirect:../"; // 로그아웃 후 홈 페이지로 리다이렉트
	}
}
