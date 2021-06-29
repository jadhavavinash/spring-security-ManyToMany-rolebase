package com.java.cargo.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.cargo.model.User;
import com.java.cargo.model.UserRoleAuthority;



@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
	
	 @Query("Select user from User user where user.userName=?1 ")
	 public User getUserByUserName(String userName);
	 
}
