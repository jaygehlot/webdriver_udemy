package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section14_ManipulationExercise1_AlansMethod {

	
	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
	
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void navigateToSite(){
		driver.get(compendiumDevSite);
	}
	
	
	@Test
	public void submitFormWithClickButton(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		//find submit button and click on it
		submitButton.click();
		
		Assert.assertEquals("Expected the form to be processed", 
						driver.getTitle(), "Processed Form Details");
	}
	
	
	@Test
	public void submitFormWithButtonSubmit(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		//find submit button and submit it, because if the element is within a form, then you can submit on the element
		submitButton.submit();
		
		Assert.assertEquals("Expected the form to be processed", 
						driver.getTitle(), "Processed Form Details");
	}
	
	
	@Test
	public void submitFormWithFormSubmit(){
		WebElement submitButton = driver.findElement(By.id("HTMLFormElements"));
		//submit the form itself
		submitButton.submit();
		
		Assert.assertEquals("Expected the form to be processed", 
						driver.getTitle(), "Processed Form Details");
	}
	
	
	@Test
	public void iCanActuallySubmitOnAnyFormElement(){
		WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
		//submit the using the password edit field
		password.submit();
		
		Assert.assertEquals("Expected the form to be processed", 
						driver.getTitle(), "Processed Form Details");
	}
	
	
	@Test
	public void submitButtonWithKeyPress(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		//submit the using the password edit field
		
		//Keys.ENTER doesn't work in FF14 onwards, so Keys.SPACE needs to be used
		submitButton.sendKeys(Keys.SPACE);
		
		Assert.assertEquals("Expected the form to be processed", 
						driver.getTitle(), "Processed Form Details");
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
