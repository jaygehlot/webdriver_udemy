package com.seleniumsimplified.webdriver;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Lecture34_NavigationExerciseTest {
	
	private static FirefoxProfile profile;
	private static WebDriver driver;
	final private String compendiumDevHomePage = "http://compendiumdev.co.uk";
	final private String seleniumSimplified = "/selenium";
	final private String search = "/search.php";
	final private String basicHtmlForm = "/basic_html_form.html";
	final private String webPage = "/basic_web_page.html";
	final private String refreshPage = "/refresh.php";
	
	@BeforeClass
	public static void instantiateWebDriver(){
		
		profile = new FirefoxProfile();
    	profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Test
	public void navigateToCompendiumDev() throws MalformedURLException{
		
		//start off on the compendium dev homepage
		driver.navigate().to(compendiumDevHomePage); 
		assertTrue(driver.getTitle().contains("Software Testing"));
		
		//then navigate to http://compendiumdev.co.uk/selenium  
		//java allows the creation of a URL which can be passed to the navigate.to() method or get() method
		//and throws MalformedURLException
		URL seleniumSimplifiedPage = new URL(compendiumDevHomePage+seleniumSimplified);
		driver.navigate().to(seleniumSimplifiedPage);
		assertTrue(driver.getTitle().contains("Selenium Simplified"));
		
		
		//navigate to - http://compendiumdev.co.uk/selenium/search.php
		driver.navigate().to(compendiumDevHomePage+seleniumSimplified+search);
		assertThat(driver.getTitle(), containsString("Search Engine")); 	//using assertThat with a JUnit Matcher containString
		
		//navigate to - http://compendiumdev.co.uk/selenium/basic_html_form.html
		driver.navigate().to(compendiumDevHomePage+seleniumSimplified+basicHtmlForm);
		assertThat(driver.getTitle(), containsString("HTML Form Elements"));
		
		//navigate to - http://compendiumdev.co.uk/selenium//basic_web_page.html
		driver.navigate().to(compendiumDevHomePage+seleniumSimplified+webPage);
		assertThat(driver.getTitle(), containsString("Basic"));
		
		//navigate to - http://compendiumdev.co.uk/selenium//basic_web_page.html
		driver.navigate().to(compendiumDevHomePage+seleniumSimplified+refreshPage);
		assertThat(driver.getTitle(), containsString("Refreshed"));
		
		driver.navigate().back();
		assertEquals("We should be on Basic Web Page", "Basic Web Page Title", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("We should be on HTML Form Elements page", "HTML Form Elements", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("We should be on Search Engine page", "Selenium Simplified Search Engine", driver.getTitle());
		
		driver.navigate().back();
		assertThat(driver.getTitle(), containsString("Selenium Simplified"));
		
		driver.navigate().back();
		assertThat(driver.getTitle(), containsString("Software Testing"));
		
		driver.navigate().forward();
		assertThat(driver.getTitle(), containsString("Selenium Simplified"));
		
		driver.navigate().forward();
		assertEquals("We should be on Search Engine page", "Selenium Simplified Search Engine", driver.getTitle());
		
		driver.navigate().forward();
		assertEquals("We should be on HTML Form Elements page", "HTML Form Elements", driver.getTitle());
		
		driver.navigate().forward();
		assertEquals("We should be on Basic Web Page", "Basic Web Page Title", driver.getTitle());
		
		driver.navigate().forward();
		assertThat(driver.getTitle(), containsString("Refreshed"));
		
		driver.navigate().refresh();
		assertThat(driver.getTitle(), containsString("Refreshed"));

		driver.close();
	}
	
	

}
