package com.seleniumsimplified.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class JunitExampleTest {

	@Test
	public void twoPlusTwoEqualsFourTest(){
		assertEquals("assert that 2 plus 2 equal to four", 4, 2+2);
	}
}
