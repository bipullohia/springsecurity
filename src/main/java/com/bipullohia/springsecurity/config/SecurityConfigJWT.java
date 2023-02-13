package com.bipullohia.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bipullohia.springsecurity.service.MyUserDetailsService;
import com.bipullohia.springsecurity.util.JWTRequestFilter;

@Configuration
public class SecurityConfigJWT {

	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JWTRequestFilter jwtFilter;
	
	//Setting up Bean for Authorization (valid for any type of authentication)
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    		.authorizeRequests()
    		.requestMatchers("/authenticate").permitAll().anyRequest().authenticated() //this is to exclude /authenticate from spring security checks
    		.and().sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS); //we are telling spring security to keep the session stateless - i.e., no session to be preserved. Every request will have individual scrutiny
    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //we have to set this filter before other processes takes place
    	return http.build();
    }
    
    //this is to comply with newer version of spring (it is not the default bean anymore)
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }
}
