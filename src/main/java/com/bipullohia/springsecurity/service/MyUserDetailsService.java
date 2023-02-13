package com.bipullohia.springsecurity.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bipullohia.springsecurity.model.MyJPAUserDetails;
//import com.bipullohia.springsecurity.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	//NOTE: We only have to set this UserDetailsService and Spring Security will automatically pick it up without mentioning in the SecurityConfigJPAMysql file
	
	/* 1. This service is being used in mySQL-JPA Authentication implementation

	@Autowired
	UserRepository userRepo;
	
	//This will call the JPA repo to get the user details
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		
		//this is invoked in the case that username given by the user is not found
		user.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
		
		return user.map(MyJPAUserDetails::new).get();
	}
	*/
	
	/* 2. An example to show that we can configure even hard-coded values in UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyHardCodedUserDetails(username);
	}*/
	
	// 3. Setting up JWT based authentication
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("user", "{noop}pwd", new ArrayList<>()); //just for demo - we can use the default User class provided by Spring Security
	}

}
