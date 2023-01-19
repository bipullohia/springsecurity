package com.bipullohia.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfigLDAP {

	//This solution might not be complete for Spring 5.7+ since it throws ldif file error
	
    @Bean
    EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
        EmbeddedLdapServerContextSourceFactoryBean ldapBean = EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
        return ldapBean;
    }

    @Bean
    AuthenticationManager ldapAuthManager(BaseLdapPathContextSource contextSrc) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSrc);
        factory.setUserDnPatterns("uid={0},ou={people}");
        factory.setUserDetailsContextMapper(new PersonContextMapper());
        return factory.createAuthenticationManager();
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
}
