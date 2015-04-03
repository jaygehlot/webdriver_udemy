package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;



public class Section18_Lecture116_WebDriverWaitExercise {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void goToPage(){
		driver.get(basicAjaxSite);
	}

	
	@Test
	public void exampleUsingExpectedConditions(){
		wait = new WebDriverWait(driver, 10);
		
		// dont need a separate assertion, if after 10 seconds the title is not found
		// a TimeOutException will be thrown
		wait.until(ExpectedConditions.titleIs("HTML Form Elements"));
	}
	
	
	@Test
	public void exampleWithSleepTimeOf50Milliseconds(){
		wait = new WebDriverWait(driver, 10, 50);
		
		// dont need a separate assertion, if after 10 seconds the title is not found
		// a TimeOutException will be thrown
		wait.until(titleIs("HTML Form Elements"));
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
