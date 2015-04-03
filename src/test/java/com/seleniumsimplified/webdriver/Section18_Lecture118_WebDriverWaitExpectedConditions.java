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
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Section18_Lecture118_WebDriverWaitExpectedConditions {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	@Before
	public void navigateToSite(){
		driver.get(basicAjaxSite);
		
		wait = new WebDriverWait(driver, 10, 50);
	}
	
	@Test
	public void waitForInvisibilityOfImage(){
		//select Server in combo1
		selectServerInCombo1();
		
		//wait for the right condition before proceeding
		//in this case invisibility of an item
		wait.until(invisibilityOfElementLocated(By.id("ajaxBusy")));
		
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	@Test
	public void waitUsingPresenceOfElementLocated(){
		//select Server in combo1
		selectServerInCombo1();
		
		//wait for the right condition before proceeding
		//in this case waiting for the presence of an element in the DOM
		//is it in the DOM
		wait.until(presenceOfElementLocated(By.cssSelector("option[value='23']")));
		
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	
	@Test
	public void waitUsingVisibilityOfElementLocated(){
		//select Server in combo1
		selectServerInCombo1();
		
		//wait for the right condition before proceeding
		//check, is the element in the DOM and visible
		wait.until(visibilityOfElementLocated(By.cssSelector("option[value='23']")));
		
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	
	@Test
	public void waitUsingElementToBeClickable(){
		//select Server in combo1
		selectServerInCombo1();
		
		//wait for the right condition before proceeding
		//check, is it in the DOM, visible and clickable
		wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
		
		selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected();
	}
	
	
	
	
	private void selectJavaInCombo2SubmitAndCheckPageShowsJavaSelected() {
		driver.findElement(By.cssSelector("#combo2 > option[value='23']")).click();
		
		//submit the form
		driver.findElement(By.name("submitbutton")).submit();
		
		wait.until(titleIs("Processed Form Details"));
		
		//ensure that value language is id 23
		Assert.assertEquals(driver.findElement(By.id("_valuelanguage_id")).getText(), "23");
	}

	private void selectServerInCombo1() {
		driver.findElement(By.cssSelector("#combo1 > option[value='3']")).click();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
