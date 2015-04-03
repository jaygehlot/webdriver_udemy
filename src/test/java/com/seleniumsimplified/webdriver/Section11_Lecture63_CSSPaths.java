package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Section11_Lecture63_CSSPaths {
	
	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void setUp(){
		driver = new FirefoxDriver();
		driver.get(compendiumDevSite);
	}
	
	@Test
	public void directDescendant(){
		Assert.assertEquals(41, driver.findElements(By.cssSelector("div > p")).size());
		//any paragraph that is directly under div element
		
		Assert.assertEquals(0, driver.findElements(By.cssSelector("div > li")).size());
		// any list item that is directly beneath a div
		// expect to be zero, because list item is normally under a ul
	}
	
	@Test
	public void anyDescendant(){
		Assert.assertEquals(41, driver.findElements(By.cssSelector("div p")).size());
		//p, at any level under div (no necessarily a direct descendant)
		
		Assert.assertEquals(25, driver.findElements(By.cssSelector("div li")).size());
		// li, at any level under div (no necessarily a direct descendant)
	}
	
	
	@Test
	public void siblingOfPreceding(){
		Assert.assertEquals(24, driver.findElements(By.cssSelector("li + li")).size());
		// gets all except first item - because this cssSelector gets an li, where it is preceded with an li
		
		Assert.assertEquals(25, driver.findElements(By.cssSelector("li")).size());
		//gets all the list items
	}
	
	
	@Test
	public void firstChild(){
		Assert.assertEquals(1, driver.findElements(By.cssSelector("li:first-child")).size());
		//gets the first child item that is missed out when li + li is used
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
