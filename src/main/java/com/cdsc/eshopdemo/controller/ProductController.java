package com.cdsc.eshopdemo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.dto.PageableResponse;
import com.cdsc.eshopdemo.dto.ProductDto;
import com.cdsc.eshopdemo.service.CategoryService;
import com.cdsc.eshopdemo.service.ProductService;
import com.cdsc.eshopdemo.serviceimpl.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/add")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,@RequestParam("categoryId") Long categoryId) {
		
		CategoryDto  catDto= categoryService.get(categoryId); // Check if category exists, throws exception if not found
		productDto.setCategory(catDto);
		
		ProductDto createdProduct = productService.create(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}
	
	 //get all
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }
	
    
    //upload product image
    @PostMapping("/upload/{productId}")
    public ResponseEntity<String> uploadProductImage(@PathVariable("productId") Long productId,
            @RequestPart("image") MultipartFile image) throws IOException {
    	
		/*
		 * if(!image.isEmpty()) {
		 * 
		 * String fileName =
		 * "src/main/resources/static/images/"+System.currentTimeMillis()+image.
		 * getOriginalFilename(); Files.copy(image.getInputStream(), Path.of(fileName),
		 * StandardCopyOption.REPLACE_EXISTING);
		 * 
		 * 
		 * }
		 */
    	
        String fileName = fileService.saveImage(image);
        ProductDto productDto = productService.get(productId);
        productDto.setProductImageName(fileName);
        productService.update(productDto, productId);
        
        return new ResponseEntity<>(fileName, HttpStatus.OK);
	
    }
	
    @GetMapping("/image/{productId}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("productId") Long productId) throws IOException {
        ProductDto productDto = productService.get(productId);
         Resource resource = new UrlResource(Path.of(productDto.getProductImageName()).toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or detect dynamically
                .body(resource);
    }
    
    
    
    
    
    
    
    
    
    
    @GetMapping("/img/{productId}")
    public void serveUserImage(@PathVariable Long productId, HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.get(productId);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(new FileInputStream(productDto.getProductImageName()), response.getOutputStream());
    }

}
