package com.winter.app.members;

import java.sql.Date;
import java.util.List;

import com.winter.app.validate.MemberAddGroup;
import com.winter.app.validate.MemberUpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Lombok을 사용해 getter, setter, toString, equals 등을 자동 생성
public class MemberVO {
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String username;
	
	@Pattern(groups = {MemberAddGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	@NotBlank(groups = {MemberAddGroup.class})
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String name;
	@Email(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private String email;
	@Past(groups = {MemberAddGroup.class, MemberUpdateGroup.class})
	private Date birth;
	private boolean enabled; // 계정 활성화 여부
	private List<RoleVO> vos;  // 회원이 가진 역할 목록 (권한)
}
