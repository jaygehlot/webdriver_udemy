package com.seleniumsimplified.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;

public class JUnitExercisesTest {
	
	private String iSetThisBefore = "set as a field";
	private static String iSetThisBeforeClass = "default";
	
	@Test
	public void usingAssertTrue(){
		assertTrue("true is true", true);
		assertTrue("I know what 3+3 is", (3+3)==6);
		
		String theAnswer = "The Answer";
		assertTrue("The Answer is true", "the answer".equalsIgnoreCase(theAnswer));
	}
	
	@Test
	public void usingAssertFalse(){
		assertFalse("false is not true", !true);
		
		assertFalse("I always forget half of 7", (7/2)==4);
		
		String anAnswer = "An Answer";
		
		assertFalse("An Answer does not contain The Answer", anAnswer.contains("The Answer"));
	}
	
	@Test 
	public void usingAssertEquals(){
		assertEquals("True is equal to true", true, true);
		
		String myAnswer = "My Answer";
		assertEquals("String compare", "My Answer", myAnswer);
		
		assertEquals("3+3", 6, 3+3);
	}
	
	@Before
	public void setSomeThingBeforeToUseLater(){
		iSetThisBefore = "set before";
	}
	
	@After
	public void cleanUpForBefore(){
		iSetThisBefore = "set as a field";
	}
	
	@Test
	public void checkISetSomethingBefore(){
		assertFalse("iSetThisBefore should not contain the default value", "set as field".equals(iSetThisBefore));
		
		assertEquals("iSetThisBefore should have changed", "set before", iSetThisBefore);		
	}

	
	@BeforeClass
	public static void setSomeThingBeforeClass(){
		iSetThisBeforeClass = "for all class methods";
	}
	
	@AfterClass
	public static void cleanUpForBeforeClass(){
		iSetThisBeforeClass = "default";
	}
	
	
	@Test
	public void checkBeforeClassRan(){
		assertFalse("iSetThisBeforeClass should not equal default value", "default".equals(iSetThisBeforeClass));
		
		assertEquals("iSetThisBeforeClass should have changed", "for all class methods", iSetThisBeforeClass);
	}
	
	@Test
	public void assertThatQuestion(){
		//assertThat uses Hamcrest Matchers
		assertThat("The Answer", is(not("An Answer")));
		assertThat("The Answer", is(not(nullValue())));

		//Junit uses custom matchers
		assertThat("The Answer", both(containsString("The")).and(containsString("Answer")));
	}
	
	
}
