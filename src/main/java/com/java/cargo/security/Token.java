package com.java.cargo.security;

import java.util.Set;

import com.java.cargo.dto.RoleDTO;
import com.java.cargo.dto.UserRoleAuthorityDTO;


public class Token {
	
	private Integer userId;
	private String username;
	private Set<RoleDTO> roles;
	private boolean isAccountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean accountDisabled;
	private Set<UserRoleAuthorityDTO> authorities;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isAccountExpired() {
		return isAccountExpired;
	}
	public void setAccountExpired(boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}
	public boolean isAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public boolean isAccountDisabled() {
		return accountDisabled;
	}
	public void setAccountDisabled(boolean accountDisabled) {
		this.accountDisabled = accountDisabled;
	}
	public Set<UserRoleAuthorityDTO> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<UserRoleAuthorityDTO> authorities) {
		this.authorities = authorities;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	
	
	

}
