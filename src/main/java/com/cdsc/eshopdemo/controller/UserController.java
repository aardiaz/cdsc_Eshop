package com.cdsc.eshopdemo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdsc.eshopdemo.entity.User;
import com.cdsc.eshopdemo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		
		user.setUserId(UUID.randomUUID().toString()); // Generate a random UUID for user IDl)
		User u = userService.createUser(user);
		
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

}
