package com.cdsc.eshopdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdsc.eshopdemo.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByEmail(String email);

	List<User> findByNameContainingIgnoreCase(String name);
}
