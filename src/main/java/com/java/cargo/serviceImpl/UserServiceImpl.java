package com.java.cargo.serviceImpl;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.java.cargo.dto.RoleDTO;
import com.java.cargo.dto.UserDTO;
import com.java.cargo.dto.UserRoleAuthorityDTO;
import com.java.cargo.model.Role;
import com.java.cargo.model.User;
import com.java.cargo.model.UserRoleAuthority;
import com.java.cargo.rest.repository.IUserRepository;
import com.java.cargo.security.Token;
import com.java.cargo.service.IUserService;




@Service("userServices")

public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository userRepository;

	@Override
	public UserDTO saveUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Token getUserByName(String userName) {
		User userRoleObj = userRepository.getUserByUserName(userName);
		Token tokenObj = new Token();
		Set<UserRoleAuthorityDTO> userRoleSetAuth = new HashSet<>();
		Set<RoleDTO> roleSet = new HashSet<>();
		if (userRoleObj != null) {
			for(Role role:userRoleObj.getRoles()) {
				RoleDTO roleDto=new RoleDTO();
				roleDto.setRoleId(role.getRoleId());
				roleDto.setRole(role.getRole());
				UserRoleAuthorityDTO userRoleAuthoriDTO = new UserRoleAuthorityDTO();
				userRoleAuthoriDTO.setAuthority("ROLE_"+role.getRole());
				userRoleSetAuth.add(userRoleAuthoriDTO);
				roleSet.add(roleDto);
			}
			tokenObj.setUserId(userRoleObj.getUserId());
			tokenObj.setUsername(userRoleObj.getUserName());
			tokenObj.setRoles(roleSet);
			tokenObj.setAuthorities(userRoleSetAuth);
			return tokenObj;
		}
		return null;
	}
	@Override
	public boolean isUserExist(UserDTO user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
