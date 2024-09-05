package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper; // 회원 Mapper 주입
	
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