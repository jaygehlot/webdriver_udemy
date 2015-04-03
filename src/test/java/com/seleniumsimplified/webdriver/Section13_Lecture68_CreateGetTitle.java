package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section13_Lecture68_CreateGetTitle {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver();
		driver.get(compendiumDevSite);
	}
	
	@Test
	public void getTitleAlternative1UseTagName(){
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.tagName("title")).getText());
	}
	
	@Test
	public void getTitleAlternative2UseCssSelector(){
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.cssSelector("title")).getText());
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.cssSelector("head > title")).getText());
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.cssSelector("html > head > title")).getText());
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.cssSelector("html title")).getText());
	}
	
	@Test
	public void getTitleAlternative3UseXPath(){
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.xpath("/html/head/title")).getText());
		Assert.assertEquals(driver.getTitle(), driver.findElement(By.xpath("//title")).getText());
	}
	
	@Test
	public void getTitleAlternative3UseGetPageSource(){
		String lowerCasePageSource = driver.getPageSource().toLowerCase();
		int titleStart = lowerCasePageSource.indexOf("<title>");
		int titleEnd = lowerCasePageSource.indexOf("</title");
		
		Assert.assertEquals(driver.getTitle(), driver.getPageSource().substring(titleStart+7, titleEnd));
	}
	
	
	
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
