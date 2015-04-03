package com.seleniumsimplified.webdriver;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Section18_Lecture122_WebDriverWaitCustomExpectedConditionsExercise {
	
	private static WebDriver driver;
	private String basicAjaxSite = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";
	private String redirectSite = "http://www.compendiumdev.co.uk/selenium/basic_redirect.html";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
	}
	
	
	
	public void customExpectedConditionUsingAnonymousClassDirectly(){
		driver.get(basicAjaxSite);
		
		//selects Server in the first combo box
		selectServerInCombo1();
		
		//custom wait which returns an object that implements the ExpectedCondition interface
		//again have to override apply method
		//can't use a constructor because its an anonymous class
		
		
		WebElement fortranOption = new WebDriverWait(driver, 10, 50).until( new ExpectedCondition<WebElement>(){
	
				public WebElement apply(WebDriver driver){
					return driver.findElement(By.cssSelector("option[value='21']"));
			}
		});
		
		Assert.assertEquals("Fortran", fortranOption.getText());
		
		selectJavaInCombo2SubmitAndCheckPageShowsFortranSelected();
		 
	}
	
	
	@Test
	public void customExpectedConditionUsingRedirectURL(){
		driver.get(redirectSite);
		
		driver.findElement(By.id("delaygotobounce")).click();
		
		Boolean waitForPageTitle = new WebDriverWait(driver, 10, 50).until(titleDoesNotContain("Basic Redirects", "Bounce"));
		
		Assert.assertTrue("Page title isn't correct", waitForPageTitle);
		
		Assert.assertEquals("Aaargh!", driver.findElement(By.cssSelector("html>body>p")).getText());
	}
	
	

	private ExpectedCondition<Boolean> titleDoesNotContain(final String oldPageTitle, final String newPageTitle) {
		
		return new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver driver){
				String titleOfPage = driver.getTitle();
				return titleOfPage != oldPageTitle && titleOfPage.equals(newPageTitle);
			}
			
		};
	}


	private void selectServerInCombo1() {
		driver.findElement(By.cssSelector("#combo1 > option[value='3']")).click();
	}
	
	private void selectJavaInCombo2SubmitAndCheckPageShowsFortranSelected() {
		driver.findElement(By.cssSelector("#combo2 > option[value='21']")).click();
		
		//submit the form
		driver.findElement(By.name("submitbutton")).submit();
		
		new WebDriverWait(driver, 10, 50).until(titleIs("Processed Form Details"));
		
		//ensure that value language is id 21
		Assert.assertEquals(driver.findElement(By.id("_valuelanguage_id")).getText(), "21");
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}

}
