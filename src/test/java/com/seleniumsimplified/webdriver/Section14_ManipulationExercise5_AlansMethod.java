package com.seleniumsimplified.webdriver;

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

public class Section14_ManipulationExercise5_AlansMethod {

	
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
	public void submitFormWithDropDown5SelectedChainOfFindElements(){
		WebElement dropDownSelect, dropDownOption;
		
		dropDownSelect = driver.findElement(By.cssSelector("[name='dropdown']"));
		dropDownOption = dropDownSelect.findElement(By.cssSelector("[value='dd5']"));
		
		dropDownOption.click();
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		assertDropDownValueIsCorrect();		
	}
	
	
	@Test
	public void submitFormWithDropDown5SelectedOptionDirect(){
		
		driver.findElement(By.cssSelector("[value='dd5']")).click();
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		assertDropDownValueIsCorrect();
	}
	
	
	
	@Test
	public void submitFormWithDropDown5UsingKeyboardFullText(){
		WebElement dropDownSelect;
		
		dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
		dropDownSelect.sendKeys("Drop Down Item 5");
		
		waitForOption5DropDownSelected();
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		assertDropDownValueIsCorrect();
	}
	
	@Test
	public void submitFormWithDropDown5UsingKeyboadSpecialKeys(){
		WebElement dropDownSelect;
		
		dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
		
		//simulate the keyboard using a "chord" of strings, sequence of keyboard presses
		dropDownSelect.sendKeys(Keys.chord(Keys.HOME,
											Keys.DOWN,
											Keys.DOWN,
											Keys.DOWN,
											Keys.DOWN,
											Keys.ENTER));
		
		waitForOption5DropDownSelected();
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		assertDropDownValueIsCorrect();
	}

	
	/**
	 * Helper methods
	 */
	
	private void waitForOption5DropDownSelected() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeSelected(By.cssSelector("option[value='dd5']")));
	}
	
	private void assertDropDownValueIsCorrect(){
		WebElement dropDownResult = driver.findElement(By.id("_valuedropdown"));
		Assert.assertEquals("Expected drop down 5",dropDownResult.getText(), "dd5");
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
