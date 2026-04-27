package com.cdsc.eshopdemo.service;

import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.dto.ProductDto;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto, Long productId);

    //delete
    void delete(Long productId);

    //get single

    ProductDto get(Long productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

	/*
	 * //get all : live PageableResponse<ProductDto> getAllLive(int pageNumber, int
	 * pageSize, String sortBy, String sortDir);
	 * 
	 * //search product PageableResponse<ProductDto> searchByTitle(String subTitle,
	 * int pageNumber, int pageSize, String sortBy, String sortDir);
	 * 
	 * 
	 * //create product with category ProductDto createWithCategory(ProductDto
	 * productDto,Long categoryId);
	 * 
	 * 
	 * //update category of product ProductDto updateCategory(String productId,Long
	 * categoryId);
	 * 
	 * PageableResponse<ProductDto> getAllOfCategory(Long categoryId,int
	 * pageNumber,int pageSize,String sortBy, String sortDir);
	 */
}
