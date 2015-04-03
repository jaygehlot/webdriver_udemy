package com.seleniumsimplified.webdriver;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.*;

public class Section14_SelectSupportClass {

	private static WebDriver driver;
	private static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
	private static By submitButton = By.cssSelector("input[type='submit'][name='submitbutton']");
	
	
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
	public void selectSupportClassTest(){
		WebElement multipleElementSelect;
		
		multipleElementSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));
		Select multipleSelect = new Select(multipleElementSelect);
		
		List<WebElement> selectedElements = multipleSelect.getAllSelectedOptions();
		Assert.assertEquals("Expect only one item ", 1, selectedElements.size());
		Assert.assertEquals("Expected 4th item to be selected by default", 
							"Selection Item 4", selectedElements.get(0).getText());
		
		multipleSelect.deselectAll();
		
		//should now be 0 items selected
		selectedElements = multipleSelect.getAllSelectedOptions();
		Assert.assertEquals("Size of elements should be zero now", 0, selectedElements.size());
		
		//select the first three elements
		multipleSelect.selectByVisibleText("Selection Item 1");
		multipleSelect.selectByIndex(1);
		multipleSelect.selectByValue("ms3");
		
		//should now be 3 items selected
		selectedElements = multipleSelect.getAllSelectedOptions();
		Assert.assertEquals("Size of elements should now be 3", 3, selectedElements.size());
		
		clickSubmitButton();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Processed Form Details"));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#_valuemultipleselect0")).getText(), "ms1");
		Assert.assertEquals(driver.findElement(By.cssSelector("#_valuemultipleselect1")).getText(), "ms2");
		Assert.assertEquals(driver.findElement(By.cssSelector("#_valuemultipleselect2")).getText(), "ms3");
		
		//returns a list for element ms4 and asserts that the list isEmpty
		Assert.assertEquals(driver.findElements(By.cssSelector("#_valuemultipleselect3")).size(), 0);
		Assert.assertTrue(driver.findElements(By.cssSelector("#_valuemultipleselect3")).isEmpty());	
	}
	
		
	private void clickSubmitButton(){
		driver.findElement(submitButton).click();
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
