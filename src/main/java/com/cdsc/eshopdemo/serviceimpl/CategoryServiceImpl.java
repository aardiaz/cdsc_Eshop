package com.cdsc.eshopdemo.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.entity.Category;
import com.cdsc.eshopdemo.exception.ResourceNotFoundException;
import com.cdsc.eshopdemo.repository.CategoryRepository;
import com.cdsc.eshopdemo.service.CategoryService;
import com.cdsc.eshopdemo.utils.Helper;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository  categoryRepository;
	
	@Autowired
	private ModelMapper  modelMapper;

	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		 
		Category category = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, long categoryId) {
		//get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void delete(long categoryId) {
		//get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        categoryRepository.delete(category);
	}

	@Override
	public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		 
		 Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
	        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	        Page<Category> page = categoryRepository.findAll(pageable);
	        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
	        return pageableResponse;
	}

	@Override
	public CategoryDto get(long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id !!"));
        return modelMapper.map(category, CategoryDto.class);
	}

}
