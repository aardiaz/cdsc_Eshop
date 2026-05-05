package com.cdsc.eshopdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cdsc.eshopdemo.dto.CategoryDto;
import com.cdsc.eshopdemo.service.CategoryService;
@SpringBootTest
public class CatgoryTest {
	
	@Autowired
	private CategoryService catService;
	
	//add category
		@Test
	   void addCat() {
		   CategoryDto cdto = CategoryDto.builder()
				               .title("TEST")
				               .description("Test category")
				               .build();
		   
		   
		   CategoryDto catReturn = catService.create(cdto);	
		   assertEquals(catReturn.getTitle(), "TEST");
	   }
	
}
