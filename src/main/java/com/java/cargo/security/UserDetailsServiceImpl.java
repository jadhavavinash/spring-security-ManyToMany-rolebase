package com.java.cargo.security;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.cargo.rest.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRestRepository;

	public UserDetailsServiceImpl(IUserRepository userRestRepository) {
		this.userRestRepository = userRestRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.java.cargo.model.User user = userRestRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}	
		return new User(user.getUserName(), user.getPassword(), emptyList());
	}
}
