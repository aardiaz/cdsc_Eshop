package org.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cdsc.eshopdemo.utils.AESUtil;

public class TestEncDec {
	
	@Test
	public void encTest() throws Exception {
		String encPsw= AESUtil.encrypt("admin@1234");
		 System.out.println("------enc data ="+encPsw);
		 
		 String decPsw= AESUtil.decrypt(encPsw);
		System.out.println("--------dec psw ==="+decPsw);
		
		 assertEquals("admin@1234", decPsw);
		
	}

}
