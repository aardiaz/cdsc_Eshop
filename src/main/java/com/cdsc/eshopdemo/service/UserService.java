package com.cdsc.eshopdemo.service;

import java.util.List;

import com.cdsc.eshopdemo.dto.UserDto;
import com.cdsc.eshopdemo.entity.User;

public interface UserService {

	// create
	UserDto createUser(UserDto user);

	// delete user
	void deleteUser(String userId);

	// update
	UserDto updateUser(String userId, UserDto user);

	// get all users
	List<UserDto> getAllUsers();

	// get one user
	UserDto getUserById(String userId);

	// get by email
	UserDto getUserByEmail(String email);

	// search users by name
	List<UserDto> searchUsersByName(String name);

}
