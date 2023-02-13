package com.bipullohia.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


//@Configuration
public class SecurityConfigJPAMysql {

	//this is enough in Spring 5.7+ to let Srping Security know that it has to feth user details from the service
	@Autowired
	UserDetailsService userDetailService;

	//Setting up Bean for Authorization (valid for any type of authentication)
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests()
    		.requestMatchers("/admin").hasRole("ADMIN")
    		.requestMatchers("/user").hasAnyRole("USER", "ADMIN") //allow both the roles (here, we can give multiple roles)
    		.requestMatchers("/").permitAll() //this is generally allowed to static files (js/css etc. files so that the page can load properly)
    		.and()
    		.formLogin();
    	return http.build();
    }
}
