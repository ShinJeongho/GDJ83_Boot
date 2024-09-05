package com.winter.app.members;

import lombok.Data;

@Data
public class RoleVO {
	
	private Long roleNum; // 역할 번호 (예: 1 = ROLE_USER, 2 = ROLE_ADMIN)
	private String roleName; // 역할 이름 (예: ROLE_USER, ROLE_ADMIN)
}
