package com.bipullohia.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	//We want everyone to access this url
	@GetMapping("/")
	public String getDefault() {
		return ("<h1>Welcome to the dark side everyone!</h1>");
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
