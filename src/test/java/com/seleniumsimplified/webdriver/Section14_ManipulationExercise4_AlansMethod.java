package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Section14_ManipulationExercise4_AlansMethod {

	
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
	public void submitFormWithOnlyCheckbox1Selected(){
		
		WebElement checkBox1, checkBox3;
		
		checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
		checkBox3 = driver.findElement(By.cssSelector("input[value='cb3']"));
		
		if(!checkBox1.isSelected()){
			checkBox1.click();
		}
		
		if (checkBox3.isSelected()){
			checkBox3.click();
		}
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		assertCheckBoxResults();
		
	}
	
	/**
	 * Helper methods
	 */
	
	private void assertCheckBoxResults(){
		WebElement checkBox1Result;
		WebElement checkBox3Result=null;
		
		checkBox1Result = driver.findElement(By.cssSelector("#_valuecheckboxes0"));
		
		try{
			checkBox3Result = driver.findElement(By.cssSelector("#_valuecheckboxes3"));
		}catch(NoSuchElementException e){
			//checkbox3 element should not exist
		}
		
		//checkBox1Result should be a WebElement, but checkBox3Result should not be found and therefore
		//should remain as null
		Assert.assertTrue(checkBox1Result!=null);
		Assert.assertTrue(checkBox3Result==null);
	}
	
	private void clickSubmitButton(){
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.click();
	}
	
	
		
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
