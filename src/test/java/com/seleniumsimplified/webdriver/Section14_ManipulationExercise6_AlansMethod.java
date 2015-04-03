package com.seleniumsimplified.webdriver;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Section14_ManipulationExercise6_AlansMethod {

	
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
	public void submitFormWithMultiSelect123SelectedChainOfFindElements(){
		WebElement multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));
		List<WebElement> multiSelectOptions = multiSelect.findElements(By.tagName("option"));
		
		multiSelectOptions.get(0).click();
		multiSelectOptions.get(1).click();
		multiSelectOptions.get(2).click();
		
		if(multiSelectOptions.get(3).isSelected()){
			multiSelectOptions.get(3).click();
		}
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		Assert.assertEquals("ms1",driver.findElement(By.cssSelector("#_valuemultipleselect0")).getText(), "ms1");
		Assert.assertEquals("ms2",driver.findElement(By.cssSelector("#_valuemultipleselect1")).getText(), "ms2");
		Assert.assertEquals("ms3",driver.findElement(By.cssSelector("#_valuemultipleselect2")).getText(), "ms3");
		
		//gets a list of the element, and asserts that the list is empty
		Assert.assertTrue("ms4", driver.findElements(By.cssSelector("#_valuemultipleselect3")).isEmpty());
		
	}
	
	/**
	 * Helper methods
	 */
	
	
	
	private void clickSubmitButton(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.click();
	}
	
	
		
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
