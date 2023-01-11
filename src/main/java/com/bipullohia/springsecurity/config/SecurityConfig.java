package com.bipullohia.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	//Trying to setup basic in-memory authentication
    @Bean
    InMemoryUserDetailsManager InMemoryAuthentication() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    
    //Setting up Authorization for the above in-memory authentication
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
