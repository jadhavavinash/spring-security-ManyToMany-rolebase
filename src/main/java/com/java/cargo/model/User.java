package com.java.cargo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.cargo.dto.UserRoleAuthorityDTO;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "isactiveflag")
	private Boolean isActiveFlag;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable( name="userroleauthority",
		joinColumns=@JoinColumn(name="userid"),
		inverseJoinColumns=@JoinColumn(name="roleid")
			)
	private Set<Role> roles= new HashSet<>();

	@Transient
	private Set<UserRoleAuthorityDTO> authorities;
 
	 
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActiveFlag() {
		return isActiveFlag;
	}

	public void setIsActiveFlag(Boolean isActiveFlag) {
		this.isActiveFlag = isActiveFlag;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) authorities;
	}

	public void setAuthorities(Set<UserRoleAuthorityDTO> authorities) {
		this.authorities = authorities;
	}
	}
