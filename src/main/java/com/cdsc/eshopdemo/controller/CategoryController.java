package com.cdsc.eshopdemo.controller;

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

import com.cdsc.eshopdemo.dto.ApiResponseMessage;
import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// create
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		// call service to save object
		CategoryDto categoryDto1 = categoryService.create(categoryDto);
		return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable long categoryId,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable long categoryId) {
		categoryService.delete(categoryId);
		ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Category is deleted successfully !!")
				.status(HttpStatus.OK).success(true).build();
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	// get all

	@GetMapping
	public ResponseEntity<PageableResponse<CategoryDto>> getAll(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

	) throws InterruptedException {
		// Thread.sleep(1000);
		PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
	}

	// get single
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingle(@PathVariable long categoryId) {
		CategoryDto categoryDto = categoryService.get(categoryId);
		return ResponseEntity.ok(categoryDto);
	}

}
