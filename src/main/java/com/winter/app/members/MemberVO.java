package com.winter.app.members;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data // Lombok을 사용해 getter, setter, toString, equals 등을 자동 생성
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled; // 계정 활성화 여부
	private List<RoleVO> vos;  // 회원이 가진 역할 목록 (권한)
}
