package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cdsc.eshopdemo.utils.AESUtil;

public class TestUtils {
	
	@Test
	void testEnc() throws Exception {
		String encData= AESUtil.encrypt("admin123");
		System.out.println("-----data-----"+encData);
		
		String decData = AESUtil.decrypt(encData);
		System.out.println("------decript data ="+decData);
		assertEquals("admin123", decData);
	}

}
