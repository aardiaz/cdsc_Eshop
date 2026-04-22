package com.cdsc.eshopdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdsc.eshopdemo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
}
