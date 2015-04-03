package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section16_Lecture95_AlertExercises {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/alerts.html";
	private static String PROMPT_ALERT_TEXT = "Jay Gehlot";
	
	@BeforeClass
	public static void mainSetup(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void preTestSetup(){
		//the test needs to navigate to the basic form page prior to each test
		//if it is in @BeforeClass it will do this only once, and so the first test will pass
		driver.get(compendiumDevSite);		
	}
	
	@Test
	public void exercise1AcceptAlert(){
		driver.findElement(By.id("alertexamples")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am an alert box!", alert.getText());
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void exercise1DismissAlert(){
		driver.findElement(By.id("alertexamples")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am an alert box!", alert.getText());
		driver.switchTo().alert().dismiss();
	}
	
	@Test
	public void exercise2AcceptConfirmAlert(){
		String confirmAlertTextBefore = driver.findElement(By.id("confirmreturn")).getText();
		Assert.assertEquals("cret", confirmAlertTextBefore);		
		
		clickOnConfirmAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("confirmreturn")).getText();
		Assert.assertEquals("true", confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
		
	}
	
	
	@Test
	public void exercise2DismissConfirmAlert(){
		String confirmAlertTextBefore = driver.findElement(By.id("confirmreturn")).getText();
		Assert.assertEquals("cret", confirmAlertTextBefore);		
		
		clickOnConfirmAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("confirmreturn")).getText();
		Assert.assertEquals("false", confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
		
	}
	
	
	@Test
	public void exercise3AcceptPromptAlert(){
		String confirmAlertTextBefore = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextBefore);		
		
		clickOnPromptAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("change me", confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
	}
	
	@Test
	public void exercise3DismissPromptAlert(){
		String confirmAlertTextBefore = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextBefore);		
		
		clickOnPromptAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
	}
	
	
	@Test
	public void exercise4PromptChangeTextAcceptAlert(){		
		String confirmAlertTextBefore = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextBefore);		
		
		clickOnPromptAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(PROMPT_ALERT_TEXT);
		alert.accept();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals(PROMPT_ALERT_TEXT, confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
	}
	
	
	@Test
	public void exercise4PromptChangeTextDismissAlert(){		
		String confirmAlertTextBefore = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextBefore);		
		
		clickOnPromptAlert();
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(PROMPT_ALERT_TEXT);
		alert.dismiss();
			
		
		String confirmAlertTextAfter = driver.findElement(By.id("promptreturn")).getText();
		Assert.assertEquals("pret", confirmAlertTextAfter);
		Assert.assertTrue(confirmAlertTextBefore!=confirmAlertTextAfter);
	}
	
	
	private void clickOnPromptAlert(){
		driver.findElement(By.id("promptexample")).click();
	}
	
	private void clickOnConfirmAlert() {
		driver.findElement(By.id("confirmexample")).click();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
	
}
