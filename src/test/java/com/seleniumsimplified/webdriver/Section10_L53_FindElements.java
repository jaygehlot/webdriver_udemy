package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section10_L53_FindElements {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void setUpDriver(){
		FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
	}
	
	@Test
	public void usingFindElements(){
		List<WebElement> pageElements = driver.findElements(By.className("normal"));
		//HashSet stores only unique items, no duplicates
		Set<String> pageTags = new HashSet<String>();
		
		for (WebElement element: pageElements){
			pageTags.add(element.getTagName());
		}
		
		assertTrue("expected p tag", pageTags.contains("p"));
		assertTrue("expected li tag", pageTags.contains("p"));
		assertTrue("expected ul tag", pageTags.contains("p"));
		assertTrue("expected a tag", pageTags.contains("p"));
		assertFalse("div is not expected", pageTags.contains("div"));
	}

	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
