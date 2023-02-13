package com.bipullohia.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bipullohia.springsecurity.model.AuthenticationRequest;
import com.bipullohia.springsecurity.model.AuthenticationResponse;
import com.bipullohia.springsecurity.util.JWTUtil;

@RestController
public class HomeController {

	@Autowired
	AuthenticationManager authManager;
	
	//to load the user
	@Autowired
	UserDetailsService userDetailsService;
	
	//to generate the jwt token to give back to the client
	@Autowired
	JWTUtil jwtUtilService;

	
	//We want everyone to access this url
	@GetMapping("/")
	public String getDefault() {
		return ("<h1>Welcome to the dark side everyone!</h1>");
	}
	
	@GetMapping("/hello")
	public String getHelloResp() {
		return ("<h1>hey there!</h1>");
	}
	
	//This is for the jwt based authentication (we have to exclude this servlet to escape the default spring security behavior)
	@RequestMapping(value = "/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPwd()));	
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials given");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwtToken = jwtUtilService.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
	
	
	//We will want to allow user and admin user to access this url
	@GetMapping("/user")
	public String getUser() {
		return ("<h1>User/Admin, welcome to the dark side!</h1>");
	}

	//We only want admin user to access this url
	@GetMapping("/admin")
	public String getAdmin() {
		return ("<h1>Admin, welcome to your secret dark side!</h1>");
	}
	
	//A fun error servlet
	@GetMapping("/error")
	public String getError() {
		return ("<h1>Oops!</h1>");
	}
}
