	package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper; // 회원 Mapper 주입
	
	//검증메서드
	public boolean memberValidate(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean check=false;
		//check=false : 검증성공(error 없음)
		//check=true  : 검증실패(error 있음)
		
		//0. 기본검증값( annotation 검증)
		check =bindingResult.hasErrors();
		
		//1. password가 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
		check=true;
		//에러메세지
		//bindingResult.rejectValue("멤버변수명(path)", "properties의key(코드)");
		bindingResult.rejectValue("passwordCheck", "memberVO.pw.notEqual");
						
		}
		
		//2. id가 중복 인지 검증
		MemberVO result = memberMapper.detail(memberVO);
		if(result != null) {
		check=true;
		bindingResult.rejectValue("username", "memberVO.username.duplication");
		}
				
				return check;
				
	}
		
	
	
	public int add(MemberVO memberVO) throws Exception{
		int result = memberMapper.add(memberVO); // 회원 정보 DB에 삽입
		
		// 회원 역할 정보 추가 (기본으로 ROLE_USER 역할 부여)
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 1); // 기본 역할 번호 1 (ROLE_USER)
		
		
		result = memberMapper.addRole(map);  // 역할 정보 DB에 삽입

		
		return result;
	}
	// 회원 상세 정보 조회 (로그인 처리용)
	public MemberVO detail(MemberVO memberVO)throws Exception{
		MemberVO result = memberMapper.detail(memberVO);// 회원 정보 조회
		// 비밀번호 일치 여부 확인
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())) {
				return result;
			}
		}
			return null;  // 비밀번호 불일치 시 null 반환
		
	}
}