package com.seleniumsimplified.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitBeforeAndAfterTest {
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("Before class");
	}
	
	@Before
	public void beforeTest(){
		System.out.println("Before the test");
	}
	@Test
	public void this1Test(){
		System.out.println("This is 1 test");
	}
	
	@Test
	public void this2Test(){
		System.out.println("This is 2 test");
	}
	
	@Test
	public void this3Test(){
		System.out.println("This is 3 test");
	}
	
	@After
	public void afterTest(){
		System.out.println("After the test");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("After class");
	}

}
