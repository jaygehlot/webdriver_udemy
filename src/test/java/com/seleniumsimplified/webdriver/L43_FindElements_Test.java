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

public class L43_FindElements_Test {

	
private static WebDriver driver;
	
	@BeforeClass
	public static void setUpDriver(){
		
		FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
	}
	
	@Test
	public void returnAListOfElementsByClassName(){
		List<WebElement> elements = driver.findElements(By.className("normal"));
		
		Set<String> foundTags = new HashSet<String>();
		
		// iterates through each of the elements in the list 'elements'
		// and gets the tagName for each and adds this tag to the HashSet 'foundTags'
		for (WebElement e: elements){
			foundTags.add(e.getTagName());
		}
		
		assertTrue(foundTags.contains("ul"));
		assertTrue(foundTags.contains("li"));
		assertTrue(foundTags.contains("p"));
		assertTrue(foundTags.contains("a"));
		assertFalse(foundTags.contains("div"));
		
		//value (on the line below) is false if the String cannot be added, true if it can be added
		Object value = foundTags.add("li");
		if ( value.equals(false))
		      System.out.println("Could not add li." );
		System.out.println(foundTags);
	}
	
	
	
	@AfterClass
	public static void tearDownDriver(){
		driver.close();
	}
}
