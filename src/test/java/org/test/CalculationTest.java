package org.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cdsc.eshopdemo.utils.Calculation;

public class CalculationTest {
	
	static Calculation  cal ;
	
	@BeforeAll
	public static void init() {
		cal = new Calculation();
	}
	
	
	@Test
	public void sumTest() {
		
		int result= cal.sum(900, 500);
		
		assertEquals(1400, result);
	}
	
	@Test
	public void subtractTest() {
		
		  cal = new Calculation();
		int result = cal.subtract(7000, 5000);
		assertEquals(2000, result);
	}

	@AfterEach
	public   void endTestcases(){
		
		System.out.println("-------- Successfully completed-------");
	}
}
