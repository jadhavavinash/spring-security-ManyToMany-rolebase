package com.java.cargo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonBackReference;

//@Entity
//@Table(name = "userroleauthority")
public class UserRoleAuthority{// implements GrantedAuthority, Serializable{

//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "userroleauthorityid", unique = true, nullable = false)
//	private Integer userRoleAuthorityId;
//
//	@JsonBackReference
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "userid")
//	private User user;
//
//	@Column(name = "role")
//	private String authority;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "roleid")
//	private Role role;
//	
//
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//	
//	public Integer getUserRoleAuthorityId() {
//		return userRoleAuthorityId;
//	}
//
//	public void setUserRoleAuthorityId(Integer userRoleAuthorityId) {
//		this.userRoleAuthorityId = userRoleAuthorityId;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public String getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}

}
