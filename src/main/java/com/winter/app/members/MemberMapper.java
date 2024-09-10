package com.winter.app.members;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	// 회원 추가 메서드
	public int add(MemberVO memberVO) throws Exception;
	// 회원 상세 정보 조회 메서드 (로그인 처리용)
	public MemberVO detail(MemberVO memberVO)throws Exception;
	// 회원 역할 추가 메서드
	public int addRole(Map<String, Object> map) throws Exception;
	
	public int pwUpdate(MemberVO memberVO)throws Exception;
	
	
}
