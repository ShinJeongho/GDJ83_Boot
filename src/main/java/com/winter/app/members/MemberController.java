package com.winter.app.members;

import java.util.Enumeration;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.validate.MemberAddGroup;
import com.winter.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*") // /member/ 하위 경로를 처리하는 컨트롤러
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService; // 회원 서비스 주입
	
	@GetMapping("check")
	public void check()throws Exception {
		
	}
	
	@GetMapping("update")
	public String update(HttpSession session, Model model)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO", memberVO);
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		
		return "redirect:./mypage";
	}
	
	@GetMapping("mypage")
	public void mypage(HttpSession session)throws Exception{
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {                  //SPRING_SECURITY_CONTEXT
			String name = en.nextElement();
			log.info("Name {} : ", name);
		}
		
		SecurityContextImpl sc =(SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("SecurityContextImpl {} : ", sc);   
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("Context {} : ", context);  
		
		Authentication authentication = context.getAuthentication();
		log.info("Authentication {} : ", authentication);
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("MemberVO : {} ", memberVO);
		log.info("Name : {} ", authentication.getName());
		
	}
	
	// 회원 가입 화면 제공 (GET 요청)
	//add
	@GetMapping("add")
	public void add(MemberVO memberVO)throws Exception {}
		
	// 회원 가입 처리 (POST 요청)
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception {
	boolean check = memberService.memberValidate(memberVO, bindingResult);
	if(check) {
		return "member/add";
	}
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		int result = memberService.add(memberVO); // 회원 추가 처리
		
		return "redirect:../"; // 회원 추가 후 홈 페이지로 리다이렉트
	}
	
	
	// 로그인 화면 제공 (GET 요청)
	@GetMapping("login")
	public String login(String message, Model model)throws Exception {
		model.addAttribute("message", message);
		
		SecurityContext context =SecurityContextHolder.getContext();
		if(context == null) {
			return "member/login";
		}
		
		String user = context.getAuthentication().getPrincipal().toString();
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		return "redirect:/";
	}
	
//	// 로그인 처리 (POST 요청)
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session)throws Exception {
//		memberVO = memberService.detail(memberVO); // 로그인 처리
//		
//		// 로그인 성공 시 세션에 사용자 정보 저장
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//		
//		
//		return "redirect:../"; // 로그인 성공 후 홈 페이지로 리다이렉트
//	}
	
	// 로그아웃 처리 (GET 요청)
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception {
		session.invalidate(); // 세션 무효화 (로그아웃 처리)
		return "redirect:../"; // 로그아웃 후 홈 페이지로 리다이렉트
	}
}
