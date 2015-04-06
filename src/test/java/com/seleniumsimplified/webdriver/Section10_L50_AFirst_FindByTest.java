package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class Section10_L50_AFirst_FindByTest {
	
	private static WebDriver driver;
	private final static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void createDriverAndVisitTestPage(){
		FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.navigate().to(compendiumDevSite);
	}
	
	@Test
	public void findByID(){
		WebElement cParagraph = driver.findElement(By.id("p3"));
		assertEquals("This is c paragraph text", cParagraph.getText());
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

	
	

}
