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

public class Section14_ManipulationExercise3_AlansMethod {

	
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
	public void submitFormWithOnlyRadioButton2Selected(){
		WebElement radioButton2 = driver.findElement(By.cssSelector("input[value='rd2']"));
		
		//if radiobutton2 is not selected, select it
		if (!radioButton2.isSelected()){
			radioButton2.click();
		}
		
		//click the submit button
		WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		
		submitButton.click();
		
		//a Wait implemented to err on the side of caution
				new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		WebElement radioButtonResult = driver.findElement(By.id("_valueradioval"));
		
		Assert.assertEquals("expected radio button 2",radioButtonResult.getText(), "rd2");
		
	}
	
		
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
