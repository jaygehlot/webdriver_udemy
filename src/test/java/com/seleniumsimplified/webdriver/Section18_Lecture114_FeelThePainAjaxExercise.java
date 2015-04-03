package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Section18_Lecture114_FeelThePainAjaxExercise{
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
        PageFactory.initElements();
	}
	
	@Before
	public void navigateToSite(){
		driver.get(basicAjaxSite);
		
		wait = new WebDriverWait(driver, 10, 50);
	}
	
	@Test
	public void simpleAjaxTest(){
		//select Server in combo1
		driver.findElement(By.cssSelector("#combo1 > option[value='3']")).click();
		
		//select Java in combo2, waiting for 10 seconds until the option is available
		wait.until(presenceOfElementLocated(By.cssSelector("#combo2 > option[value='23']")));
		driver.findElement(By.cssSelector("#combo2 > option[value='23']")).click();
		
		//submit the form
		driver.findElement(By.name("submitbutton")).submit();
		
		wait.until(titleIs("Processed Form Details"));
		
		//ensure that value language is id 23
		Assert.assertEquals(driver.findElement(By.id("_valuelanguage_id")).getText(), "23");
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
