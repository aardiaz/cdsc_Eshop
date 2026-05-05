package com.cdsc.eshopdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.service.CategoryService;

@SpringBootTest
public class CategoryTests {
	
	@Autowired
	private CategoryService  catService;
	
	@Test
	public void createTest() {

		CategoryDto dto = CategoryDto.builder()
		           .title("Mobile")
		           .description("test mobile")
		           .build();
		
		CategoryDto  resDto = catService.create(dto);
		
		assertNotNull(resDto);
		assertEquals("Mobile", resDto.getTitle());
		assertEquals("test mobile", resDto.getDescription());
	}
}
