package com.seleniumsimplified.junit;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

public class JUnitExerciseClass {
	
	static int number1;
	static int number2;
	static int number3;
	
	@BeforeClass
	public static void beforeClass(){
		number1 = 10;
		number2 = 20;
	}
	
	@Before
	public void beforeTest(){
	number1 += 10;
	number2 += 20;
	}
	
	/**
	 * This method will not modify the static variables - number1 or number2
	 * This method will modify static variable number3
	 */
	@Test
	public void addIt(){
		number3 = number1 + number2;
		assertTrue("It is true that - A thousand is greater than Number1 added to Number2", 1000>number3);
		assertFalse("Five is not going to be greater than number3, so this assertion fails", 5>number3);
		assertEquals("Number3 is equal to number1 plus number2", number3, number1+number2);
		
		assertThat(number3, is(number3));
	}

	@Test
	public void multiplyIt(){
		number3 = number1 * number2;
		assertTrue("It is true that - A thousand is greater than Number1 multiplied to Number2", 1000>number3);
		assertFalse("Five is not going to be greater than number3, so this assertion fails", 5>number3);
		assertEquals("Number3 is equal to number1 plus number2", number3, number1*number2);
		
		assertThat(number3, greaterThan(number2) );
		assertThat(number3, not(number2) );
		
	
	}
	
	/**
	 * Re-sets static variable number3
	 * Sets static variable number1 to 10 (so that the @Before can add 10 to it again)
	 * Sets static variable number1 to 20 (so that the @Before can add 20 to it again)
	 */
	@After
	public void tearDownAfterEachTest(){
		number3 = 0;	
		number1 = 10;
		number2 = 20;
	}
	
	@AfterClass
	public static void afterClass(){
		number3 = 0;
		number1 = 0;
		number2 = 0;
	}
}
