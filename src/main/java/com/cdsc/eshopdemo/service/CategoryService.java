package com.cdsc.eshopdemo.service;

import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.dto.PageableResponse;

public interface CategoryService {

	// create
	CategoryDto create(CategoryDto categoryDto);

	// update
	CategoryDto update(CategoryDto categoryDto, long categoryId);

	// delete
	void delete(long categoryId);

	// get all
	PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

	// get single category detail
	CategoryDto get(long categoryId);

}
