package com.bipullohia.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bipullohia.springsecurity.entity.User;
//import com.bipullohia.springsecurity.repository.UserRepository;

@SpringBootApplication
//@EnableOAuth2Sso //This is needed for OAuth based Authentication/SSO - this line apart from some properties in the application.prop, is all that's needed to set it up. We can put some html in static index.html file
/*
 * This is needed for DB based authentication
 * @EnableJpaRepositories(basePackageClasses = UserRepository.class)
 * @EntityScan(basePackageClasses = User.class)
 */
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

}
