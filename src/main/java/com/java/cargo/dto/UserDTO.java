package com.java.cargo.dto;

import com.java.cargo.model.Role;

import lombok.Data;

@Data
public class UserDTO {
	
	
	private Integer userId;
	
	private Role role;
	
	private String userName;
	
	private String password;
	
	private Boolean isActiveFlag;

	
	
	

}
