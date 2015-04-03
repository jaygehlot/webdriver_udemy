package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class Section11_Lecture61_CSSSelectorExercise {
	
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
	public void firstCSSSelectorTestReplaceById(){
		Assert.assertEquals("The name of this element is not pName31",							 
							"pName31",
							driver.findElement(By.cssSelector("#p31")).getAttribute("name"));
	}
	
	@Test
	public void secondCSSSelectorTestReplaceByName(){
		Assert.assertEquals("The id of this element is not ul1",							
							"ul1",
							driver.findElement(By.cssSelector("[name='ulName1']")).getAttribute("id"));
	}
	
	@Test
	public void thirdCSSSelectorTestReplaceClassName(){
		Assert.assertEquals("The id of this element is not div1",							
							"div1",
							driver.findElement(By.cssSelector(".specialDiv")).getAttribute("id"));
	}
	
	
	@Test
	public void fourthCSSSelectorTestReplaceTagName(){
		Assert.assertEquals("The name of this element is not liName1",							 
							"liName1",
							driver.findElement(By.cssSelector("li[name='liName1']")).getAttribute("name"));       
							//since liName1 is the first one, we could use just "li" for the cssSelector
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
