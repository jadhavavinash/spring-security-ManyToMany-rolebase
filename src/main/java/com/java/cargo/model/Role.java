package com.java.cargo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "role" ,uniqueConstraints = @UniqueConstraint(columnNames = "role"))
public class Role implements GrantedAuthority{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "roleid")
	private Integer roleId;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "isactiveflag")
	private Integer isActiveFlag;

	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getIsActiveFlag() {
		return isActiveFlag;
	}
	public void setIsActiveFlag(Integer isActiveFlag) {
		this.isActiveFlag = isActiveFlag;
	}
	
	@JsonIgnore
	@Override
	@Transient
	public String getAuthority() {
		return role;
	}

	@Transient
	public void setAuthority(String role) {
		this.role = role;
	} 
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + ", isActiveFlag=" + isActiveFlag + "]";
	}
	
	

}
