package com.SpringSecurity.UsersControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.UserEntity.UserEntity;
import com.SpringSecurity.UserRepository.UserRepo;
import com.SpringSecurity.UserService.UserService;

@RestController
public class UserControllers {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Save User Data
	@PostMapping("/store")
	public UserEntity saveTheData(@RequestBody UserEntity userEntity) {
		// Prefix role with "ROLE_" for Spring Security
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return userRepo.save(userEntity);
	}

	// Test endpoint for users with "USER" role
	@GetMapping("/getOne") // only For User
	public String one() {
		return "Welcome to User endpoint: /getOne";
	}

	// Test endpoint for users with "ADMIN" role
	@GetMapping("/getTwo") // Only For Admin
	public String two() {
		return "Welcome to Admin endpoint: /getTwo";
	}
}
