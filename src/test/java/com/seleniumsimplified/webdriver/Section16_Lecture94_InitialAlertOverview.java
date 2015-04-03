package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section16_Lecture94_InitialAlertOverview {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/alert.html";	
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void preTestSetup(){
		//the test needs to navigate to the basic form page prior to each test
		//if it is in @BeforeClass it will do this only once, and so the first test will pass
		driver.get(compendiumDevSite);		
	}
	
	@Test
	public void basicAlertHandlingExample(){
		
		driver.findElement(By.id("alertexamples")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am an alert box!", alert.getText());
		
		driver.switchTo().alert().accept();
		
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
	
}
