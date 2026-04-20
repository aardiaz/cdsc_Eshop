package com.cdsc.eshopdemo.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperUtils {

	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	
}
