package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import static org.hamcrest.Matchers.is;

public class Section10_L51_FindElement_By_ExerciseTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUpDriver(){
		
		FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
	}
	
	@Test
	public void noSuchElementException_thrownWhenLocatorIsWrong(){
		
		try{
			WebElement cParagraph = driver.findElement(By.id("p3Name"));
			
			//the method below will be executed when findElement
			//DOES find what it is looking for in the DOM
			//if it doesn't find what it is looking for, the catch statement
			//will be executed because findElement will throw a 
			//NoSuchElementException
			fail("Expected NoSuchElementException");
		}catch(NoSuchElementException e){
			//ignore, we wanted to find an exception
			
		}
	}
	
	@Test
	public void usingByID(){
		WebElement byID = driver.findElement(By.id("p25"));
		assertThat(byID.getAttribute("id"), is("p25"));
		assertThat(byID.getText(), is("This is y paragraph text"));
	}
	
	@Test
	public void usingByLinkText(){
		WebElement jumpToPara1 = driver.findElement(By.linkText("jump to para 1"));
		assertThat(jumpToPara1.getText(), is("jump to para 1"));
		assertEquals("a27", jumpToPara1.getAttribute("id"));
	}
	
	@Test
	public void usingByPartialLinkText(){
		WebElement byPartialLinkText = driver.findElement(By.partialLinkText("para 5"));
		assertThat(byPartialLinkText.getText(), is("jump to para 5"));
	}
	
	@Test
	public void usingByPartialLinkText2(){
		//match beginning of text
		WebElement jump_to = driver.findElement(By.partialLinkText("jump to"));
		assertEquals("jump to para 0", jump_to.getText());
		
		//match middle of text
		jump_to=null;
		jump_to = driver.findElement(By.partialLinkText("to"));
		assertEquals("jump to para 0", jump_to.getText());
		
		//match end of text
		jump_to=null;
		jump_to = driver.findElement(By.partialLinkText("7"));
		assertEquals("jump to para 7", jump_to.getText());
	}
	
	@Test
	public void usingByName(){
		WebElement byName = driver.findElement(By.name("pName26"));
		assertThat(byName.getAttribute("name"), is("pName26"));
		assertThat(byName.getText(), is("nested para text"));
	}
	
	@Test
	public void usingByClassName1(){
		//start out at the outer level which is for the class = specialDiv
		WebElement byClassName = driver.findElement(By.className("specialDiv"));
		assertEquals("mydivname", byClassName.getAttribute("name"));
	}
	
	@Test
	public void usingByClassName2(){
		//chaining up findElement to get to further levels (or deeper) into a DOM
		//start out at the outer level which is for the class = specialDiv
		//then within specialDiv look for the 'name' attribute, called multiValues, and get the text of that
		WebElement byClassName = driver.findElement(By.className("specialDiv")).findElement(By.name("multiValues"));
		assertTrue(byClassName.getText().contains("within div of multiple class styles"));
	}
	
	
	@AfterClass
	public static void tearDownDriver(){
		driver.close();
	}

}
