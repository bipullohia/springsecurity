package com.bipullohia.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	//Setting up Bean for Embedded H2 datasource (schema.sql has scehma DDLs)
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	//Setting up Bean for JDBC Authentication (data.sql has user dmls)
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
		users.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
		return users;
	}
	
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
    
    /* 2.C JDBC authentication (our own schema and users) according to Spring 5.7 and above
    ------------------------------------------------------------------------
    
    //Setting up Bean for Embedded H2 datasource (schema.sql has scehma DDLs)
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	//Setting up Bean for JDBC Authentication (data.sql has user dmls)
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}*/
    
    
    /* 2.B JDBC authentication (schema given in schema.sql file, users in data.sql file) according to Spring 5.7 and above
    ------------------------------------------------------------------------
    
    //Setting up Bean for Embedded H2 datasource (schema.sql has scehma DDLs)
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	//Setting up Bean for JDBC Authentication (data.sql has user dmls)
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}*/
    
    
    /* 2.A JDBC authentication (DEFAULT SCHEMA) according to Spring 5.7 and above
     ------------------------------------------------------------------------
    
    //Setting up Bean for Embedded H2 datasource WITH DEFAULT SCHEMA
  	@Bean
  	public DataSource dataSource() {
  		return new EmbeddedDatabaseBuilder()
  				.setType(EmbeddedDatabaseType.H2)
  				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
  				.build();
  	}
  	
  	//Setting up Bean for JDBC Authentication (creating Users in code)
  	@Bean
  	public UserDetailsManager users(DataSource dataSource) {
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
  		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
  		users.createUser(user);
  		users.createUser(admin);
  		return users;
  	}*/
	
	
	/* 1. In-memory authentication according to Spring 5.7 and above
	---------------------------------------------------------------------------
	
	//Setting up Bean for In-Memory Authentication
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
    }*/
}
