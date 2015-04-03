package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Section18_Lecture125_WebDriverWaitCustomConditionsExercise {
	
	private static WebDriver driver;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";
	private String redirectSite = "http://www.compendiumdev.co.uk/selenium/basic_redirect.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	
	@Test
	public void customExpectedConditionForTitleDoesNotContainUsingClass(){
		driver.get(redirectSite);
		
		driver.findElement(By.id("delaygotobasic")).click();
		
		//the private class is called
		//this will check  if the current page title contains "Redirects", if it does null is returned
		// until the redirect has take place
		Assert.assertEquals("Basic Web Page Title", new WebDriverWait(driver, 8).until(new TitleDoesNotContain("Redirects")));
		
	}
	
	
	//checks if Redirects is in the current page title
	//if it is returns null, if not returns the current title
	private class TitleDoesNotContain implements ExpectedCondition<String> {
		private String titleExcludes;
		
		public TitleDoesNotContain(String doesNotContainString){
			this.titleExcludes = doesNotContainString;
		}
		
		public String apply(WebDriver driver) {
			if (driver.getTitle().contains(this.titleExcludes)){
				return null;
			}
				else {
					return driver.getTitle();
				}
				
			}
	}
	
	
	
	@Test
	public void customExpectedConditionForTitleUsingMethod(){
		WebDriverWait wait;
		
		driver.get(redirectSite);
		
		driver.findElement(By.id("delaygotobasic")).click();
		
		wait = new WebDriverWait(driver, 8);
		
		Assert.assertEquals("Basic Web Page Title", wait.until(titleDoesNotContainText("Redirects")));
	}
	
	private ExpectedCondition<String> titleDoesNotContainText(String textToCheckInTitle){
		return new TitleDoesNotContain(textToCheckInTitle);
	}
	
	
	@Test
	public void customExpectedConditionForTitleUsingACMethod(){
		WebDriverWait wait;
		
		driver.get(redirectSite);
		
		driver.findElement(By.id("delaygotobasic")).click();
		
		wait = new WebDriverWait(driver, 8);
		
		Assert.assertEquals("Basic Web Page Title", wait.until(titlePageDoesNotContainText("Redirects")));
	}
	
	//returns a string object, which is an implementation of ExpectedCondition interface
	private ExpectedCondition<String> titlePageDoesNotContainText(final String stringNotInTitle) {
		return new ExpectedCondition<String>(){

		 public String apply(WebDriver driver) {
			if (driver.getTitle().contains(stringNotInTitle)){
				return null;
			}
				else {
					return driver.getTitle();
				}
				
		 }
		};
		
	}
		
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
