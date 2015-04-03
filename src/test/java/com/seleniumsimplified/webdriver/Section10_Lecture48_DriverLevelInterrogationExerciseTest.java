package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.containsString;


public class Section10_Lecture48_DriverLevelInterrogationExerciseTest {
	
	private static WebDriver driver;
	private static String compendiumDevBasicWebPage = "http://compendiumdev.co.uk/selenium/basic_web_page.html";
	
	@BeforeClass
	public static void setUpDriver(){
		FirefoxProfile profile = new FirefoxProfile();
		  profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}

	@Test
	public void interrogateCompendiumDev() throws MalformedURLException{
		
		URL compendiumdev = new URL(compendiumDevBasicWebPage);
		driver.navigate().to(compendiumdev);
	
		assertThat(driver.getTitle(), is("Basic Web Page Title"));
		assertThat(driver.getCurrentUrl(), is(compendiumDevBasicWebPage));
		assertThat(driver.getPageSource(), containsString("A paragraph of text"));
	}
	
	@AfterClass
	public static void closeDownDriver(){
		driver.close();
	}
}
