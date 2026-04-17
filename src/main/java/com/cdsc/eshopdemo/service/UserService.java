package com.cdsc.eshopdemo.service;

import java.util.List;

import com.cdsc.eshopdemo.entity.User;

public interface UserService {

	// create
	User createUser(User user);

	// delete user
	void deleteUser(String userId);

	// update
	User updateUser(String userId, User user);

	// get all users
	List<User> getAllUsers();

	// get one user
	User getUserById(String userId);

	// get by email
	User getUserByEmail(String email);

	// search users by name
	List<User> searchUsersByName(String name);

}
