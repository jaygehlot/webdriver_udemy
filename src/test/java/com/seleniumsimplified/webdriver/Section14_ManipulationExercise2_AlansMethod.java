package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Section14_ManipulationExercise2_AlansMethod {

	
	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
	
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void navigateToSite(){
		driver.get(compendiumDevSite);
	}
	
	
	@Test
	public void submitFormDefaultComments(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		
		submitButton.click();
		
		//a Wait implemented to err on the side of caution
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		WebElement valueComments = driver.findElement(By.id("_valuecomments"));
		
		Assert.assertEquals("Expected default comments", "Comments...", valueComments.getText());
	}
	
	
	@Test
	public void submitFormWithMyComments(){
		
		WebElement submitButton;
		WebElement commentsArea;
		
		String myComments = "This is my comment";
		
		commentsArea = driver.findElement(By.name("comments"));
		commentsArea.clear();
		commentsArea.sendKeys(myComments);
		
		submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		
		submitButton.click();
		
		//a Wait implemented to err on the side of caution
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
				
		WebElement commentResults = driver.findElement(By.id("_valuecomments"));
				
		Assert.assertEquals("Expected default comments", myComments, commentResults.getText());
		
	}
		
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
