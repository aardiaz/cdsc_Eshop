package com.cdsc.eshopdemo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdsc.eshopdemo.entity.User;
import com.cdsc.eshopdemo.repository.UserRepository;
import com.cdsc.eshopdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		 
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(String userId) {

		userRepository.deleteById(userId);
	}

	@Override
	public User updateUser(String userId, User user) {
		 
		userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		
		user.setUserId(userId); // Ensure the user ID is set for the update
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		 
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String userId) {
		 
		User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
		return u;
	}

	@Override
	public User getUserByEmail(String email) {
		
		 User u = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email));
		
		return u;
	}

	@Override
	public List<User> searchUsersByName(String name) {
		
		List<User> ulist = userRepository.findByNameContainingIgnoreCase(name);
		return ulist;
	}
	
	

}
