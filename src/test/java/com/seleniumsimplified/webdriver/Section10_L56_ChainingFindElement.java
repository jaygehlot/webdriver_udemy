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
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByChained;

public class Section10_L56_ChainingFindElement {

	private static WebDriver driver;
	private final static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void setup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver();
		driver.navigate().to(compendiumDevSite);
	}
	
	@Test
	public void chainingFindElement(){
		WebElement element = driver.findElement(By.id("div1")).
								findElement(By.name("pName3")).
								findElement(By.tagName("a"));
		
		Assert.assertEquals("expected a different id", "a3", element.getAttribute("id"));
	}
	
	@Test
	public void usingByChainedClass(){
		WebElement element = driver.findElement( new ByChained(By.id("div1"),
																By.name("pName9"), 
																	By.tagName("a") ));
		
		Assert.assertEquals("expected a different id", "a9", element.getAttribute("id"));
	}
	
	@Test
	public void findByIdOrNameMatchOnName(){
		
		WebElement element = driver.findElement( new ByIdOrName("pName2"));
		
		Assert.assertEquals("Expected a match on name", "This is b paragraph text", element.getText());
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
	
}
