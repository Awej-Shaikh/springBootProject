package com.SpringSecurity.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class userConfig {

	@Autowired
	private UserService userservice;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return userservice;
	}

	@Bean
	public DaoAuthenticationProvider ap() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userDetailsService());
		dap.setPasswordEncoder(passwordEncoder());

		return dap;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in Postman
				.authorizeHttpRequests(auth -> auth.requestMatchers("/store").permitAll() // Public access for user
																							// registration
						.requestMatchers("/getOne").hasRole("USER") // Restricted to ROLE_USER
						.requestMatchers("/getTwo").hasRole("ADMIN") // Restricted to ROLE_ADMIN
						.anyRequest().authenticated() // All other requests require authentication
				).formLogin(form -> form.permitAll()) // Configure form login
				.build(); // Correctly placed build method
	}
}
