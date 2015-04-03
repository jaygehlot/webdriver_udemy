package com.seleniumsimplified.webdriver;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class YellDotComIeTest {

	private static WebDriver driver;
	private static String yellDotComSite = "http://www-b.yellqatest.com";
	
	
	@BeforeClass
	public static void mainSetup(){
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("network.proxy.type", 0);
		driver = new InternetExplorerDriver();
	}
	
	@Before
	public void preTestSetup(){
		//the test needs to navigate to the basic form page prior to each test
		//if it is in @BeforeClass it will do this only once, and so the first test will pass
		driver.get(yellDotComSite);		
	}
	
	@Test
	public void addListingToShortListThenRemove(){
		driver.findElement(By.id("search_whatInput")).sendKeys("plumbers");
		driver.findElement(By.id("location")).sendKeys("reading");
		driver.findElement(By.className("search-submit")).click();
		
		//click to add the first listing to a shortlist
		driver.findElement(By.xpath(".//*[@data-company-item='addtoshortlist']")).click();
		
		List<WebElement> shortListAdd = driver.findElements(By.xpath(".//*[@id='shortlist']/ul/li"));
		Assert.assertTrue(shortListAdd.size() == 1);
		
		//remove the first listing from the shortlist
		driver.findElement(By.xpath(".//*[@data-company-item='removefromshortlist']")).click();
		List<WebElement> shortListRemove = driver.findElements(By.xpath(".//*[@id='shortlist']/ul/li"));
		Assert.assertTrue(shortListRemove.size() == 0);
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
	
}
