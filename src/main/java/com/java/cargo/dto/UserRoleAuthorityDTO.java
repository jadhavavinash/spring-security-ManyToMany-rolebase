package com.java.cargo.dto;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class UserRoleAuthorityDTO implements GrantedAuthority, Serializable{
	
	private String authority;
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
