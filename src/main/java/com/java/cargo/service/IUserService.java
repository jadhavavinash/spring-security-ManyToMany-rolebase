package com.java.cargo.service;


import com.java.cargo.dto.UserDTO;
import com.java.cargo.security.Token;



public interface IUserService {
	
	UserDTO saveUser(UserDTO user);

	boolean isUserExist(UserDTO user);
	
	Token getUserByName(String userName);
}
