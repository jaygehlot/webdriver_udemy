package com.seleniumsimplified.webdriver;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Section10_L54_FindElements {

	private static WebDriver driver;
	private final static String compendiumDevSite = "http://www.compendiumdev.co.uk/selenium/find_by_playground.php";
	
	@BeforeClass
	public static void setUp(){
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(profile);
		driver.navigate().to(compendiumDevSite);
	}
	
	@Test
	public void ensure18DivElements(){
		List<WebElement> divElements = driver.findElements(By.tagName("div"));
		Assert.assertTrue(divElements.size() == 19);
	}
	
	@Test
	public void ensure25AWhereHrefToParagraph(){
		int x=0;
		List<WebElement> anchorLinks = driver.findElements(By.tagName("a"));
		
		for (WebElement element:anchorLinks){
			 if((element.getAttribute("href")) != null) //if the href attributes exists in the anchor tag then get 
			 {											//the value of the href attribute and its text and print out						
				 System.out.println(element.getAttribute("href"));
				 System.out.println(element.getText());
				 x++;
			 }
		}		
		Assert.assertTrue(x==25);
	}
	
	@Test
	public void assert25LocalHrefLinks(){
		List<WebElement> elements = driver.findElements(By.partialLinkText("jump to para"));
		Assert.assertEquals(25, elements.size());
	}
	
	@Test
	public void checkThatAnAttributeDoesNotExist(){
		String checkIfNull = "someText";
		List<WebElement> anchorLinks = driver.findElements(By.tagName("a"));
		
		for (WebElement element:anchorLinks){
			 checkIfNull = element.getAttribute("bob"); //if the attribute does not exist in the element, return null
		}
		
		Assert.assertNull(checkIfNull);	
	}
	
	@Test
	public void checkNumberOfParagraphsIs41AndNestedParagraphsIs16(){
		int nestedCounter=0;
		List<WebElement> paragraphElements = driver.findElements(By.tagName("p"));
		
		for(WebElement element:paragraphElements){
			if(element.getText().contains("nested")){
				nestedCounter++;
			}
		}
		
		Assert.assertEquals(41, paragraphElements.size());
		Assert.assertEquals(16, nestedCounter);
	}
	
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}
}
