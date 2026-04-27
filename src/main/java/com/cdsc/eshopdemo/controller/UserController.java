package com.cdsc.eshopdemo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.dto.UserDto;
import com.cdsc.eshopdemo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
		
		user.setUserId(UUID.randomUUID().toString()); // Generate a random UUID for user IDl)
		UserDto u = userService.createUser(user);
		
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}
	
	@GetMapping
	public  ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required 
            = false) String sortDir
    ) {
        return new ResponseEntity<>(userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") String userId) {

		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {

		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<UserDto>> searchUsersByName(@PathVariable("name") String name) {

		return new ResponseEntity<>(userService.searchUsersByName(name), HttpStatus.OK);
	}
	
	//delete user
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {

		userService.deleteUser(userId);

		return new ResponseEntity<>("Deleted success",HttpStatus.OK);
	}
	
	//update user

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId, @RequestBody UserDto user) {

		return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
	}
	
}





