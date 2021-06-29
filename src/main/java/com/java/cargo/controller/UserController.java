package com.java.cargo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.cargo.dto.UserDTO;
import com.java.cargo.service.IUserService;


@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	IUserService userServices;
	
	@PostMapping(value = "/saveUser111")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {

		UserDTO newUser = null;
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

}
